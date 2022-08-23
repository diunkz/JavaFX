package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

    @FXML
    private Button emprestimoeDevolucao;

    @FXML
    private Button gerenciarTitulos;

    @FXML
    private Button gerenciarUsuarios;

    @FXML
    private Button relatorios;

    @FXML
    void btnEmprestimoeDevolucao(ActionEvent event) {

    }

    @FXML
    void btnGerenciarTitulos(ActionEvent event) {
    	Main.changeScreen("titulos");
    	System.out.println("Titulos Scene Activated!");
    }

    @FXML
    void btnRelatorios(ActionEvent event) {

    }

    @FXML
    void gerenciarUsuarios(ActionEvent event) {
    	Main.changeScreen("usuarios");
    	System.out.println("Usuarios Scene Activated!");
    }

}