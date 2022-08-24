package connection;

import base.Usuario;

//import base.Usuario;

public class TestandoConexao {
	String user = "admbiblioteca";
	String pass = "Biblioteca123";
	
	TestandoConexao(){
		
	}
	
	public boolean verificarLogin() {
    	BancoDeDados bd = new BancoDeDados(user, pass);
    	if(bd.conecta()) {
    		return true;
    	}else {
    		return false;
    	}
    }
	
	public static void main(String[] args) {
		Usuario user = new Usuario("Noah", "98398946253", "21753511", "Ciência da Computação");
		TestandoConexao x = new TestandoConexao();
		x.verificarLogin();
		System.out.println(BancoDeDados.getConexao());
//		BancoDeDados.atualizarIDsUsuario();
//		BancoDeDados.adicionaTeste();
		BancoDeDados.adicionarUsuario(user);
	}
}