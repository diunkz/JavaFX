package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RelatoriosController {

    @FXML
    private Button btnInicio;

    @FXML
    private TableColumn<?, ?> colunaAno;

    @FXML
    private TableColumn<?, ?> colunaAutor;

    @FXML
    private TableColumn<?, ?> colunaCpf;

    @FXML
    private TableColumn<?, ?> colunaCurso;

    @FXML
    private TableColumn<?, ?> colunaDescricao;

    @FXML
    private TableColumn<?, ?> colunaEditora;

    @FXML
    private TableColumn<?, ?> colunaIdEmprestimos;

    @FXML
    private TableColumn<?, ?> colunaIdTitulos;

    @FXML
    private TableColumn<?, ?> colunaIdUsuarios;

    @FXML
    private TableColumn<?, ?> colunaMatricula;

    @FXML
    private TableColumn<?, ?> colunaNome;

    @FXML
    private TableColumn<?, ?> colunaNomeEmprestimos;

    @FXML
    private TableColumn<?, ?> colunaQuantidade;

    @FXML
    private TableColumn<?, ?> colunaQuantidadeEmprestimos;

    @FXML
    private TableColumn<?, ?> colunaTitulo;

    @FXML
    private TableColumn<?, ?> colunaTituloEmprestimos;

    @FXML
    private TableColumn<?, ?> colunacoCpfEmprestimos;

    @FXML
    private TableView<?> tableEmprestimos;

    @FXML
    private TableView<?> tableTitulos;

    @FXML
    private TableView<?> tableUsuarios;

    @FXML
    void btnVoltarInicio(ActionEvent event) {
    	Main.changeScreen("menu");
    	System.out.println("Main Menu Scene Activated!");
    }

}
