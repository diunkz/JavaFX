package base;

public class Usuario {
	private Integer id;
	private String nome;
	private String cpf;
	private String matricula;
	private String curso;

	public Usuario() {
	}
	
	public Usuario(Integer id, String nome, String cpf, String matricula, String curso) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.curso = curso;
	}
	
	public Usuario(String nome, String cpf, String matricula, String curso) {
		this.id = 0;
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.curso = curso;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula= matricula;
	}
	
	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

}