package connection;
import java.sql.*;

import base.Usuario;

public class BancoDeDados {
	private static String url = "jdbc:mysql://localhost:3306/Biblioteca";
	String user;
	String pass;
	public static Connection conexao = null;
	
	public BancoDeDados(){
		
	}
	
	public BancoDeDados(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	
	public boolean conecta() {
		try {
			BancoDeDados.conexao = DriverManager.getConnection(url, user, pass);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean desconecta() {
		try {
			conexao.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static Connection getConexao() {
		return BancoDeDados.conexao;
	}
	
	public static void atualizarIDsUsuario() {
		try {
			Statement st = getConexao().createStatement();
			st.executeUpdate("SET @count = 0;");
			st.executeUpdate("UPDATE `usuarios` SET `usuarios`.`id` = @count:= @count + 1;");			
		}catch (SQLException e) {
		}
	}
	
	public static void atualizarIDsTitulos() {
		try {
			Statement st = getConexao().createStatement();
			st.executeUpdate("SET @count = 0;");
			st.executeUpdate("UPDATE `titulos` SET `titulos`.`id` = @count:= @count + 1;");			
		}catch (SQLException e) {
		}
	}
	
	public static void atualizarIDsEmprestimos() {
		try {
			Statement st = getConexao().createStatement();
			st.executeUpdate("SET @count = 0;");
			st.executeUpdate("UPDATE `emprestimos` SET `emprestimos`.`id` = @count:= @count + 1;");			
		}catch (SQLException e) {
		}
	}
	
	public static boolean adicionarUsuario(Usuario usuario) {
		try {
			Statement st = getConexao().createStatement();
			st.executeUpdate("INSERT INTO usuarios VALUES (NULL, '" + usuario.getNome() + "', '" + usuario.getCpf() + "', '" + usuario.getMatricula() + "', '" +  usuario.getCurso() + "')");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static boolean removerUsuario(int id) {
		try {
			Statement st = getConexao().createStatement();
			st.executeUpdate("DELETE FROM usuarios WHERE id =" + String.valueOf(id));
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static String mostrarUsuarios() {
		String result = "";
		try {
			Statement st = getConexao().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
			while(rs.next()) {
				result += (rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4) + " - " + rs.getString(5) + "\n" );
			}
		}catch (SQLException e) {
			result = "Falha no programa!";
		}
		return result;
	}
		
	
	
	public static boolean mudarNomeUsuario(int id, String nome) {
		try {
			Statement st = getConexao().createStatement();
			st.executeUpdate("UPDATE usuarios SET nome='" + nome + "' WHERE id=" + String.valueOf(id));
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static void adicionaTeste() {
		String query = "insert into usuarios (id, nome, cpf, matricula, curso) values (NULL, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(query);
			stmt.setString(1, "nome");	
			stmt.setString(2, "cpf");
			stmt.setString(3, "matricula");
			stmt.setString(4, "curso");
			stmt.execute();
		} catch (Exception e) {
			
		}
		
		
	}
	
}