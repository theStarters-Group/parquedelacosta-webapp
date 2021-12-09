package services;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Atraccion;
import model.Ofertable;
import model.Promocion;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;

public class OfertaService {

	public List<Ofertable> list() throws SQLException {
		List<Ofertable> ofertas = new LinkedList<Ofertable>();
		PromocionDAO promocionDAO = new PromocionDAO();
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.findAll();
		List<Promocion> promociones = promocionDAO.findAll(atracciones);

		ofertas.addAll(promociones);
		ofertas.addAll(atracciones);
		return ofertas;
	}

}