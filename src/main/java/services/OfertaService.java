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

	public Atraccion create(Integer id, String name, double cost, double duration, int capacity) throws SQLException {

		Atraccion attraction = new Atraccion(id, name, cost, duration, capacity);

		if (attraction.isValid()) {
			AtraccionDAO attractionDAO = new AtraccionDAO();
			attractionDAO.insert(attraction);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}

	public Atraccion update(int id, String name, double cost, double duration, int capacity) {
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		Atraccion attraction = atraccionDAO.find(id);

		attraction.setNombre(name);
		attraction.setCosto(cost);
		attraction.setTiempo(duration);
		attraction.setCupo(capacity);

		if (attraction.isValid()) {
			atraccionDAO.update(attraction);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}

	public void delete(int id) {
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		Atraccion attraction = new Atraccion(id, null, null, null, null);

		atraccionDAO.delete(attraction);
	}

	public Atraccion find(int id) {
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		return atraccionDAO.find(id);
	}

}
