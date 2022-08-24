package base;

public class Titulo {
	Integer id;
	String titulo;
	String editora;
	String autor;
	String ano;
	String descricao;
	Integer quantidade;

	public Titulo() {
	}

	public Titulo(Integer id, String titulo, String editora, String autor, String ano, String descricao, Integer quantidade) {
		this.id = id;
		this.titulo = titulo;
		this.editora = editora;
		this.autor = autor;
		this.ano = ano;
		this.descricao = descricao;
		this.quantidade = quantidade;
		
	}
	
	public Titulo(String titulo, String editora, String autor, String ano, String descricao, Integer quantidade) {
		this.id = 0;
		this.titulo = titulo;
		this.editora = editora;
		this.autor = autor;
		this.ano = ano;
		this.descricao = descricao;
		this.quantidade = quantidade;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return this.editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAno() {
		return this.ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}