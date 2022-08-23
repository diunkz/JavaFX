package base;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class UsuarioProperty {
	
	private final IntegerProperty id;
	private final StringProperty nome;
	private final StringProperty cpf;
	private final StringProperty matricula;
	private final StringProperty curso;
	
	public UsuarioProperty() {
		id = new SimpleIntegerProperty(this, "id");
		nome = new SimpleStringProperty(this, "nome");
		cpf = new SimpleStringProperty(this, "cpf");
		matricula = new SimpleStringProperty(this, "matricula");
		curso = new SimpleStringProperty(this, "curso");
	}
	
	public IntegerProperty idProperty( ) {
		return id;
	}
	
	public Integer getId() {
		return id.get();
	}
	
	public void setId(Integer newId) {
		id.set(newId);
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public String getNome() {
		return nome.get();
	}
	
	public void setNome (String newNome) {
		nome.set(newNome);
	}
	
	public StringProperty cpfProperty() {
		return cpf;
	}
	
	public String getCpf() {
		return cpf.get();
	}
	
	public void setCpf (String newCpf) {
		cpf.set(newCpf);
	}
	
	public StringProperty matriculaProperty() {
		return matricula;
	}
	
	public String getMatricula() {
		return matricula.get();
	}
	
	public void setMatricula(String newMatricula) {
		matricula.set(newMatricula);
	}
	
	public StringProperty cursoProperty() {
		return curso;
	}
	
	public String getCurso() {
		return curso.get();
	}
	
	public void setCurso (String newCurso) {
		curso.set(newCurso);
	}
}
