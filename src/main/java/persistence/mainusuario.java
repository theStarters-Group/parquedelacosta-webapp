package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Usuario;

public class mainusuario {

	public static void main(String[] args) throws SQLException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.findAll();
//		for (Usuario usuario : usuarios) {
//			System.out.println("Pass: " + usuario.getDinero());
//		}
		System.out.println(usuarios);
	}
}
