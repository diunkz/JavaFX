package base;

public class Emprestimo {
	Integer id;
	String nome;
	String cpf;
	String titulo;
	Integer quantidade;
	
	public Emprestimo(){
	}
	
	public Emprestimo(Integer id, String nome, String cpf, String titulo, Integer quantidade) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.titulo = titulo;
		this.quantidade = quantidade;
	}
	
	public Emprestimo(String nome, String cpf, String titulo, Integer quantidade) {
		this.id = 0;
		this.nome = nome;
		this.cpf = cpf;
		this.titulo = titulo;
		this.quantidade = quantidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
}
