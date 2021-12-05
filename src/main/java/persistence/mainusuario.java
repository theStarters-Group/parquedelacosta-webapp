package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Atraccion;

public class mainusuario {

	public static void main(String[] args) throws SQLException {
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		List<Atraccion> atraccion = atraccionDAO.findAll();
//		for (Usuario usuario : usuarios) {
//			System.out.println("Pass: " + usuario.getDinero());
//		}
		System.out.println(atraccion);
	}
}
