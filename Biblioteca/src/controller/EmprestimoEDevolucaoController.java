package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.BancoDeDados;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EmprestimoEDevolucaoController {

    @FXML
    private Text alertaDevolver;

    @FXML
    private Text alertaEmprestar;

    @FXML
    private Button btnDevolver;

    @FXML
    private Button btnEmprestar;

    @FXML
    private Button btnInicio;
    
    @FXML
    private Button btnVerificarDevolver;

    @FXML
    private Button btnVerificarEmprestar;

    @FXML
    private TextField cpfDevolver;

    @FXML
    private TextField cpfEmprestar;

    @FXML
    private TextField nomeDevolver;

    @FXML
    private TextField nomeEmprestar;

    @FXML
    private TextField quantidadeDevolver;

    @FXML
    private TextField quantidadeEmprestar;

    @FXML
    private TextField tituloDevolver;

    @FXML
    private TextField tituloEmprestar;

	String salvar_cpf_emprestar;
	
	boolean verificarTituloEmprestar() {
		try {
			PreparedStatement stmt = BancoDeDados.getConexao().prepareStatement("select titulo from titulos where binary titulo = ?");	
			stmt.setString(1, tituloEmprestar.getText());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch(SQLException e) {
			
		}
		return false;
	}
	
	boolean verificarTituloDevolver() {
		try {
			PreparedStatement stmt = BancoDeDados.getConexao().prepareStatement("select titulo from titulos where binary titulo = ?");	
			stmt.setString(1, tituloDevolver.getText());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch(SQLException e) {
			
		}
		return false;
	}
	
	int verificarQuantidadeTituloEmprestar() {
		Integer quantidade = 0;
		try {
			PreparedStatement stmt = BancoDeDados.getConexao().prepareStatement("select quantidade from titulos where titulo = ?");	
			stmt.setString(1, tituloEmprestar.getText());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				quantidade = Integer.parseInt(rs.getString(1));
			}
		} catch(SQLException e) {
			
		}
		return quantidade;
	}
	
    @FXML
    void btnVerificarCpfEmprestimo(ActionEvent event) {
    	String cpf = cpfEmprestar.getText();
    	try {
    		PreparedStatement stmt = BancoDeDados.getConexao().prepareStatement("select nome,cpf from usuarios where cpf = ?");
    		stmt.setString(1, cpf);
    		ResultSet rs = stmt.executeQuery();
    		if (rs.next()) {
    			nomeEmprestar.setText(rs.getString(1));
    			salvar_cpf_emprestar = rs.getString(2);
    			alertaEmprestar.setText("");
    		}
    		else {
    			nomeEmprestar.setText("");
    			alertaEmprestar.setText("Verifique o CPF!");
    			salvar_cpf_emprestar = "";
    		}
//    		System.out.println(rs.getString(1));    			
    	} catch(SQLException e) {
    		
    	}
    }

    @FXML
    void btnEmprestarTitulo(ActionEvent event) {
    	if (salvar_cpf_emprestar.equals(cpfEmprestar.getText())) {
    		if (verificarTituloEmprestar()) {
    			if (verificarQuantidadeTituloEmprestar() > 0) {
    				if (Integer.parseInt(quantidadeEmprestar.getText()) > verificarQuantidadeTituloEmprestar()) {
    					alertaEmprestar.setText("Quantidade maior do que disponível!");
    				}else {
    					try {
        					alertaEmprestar.setText("Empréstimo bem sucedido.");  
        					PreparedStatement stmt;		
        					stmt = BancoDeDados.getConexao().prepareStatement("insert into emprestimos (id, nome, cpf, titulo, quantidade) values (NULL, ?, ?, ?, ?)");
        					stmt.setString(1, nomeEmprestar.getText());
        					stmt.setString(2, cpfEmprestar.getText());
        					stmt.setString(3, tituloEmprestar.getText());
        					stmt.setInt(4, Integer.parseInt(quantidadeEmprestar.getText()));
        					stmt.executeUpdate();

        					stmt = BancoDeDados.getConexao().prepareStatement("update titulos set quantidade = quantidade-? where titulo = ?");
        					stmt.setInt(1, Integer.parseInt(quantidadeEmprestar.getText()));
        					stmt.setString(2, tituloEmprestar.getText());
        					stmt.executeUpdate();
        					
        					BancoDeDados.atualizarIDsEmprestimos();
        					
        					Alert alert = new Alert(Alert.AlertType.INFORMATION);
        					alert.setTitle("Empréstimo");
        					alert.setHeaderText("Tudo certo!");
        					alert.setContentText("Empréstimo bem sucedido.");
        					alert.showAndWait();			
        				} catch(SQLException e) {
        					
        				}
    				}	
    			}
    			else {
    				alertaEmprestar.setText("Livro indisponível!");
    			}
    		}
    		else {
    			alertaEmprestar.setText("Verifique o título!");
    		}
    	}
    	else {
    		alertaEmprestar.setText("Verifique o CPF!");
    	}
    }

    int verificarQuantidadeTituloDevolver() {
		Integer quantidade  = 0;
		try {
			PreparedStatement stmt = BancoDeDados.getConexao().prepareStatement("select quantidade from emprestimos where cpf = ? and titulo = ?");	
			stmt.setString(1, cpfDevolver.getText());
			stmt.setString(2, tituloDevolver.getText());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				quantidade = Integer.parseInt(rs.getString(1));
			}else {
			}
		} catch(SQLException e) {
			
		}
		return quantidade;
	}
    
    @FXML
    void btnVerificarCpfDevolucao(ActionEvent event) {
    	String cpf = cpfDevolver.getText();
    	try {
    		PreparedStatement stmt = BancoDeDados.getConexao().prepareStatement("select nome from emprestimos where cpf = ?");
    		stmt.setString(1, cpf);
    		ResultSet rs = stmt.executeQuery();
    		if (rs.next()) {
    			nomeDevolver.setText(rs.getString(1));
    			alertaDevolver.setText("");    			
    		}
    		else {
    			nomeEmprestar.setText("");
    			alertaDevolver.setText("Esse CPF não emprestou livros!");
    		}
    	} catch(SQLException e) {
    		
    	}
    }

    @FXML
    void btnDevolverTitulo(ActionEvent event) {
    	PreparedStatement stmt;
    	if ((quantidadeDevolver.getText().equals("")) || tituloDevolver.getText().equals("")){
    		alertaDevolver.setText("Preencha os campos!");  
    	}
    	else {
    		if (verificarTituloDevolver()){
    			if (verificarQuantidadeTituloDevolver() >= Integer.parseInt(quantidadeDevolver.getText())) {
            		try {
            			
            			if (verificarQuantidadeTituloDevolver() == 1) {
            				stmt = BancoDeDados.getConexao().prepareStatement("delete from emprestimos where cpf = ? and titulo = ?");
                			stmt.setString(1, cpfDevolver.getText());
                			stmt.setString(2, tituloDevolver.getText());
                			stmt.executeUpdate();
            			}
            			else {
            				stmt = BancoDeDados.getConexao().prepareStatement("update emprestimos set quantidade = quantidade-? where cpf = ? and titulo = ?");
                			stmt.setInt(1, Integer.parseInt(quantidadeDevolver.getText()));
                			stmt.setString(2, cpfDevolver.getText());
                			stmt.setString(3, tituloDevolver.getText());
                			stmt.executeUpdate();
            			}
            			BancoDeDados.atualizarIDsEmprestimos();
            			
            			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            			alert.setTitle("Devolução");
            			alert.setHeaderText("Tudo certo!");
            			alert.setContentText("Devolução bem sucedida.");
            			alert.showAndWait();   
            			alertaDevolver.setText("Devolução bem sucedida.");  	 			
            		}catch(SQLException e) {
            			
            		}
        		} else {
        			alertaDevolver.setText("Verifique a quantidade");  
        		}
    		} else {
    			alertaDevolver.setText("Verifique o título!");
    		}
    		
    	}	
    }

    @FXML
    void btnVoltarInicio(ActionEvent event) {
    	alertaEmprestar.setText("");
    	alertaDevolver.setText("");
    	Main.changeScreen("menu");
    	System.out.println("Main Menu Scene Activated!");
    }

}
