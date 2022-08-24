package controller;

import java.net.URL;
import java.util.ResourceBundle;

import connection.BancoDeDados;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainMenuController implements Initializable {

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
    	Main.changeScreen("emprestimodevolucao");
    	System.out.println("Empréstimo e Devolução Scene Activated!");
    }

    @FXML
    void btnGerenciarTitulos(ActionEvent event) {
    	Main.changeScreen("titulos");
    	System.out.println("Títulos Scene Activated!");
    }

    @FXML
    void btnRelatorios(ActionEvent event) {
    	Main.changeScreen("relatorios");
    	System.out.println("Relatórios Scene Activated!");
    }

    @FXML
    void gerenciarUsuarios(ActionEvent event) {
    	Main.changeScreen("usuarios");
    	System.out.println("Usuarios Scene Activated!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	//Banco de dados interno precisa estar conectado
    	BancoDeDados bd = new BancoDeDados("admbiblioteca", "Biblioteca123");
    	bd.conecta();
    }


}