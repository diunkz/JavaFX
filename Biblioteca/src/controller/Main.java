package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;

public class Main extends Application {
	
	private static Stage stage;
	
	private static Scene Login;
	private static Scene MainMenu;
	private static Scene Usuarios;
	private static Scene Titulos;
	private static Scene EmprestimoEDevolucao;
	private static Scene Relatorios;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	stage = primaryStage;
    	
    	Parent fxmlLogin = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        Login = new Scene(fxmlLogin);
        
        Parent fxmlMainMenu = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        MainMenu = new Scene(fxmlMainMenu);
        
        Parent fxmlUsuarios = FXMLLoader.load(getClass().getResource("../view/Usuarios.fxml"));
        Usuarios = new Scene(fxmlUsuarios);
        
        Parent fxmlTitulos = FXMLLoader.load(getClass().getResource("../view/Titulos.fxml"));
        Titulos = new Scene(fxmlTitulos);
        
        Parent fxmlEmprestimoEDevolucao = FXMLLoader.load(getClass().getResource("../view/EmprestimoEDevolucao.fxml"));
        EmprestimoEDevolucao = new Scene(fxmlEmprestimoEDevolucao);
        
        Parent fxmlRelatorios = FXMLLoader.load(getClass().getResource("../view/Relatorios.fxml"));
        Relatorios = new Scene(fxmlRelatorios);

//      primaryStage.initStyle(StageStyle.UNDECORATED)
        primaryStage.setScene(Login);
        primaryStage.show();
    	
    }
    
    public static void changeScreen(String scr) {
    	switch(scr) {
	    	case "menu":
	    		stage.setScene(MainMenu);
	    		break;
	    	case "usuarios":
	    		stage.setScene(Usuarios);
	    		break;
	    	case "titulos":
	    		stage.setScene(Titulos);
	    		break;
	    	case "emprestimodevolucao":
	    		stage.setScene(EmprestimoEDevolucao);
	    		break;
	    	case "relatorios":
	    		stage.setScene(Relatorios);
	    		break;
    	}
    }

    public static void main(String[] args) {
        launch(args);    	
        new UsuariosController();
        new TitulosController();
        new RelatoriosController();
    }
}