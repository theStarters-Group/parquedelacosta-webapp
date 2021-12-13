package services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import model.Atraccion;
import model.Itinerario;
import model.Promocion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.PromocionDAO;
import persistence.UsuarioDAO;

public class BuyPromocionService {

	PromocionDAO promocionDAO = new PromocionDAO();
	UsuarioDAO userDAO = new UsuarioDAO();

	public Map<String, String> buy(int userId, int idPromo) throws SQLException {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario user = userDAO.find(userId);
		Promocion promocion = promocionDAO.find(idPromo);

		if (!promocion.canHost(1)) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.puedeComprar(promocion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(promocion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.addToItinerary(promocion);
			promocion.actualizarCupo();

			ItinerarioDAO itinerarioDAO = new ItinerarioDAO();
			Itinerario itinerario = new Itinerario(user.getIdUsuario(), promocion.getIdPromo(), 0);
			promocionDAO.update(promocion);
			userDAO.update(user);
			itinerarioDAO.insert(itinerario);
		}

		return errors;

	}

}
