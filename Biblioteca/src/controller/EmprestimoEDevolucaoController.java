package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EmprestimoEDevolucaoController {

    @FXML
    private Button btnDevolver;

    @FXML
    private Button btnEmprestar;

    @FXML
    private Button btnInicio;

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

    @FXML
    void btnAdicionarUsuario(ActionEvent event) {

    }

    @FXML
    void btnAtualizarUsuario(ActionEvent event) {

    }

    @FXML
    void btnVoltarInicio(ActionEvent event) {
    	Main.changeScreen("menu");
    	System.out.println("Main Menu Scene Activated!");
    }

}
