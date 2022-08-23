package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.Usuario;
import connection.BancoDeDados;

public class UsuariosController implements Initializable{

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnInicio;

    @FXML
    private TableColumn<Usuario,String> colunaCpf;

    @FXML
    private TableColumn<Usuario, String> colunaCurso;

    @FXML
    private TableColumn<Usuario, Number> colunaId;

    @FXML
    private TableColumn<Usuario,String> colunaMatricula;

    @FXML
    private TableColumn<Usuario,String> colunaNome;
    
    @FXML
    private TableView<Usuario> tableUsuarios;

    @FXML
    private TextField cpfUsuario;

    @FXML
    private TextField cursoUsuario;

    @FXML
    private TextField matriculaUsuario;

    @FXML
    private TextField nomeUsuario;
    
    @FXML
    void btnAdicionarUsuario(ActionEvent event) {
    	String nome, cpf, matricula, curso;
    	nome = nomeUsuario.getText();
    	cpf = cpfUsuario.getText();
    	matricula = matriculaUsuario.getText();
    	curso = cursoUsuario.getText();
    	
    	try {
    		PreparedStatement pst = BancoDeDados.getConexao().prepareStatement("insert into usuarios (id, nome, cpf, matricula, curso) values(NULL, ?, ?, ?, ?)");
    		pst.setString(1, nome);
    		pst.setString(2, cpf);
    		pst.setString(3, matricula);
    		pst.setString(4, curso);
    		pst.executeUpdate();
    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Cadastro de usuário");
			alert.setHeaderText("Tudo certo!");
			alert.setContentText("Cadastro bem sucedido.");
			
			alert.showAndWait();
			
			BancoDeDados.atualizarIDs();
			limparCampos();
			usuariosTable();
    	}catch (SQLException ex) {
    		Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	
    }

    @FXML
    void btnAtualizarUsuario(ActionEvent event) {

    }

    @FXML
    void btnDeletarUsuario(ActionEvent event) {
    	int myIndex =  tableUsuarios.getSelectionModel().getSelectedIndex();
		int id = Integer.parseInt(String.valueOf(tableUsuarios.getItems().get(myIndex).getId()));
		
		try {
    		PreparedStatement pst = BancoDeDados.getConexao().prepareStatement("delete from usuarios where id = ?");
    		pst.setInt(1,  id);
    		pst.executeUpdate();
    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Remoção de usuário");
			alert.setHeaderText("Tudo certo!");
			alert.setContentText("Remoção bem sucedida.");
			
			alert.showAndWait();
			BancoDeDados.atualizarIDs();
			limparCampos();
			usuariosTable();
		} catch(SQLException ex) {
    		Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }

    @FXML
    void btnVoltarInicio(ActionEvent event) {
    	Main.changeScreen("menu");
    	System.out.println("Main Menu Scene Activated!");
    }
    
    public void limparCampos() {
    	nomeUsuario.setText("");
		cpfUsuario.setText("");
		matriculaUsuario.setText("");
		cursoUsuario.setText("");
    }
    public void usuariosTable() {
    	ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    	try {
    		PreparedStatement pst = BancoDeDados.getConexao().prepareStatement("select * from usuarios");
    		ResultSet rs = pst.executeQuery();

    		while(rs.next()) {
    			usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"), rs.getString("curso")));
    		}
    		
        	
        	colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        	colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        	colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        	colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        	colunaCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
//        	
        	tableUsuarios.setItems(usuarios);
//        	
    	}catch(SQLException ex) {
    		Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	
    	tableUsuarios.setRowFactory( tv -> {
    		TableRow<Usuario> myRow = new TableRow<>();
    		myRow.setOnMouseClicked (event ->
    		{
    			if (event.getClickCount() == 1 && (!myRow.isEmpty()))
    			{
    				int myIndex =  tableUsuarios.getSelectionModel().getSelectedIndex();
//    				int id = Integer.parseInt(String.valueOf(tableUsuarios.getItems().get(myIndex).getId()));
    				nomeUsuario.setText(tableUsuarios.getItems().get(myIndex).getNome());
    				cpfUsuario.setText(tableUsuarios.getItems().get(myIndex).getCpf());
    				matriculaUsuario.setText(tableUsuarios.getItems().get(myIndex).getMatricula());       
    				cursoUsuario.setText(tableUsuarios.getItems().get(myIndex).getCurso()); 
    			}
    		});
    		return myRow;
    	});
    }
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	//Banco de dados interno precisa estar conectado
    	BancoDeDados bd = new BancoDeDados("admbiblioteca", "Biblioteca123");
    	bd.conecta();
    	usuariosTable();
    }

}

