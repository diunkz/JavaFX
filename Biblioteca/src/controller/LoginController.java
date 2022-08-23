package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import connection.BancoDeDados;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private Button login;

    @FXML
    private PasswordField senha;
    
    @FXML
    private Label mensagemLogin;


    @FXML
    void btnLogin(ActionEvent event) {
    	if(!email.getText().isBlank() && !senha.getText().isBlank()) {
    		if(verificarLogin()) {
    			try {
    				mensagemLogin.setText("Login bem sucedido!");
    				Alert alert = new Alert(Alert.AlertType.INFORMATION);
    				alert.setTitle("Login");
    				alert.setHeaderText("Tudo certo!");
    				alert.setContentText("Login bem sucedido.");
    				
    				alert.showAndWait();
        			Main.changeScreen("menu");
    			}
    			catch(Exception e){
    				 System.out.println(e);
    			}
    			
    		}else {
    			mensagemLogin.setText("Usuário ou Senha Inválidos.");
    		}
    	}else {
    		mensagemLogin.setText("Insira os dados corretamente!");
    	}
    }
    
    public boolean verificarLogin() {
    	BancoDeDados bd = new BancoDeDados(email.getText(), senha.getText());
    	if(bd.conecta()) {
    		return true;
    	}else {
    		return false;
    	}
    }

}
