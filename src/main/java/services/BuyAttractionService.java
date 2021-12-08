package services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import model.Atraccion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.UsuarioDAO;

public class BuyAttractionService {

	AtraccionDAO attractionDAO = new AtraccionDAO();
	UsuarioDAO userDAO = new UsuarioDAO();

	public Map<String, String> buy(int userId, int attractionId) throws SQLException {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario user = userDAO.find(userId);
		Atraccion attraction = attractionDAO.find(attractionId);

		if (!attraction.hayCupo(1)) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.canAfford(attraction)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(attraction)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.addToItinerary(attraction);
			attraction.actualizarCupo(1);

			// no grabamos para no afectar la base de pruebas
//			attractionDAO.update(attraction);
//			userDAO.update(user);
		}

		return errors;

	}

}
