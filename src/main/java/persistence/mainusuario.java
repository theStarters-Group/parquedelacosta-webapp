package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Atraccion;
import model.Promocion;

public class mainusuario {

	public static void main(String[] args) throws SQLException {
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.findAll();
		PromocionDAO promocionDAO = new PromocionDAO();
		List<Promocion> promociones = promocionDAO.findAll(atracciones);
//		for (Usuario usuario : usuarios) {
//			System.out.println("Pass: " + usuario.getDinero());
//		}
		System.out.println(promociones);
	}
}
