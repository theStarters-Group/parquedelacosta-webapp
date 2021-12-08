package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import persistence.commons.ConnectionProvider;
import model.Atraccion;
import model.Promocion;

public class PromocionDAO {
	public List<Promocion> findAll(List<Atraccion> atracciones) throws SQLException {
		String sql = "SELECT promociones.*, group_concat(ap.id_atraccion, ' ') AS lista_atracciones\r\n"
				+ "FROM promociones\r\n" + "join atracciones_promo ap on ap.id_promocion = promociones.id\r\n"
				+ "GROUP BY promociones.id";

		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Promocion> promociones = new LinkedList<Promocion>();
		while (resultados.next()) {
			promociones.add(toPromocion(resultados, atracciones));
		}

		return promociones;
	}

	public List<String> findAtracciones() throws SQLException {
		String sql = "SELECT  group_concat(ap.id_atraccion, ' ') AS lista_atracciones\r\n" + "FROM promociones\r\n"
				+ "join atracciones_promo ap on ap.id_promocion = promociones.id\r\n" + "GROUP BY promociones.id";

		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<String> Lista_atracciones = new LinkedList<String>();
		while (resultados.next()) {
			Lista_atracciones.add(toString(resultados));
		}

		return Lista_atracciones;
	}

	private String toString(ResultSet resultados) throws SQLException {
		return new String(resultados.getString(1));
	}

	public int countAll() throws SQLException {
		String sql = "SELECT COUNT(1) AS TOTAL FROM promociones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("TOTAL");

		return total;
	}

	private Promocion toPromocion(ResultSet resultados, List<Atraccion> atracciones) throws SQLException {
		String[] s = resultados.getString(6).split(" ");
		Atraccion[] atraccionesEnPromo = new Atraccion[s.length];

		for (int i = 0; i < s.length; i++) {

			for (Atraccion atraccion : atracciones) {
				if (atraccion.getIdAtraccion() == Integer.parseInt(s[i])) {
					atraccionesEnPromo[i] = atraccion;
				}
			}
		}

		return new Promocion(resultados.getInt(1), resultados.getString(2), resultados.getInt(3), resultados.getInt(4),
				resultados.getDouble(5), atraccionesEnPromo);
	}

	public Promocion find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Promocion promocion) {
		// TODO Auto-generated method stub
		
	}

	public void update(Promocion promocion) {
		// TODO Auto-generated method stub
		
	}

	public void insert(Promocion promocion) {
		// TODO Auto-generated method stub
		
	}
}
