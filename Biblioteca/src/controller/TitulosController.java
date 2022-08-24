package controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.Titulo;

import connection.BancoDeDados;
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

public class TitulosController implements Initializable{

    @FXML
    private TextField tituloTitulo;
    
    @FXML
    private TextField editoraTitulo;
    
    @FXML
    private TextField autorTitulo;
    
    @FXML
    private TextField anoTitulo;
    
    @FXML
    private TextField descricaoTitulo;

    @FXML
    private TextField quantidadeTitulo;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnInicio;

    @FXML
    private TableColumn<Titulo, Integer> colunaId;
    
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
    private TableView<Titulo> tableTitulos;


    @FXML
    void btnAdicionarTitulo(ActionEvent event) {
    	String titulo, editora, autor, ano, descricao;
    	Integer quantidade;
    	titulo = tituloTitulo.getText();
    	editora = editoraTitulo.getText();
    	autor = autorTitulo.getText();
    	ano = anoTitulo.getText();
    	descricao = descricaoTitulo.getText();
    	quantidade = Integer.parseInt(quantidadeTitulo.getText());
    	
    	try {
    		PreparedStatement pst = BancoDeDados.getConexao().prepareStatement("insert into titulos (id, titulo, editora, autor, ano, descricao, quantidade) values(NULL, ?, ?, ?, ?, ?, ?)");
    		pst.setString(1, titulo);
    		pst.setString(2, editora);
    		pst.setString(3, autor);
    		pst.setString(4, ano);
    		pst.setString(5, descricao);
    		pst.setInt(6, quantidade);
    		pst.executeUpdate();
    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Cadastro de título");
			alert.setHeaderText("Tudo certo!");
			alert.setContentText("Cadastro bem sucedido.");
			
			alert.showAndWait();
			
			BancoDeDados.atualizarIDsTitulos();
			limparCampos();
			titulosTable();
    	}catch (SQLException ex) {
    		Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }

    @FXML
    void btnAtualizarTitulo(ActionEvent event) {
    	String titulo, editora, autor, ano, descricao;
    	Integer quantidade;
    	int myIndex =  tableTitulos.getSelectionModel().getSelectedIndex();
		int id = Integer.parseInt(String.valueOf(tableTitulos.getItems().get(myIndex).getId()));
		
		titulo = tituloTitulo.getText();
    	editora = editoraTitulo.getText();
    	autor = autorTitulo.getText();
    	ano = anoTitulo.getText();
    	descricao = descricaoTitulo.getText();
    	quantidade = Integer.parseInt(quantidadeTitulo.getText());
    	
		try {
    		PreparedStatement pst = BancoDeDados.getConexao().prepareStatement("update titulos set titulo = ?, editora = ?, autor = ?, ano = ?, descricao = ?, quantidade = ? where id = ?");
    		pst.setString(1, titulo);
    		pst.setString(2, editora);
    		pst.setString(3, autor);
    		pst.setString(4, ano);
    		pst.setString(5, descricao);
    		pst.setInt(6, quantidade);
    		pst.setInt(7, id);
    		pst.executeUpdate();
    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Atualização de título");
			alert.setHeaderText("Tudo certo!");
			alert.setContentText("Atualização bem sucedida.");
			
			alert.showAndWait();
			BancoDeDados.atualizarIDsTitulos();
			limparCampos();
			titulosTable();
		} catch(SQLException ex) {
    		Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }

    @FXML
    void btnDeletarTitulo(ActionEvent event) {
    	int myIndex =  tableTitulos.getSelectionModel().getSelectedIndex();
		int id = Integer.parseInt(String.valueOf(tableTitulos.getItems().get(myIndex).getId()));
		
		try {
    		PreparedStatement pst = BancoDeDados.getConexao().prepareStatement("delete from titulos where id = ?");
    		pst.setInt(1,  id);
    		pst.executeUpdate();
    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Remoção de título");
			alert.setHeaderText("Tudo certo!");
			alert.setContentText("Remoção bem sucedida.");
			
			alert.showAndWait();
			BancoDeDados.atualizarIDsTitulos();
			limparCampos();
			titulosTable();
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
    	tituloTitulo.setText("");
    	editoraTitulo.setText("");
    	autorTitulo.setText("");
    	anoTitulo.setText("");
    	descricaoTitulo.setText("");
    	quantidadeTitulo.setText(""); 
    }
    
    public void titulosTable() {
    	ObservableList<Titulo> titulos = FXCollections.observableArrayList();
    	try {
    		PreparedStatement pst = BancoDeDados.getConexao().prepareStatement("select * from titulos");
    		ResultSet rs = pst.executeQuery();
    		while(rs.next()) {
    			titulos.add(new Titulo(rs.getInt("id"), rs.getString("titulo"), rs.getString("editora"), rs.getString("autor"), rs.getString("ano"), rs.getString("descricao"), rs.getInt("quantidade")));
    		}
    		
        	
        	colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        	colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        	colunaEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        	colunaAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        	colunaAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        	colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        	colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
//        	
        	tableTitulos.setItems(titulos);
//        	
    	}catch(SQLException ex) {
    		Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	
    	tableTitulos.setRowFactory( tv -> {
    		TableRow<Titulo> myRow = new TableRow<>();
    		myRow.setOnMouseClicked (event ->
    		{
    			if (event.getClickCount() == 1 && (!myRow.isEmpty()))
    			{
    				int myIndex =  tableTitulos.getSelectionModel().getSelectedIndex();
//    				int id = Integer.parseInt(String.valueOf(tableUsuarios.getItems().get(myIndex).getId()));
    				tituloTitulo.setText(tableTitulos.getItems().get(myIndex).getTitulo());
    				editoraTitulo.setText(tableTitulos.getItems().get(myIndex).getEditora());
    				autorTitulo.setText(tableTitulos.getItems().get(myIndex).getAutor());
    				anoTitulo.setText(tableTitulos.getItems().get(myIndex).getAno());
    				descricaoTitulo .setText(tableTitulos.getItems().get(myIndex).getDescricao());
    				quantidadeTitulo.setText(tableTitulos.getItems().get(myIndex).getQuantidade().toString());
    			}
    		});
    		return myRow;
    	});
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	//Banco de dados interno precisa estar conectado
//    	BancoDeDados bd = new BancoDeDados("admbiblioteca", "Biblioteca123");
//    	bd.conecta();
    	titulosTable();
    }
}
