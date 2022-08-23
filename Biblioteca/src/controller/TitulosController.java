package controller;

import base.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TitulosController {

    @FXML
    private TextField ano;

    @FXML
    private TextField autor;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnInicio;

    @FXML
    private TableColumn<?, ?> colunaAno;

    @FXML
    private TableColumn<?, ?> colunaAutor;

    @FXML
    private TableColumn<?, ?> colunaDescricao;

    @FXML
    private TableColumn<?, ?> colunaEditora;

    @FXML
    private TableColumn<?, ?> colunaId;

    @FXML
    private TableColumn<?, ?> colunaQuantidade;

    @FXML
    private TableColumn<?, ?> colunaTitulo;

    @FXML
    private TextField descricao;

    @FXML
    private TextField editora;

    @FXML
    private TextField quantidade;

    @FXML
    private TableView<Usuario> tableTitulos;

    @FXML
    private TextField titulo;

    @FXML
    void btnAdicionarUsuario(ActionEvent event) {

    }

    @FXML
    void btnAtualizarUsuario(ActionEvent event) {

    }

    @FXML
    void btnDeletarUsuario(ActionEvent event) {

    }

    @FXML
    void btnVoltarInicio(ActionEvent event) {
    	Main.changeScreen("menu");
    	System.out.println("Main Menu Scene Activated!");
    }
    

}
