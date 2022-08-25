package controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.Emprestimo;
import base.Titulo;
import base.Usuario;
import connection.BancoDeDados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RelatoriosController{

    @FXML
    private Button btnInicio;

    @FXML
    private Button atualizarRelatorios;

    @FXML
    private TableColumn<Usuario, Integer> colunaIdUsuarios;
    
    @FXML
    private TableColumn<Usuario, String> colunaNome;
    
    @FXML
    private TableColumn<Usuario, String> colunaCpf;
    
    @FXML
    private TableColumn<Usuario, String> colunaMatricula;
    
    @FXML
    private TableColumn<Usuario, String> colunaCurso;


    @FXML
    private TableColumn<Titulo, Integer> colunaIdTitulos;
    
    @FXML
    private TableColumn<Titulo, String> colunaTitulo;
    
    @FXML
    private TableColumn<Titulo, String> colunaEditora;
    
    @FXML
    private TableColumn<Titulo, String> colunaAutor;
    
    @FXML
    private TableColumn<Titulo, String> colunaAno;
    
    @FXML
    private TableColumn<Titulo, String> colunaDescricao;
    
    @FXML
    private TableColumn<Titulo, Integer> colunaQuantidade;


    @FXML
    private TableColumn<Emprestimo, Integer> colunaIdEmprestimos;

    @FXML
    private TableColumn<Emprestimo, String> colunaNomeEmprestimos;

    @FXML
    private TableColumn<Emprestimo, String> colunacoCpfEmprestimos;

    @FXML
    private TableColumn<Emprestimo, String> colunaTituloEmprestimos;
    
    @FXML
    private TableColumn<Emprestimo, Integer> colunaQuantidadeEmprestimos;


    @FXML
    private TableView<Usuario> tableUsuarios;
    
    @FXML
    private TableView<Titulo> tableTitulos;
    
    @FXML
    private TableView<Emprestimo> tableEmprestimos;

    @FXML
    void btnVoltarInicio(ActionEvent event) {
    	Main.changeScreen("menu");
    	System.out.println("Main Menu Scene Activated!");
    }
    
    public void relatoriosTable() {
    	ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    	ObservableList<Titulo> titulos = FXCollections.observableArrayList();
    	ObservableList<Emprestimo> relatorios = FXCollections.observableArrayList();
    	try {
    		PreparedStatement pst = BancoDeDados.getConexao().prepareStatement("select * from usuarios");
    		ResultSet rs = pst.executeQuery();

    		while(rs.next()) {
    			usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"), rs.getString("curso")));
    		}
    		
    		pst = BancoDeDados.getConexao().prepareStatement("select * from titulos");
    		rs = pst.executeQuery();
    		
    		while(rs.next()) {
    			titulos.add(new Titulo(rs.getInt("id"), rs.getString("titulo"), rs.getString("editora"), rs.getString("autor"), rs.getString("ano"), rs.getString("descricao"), rs.getInt("quantidade")));
    		}
    		
    		pst = BancoDeDados.getConexao().prepareStatement("select * from emprestimo");
    		rs = pst.executeQuery();
    		
    		while(rs.next()) {
    			relatorios.add(new Emprestimo(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("titulo"),rs.getInt("quantidade")));
    		}
    		
    		colunaIdUsuarios.setCellValueFactory(new PropertyValueFactory<>("id"));
        	colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        	colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        	colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        	colunaCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
//        	
        	tableUsuarios.setItems(usuarios);
        	
    		colunaIdTitulos.setCellValueFactory(new PropertyValueFactory<>("id"));
        	colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        	colunaEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        	colunaAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        	colunaAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        	colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        	colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
//        	
        	tableTitulos.setItems(titulos);
        	
            colunaIdEmprestimos.setCellValueFactory(new PropertyValueFactory<>("id"));;
            colunaNomeEmprestimos.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunacoCpfEmprestimos.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            colunaTituloEmprestimos.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            colunaQuantidadeEmprestimos.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
//        	
    	}catch(SQLException ex) {
    		Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	
    }
    
    @FXML
    void btnAtualizarRelatorios(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Atualização dos Relatórios");
		alert.setHeaderText("Tudo certo!");
		alert.setContentText("Atualização bem sucedida.");
		
		alert.showAndWait();
		relatoriosTable();
    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//    	relatoriosTable();
//    }

}
