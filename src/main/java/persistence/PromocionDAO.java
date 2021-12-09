package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;
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

	private Promocion toPromocion2(ResultSet resultados) throws SQLException {
		String[] s = resultados.getString(6).split(" ");
		Atraccion[] atraccionesEnPromo = new Atraccion[s.length];
		AtraccionDAO atraccionDAO=new AtraccionDAO();
		List<Atraccion> atracciones= atraccionDAO.findAll();
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
		try {
			String sql = "SELECT * FROM promociones WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;
			if (resultados.next()) {
				promocion = toPromocion2(resultados);
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int habilite(Promocion promocion) {
		try {
			String sql = "UPDATE promociones SET deshabilitado=0 WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getIdPromo());
			int rows = statement.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Promocion promocion) {
		try {
			String sql = "UPDATE promociones SET deshabilitado=1 WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getIdPromo());
			int rows = statement.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Promocion promocion) {
		try {
			String sql = "UPDATE promociones SET nombre = ?, datoExtra = ?, id_tipo_promocion = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, promocion.getNombre());
			statement.setInt(i++, promocion.getTipoPromocion());
			statement.setInt(i++, promocion.getIdPromo());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public int insert(Promocion promocion) throws SQLException {
		String sql = "INSERT INTO atracciones (nombre, precio, duracion, cupo, id_tipo_promocion) VALUES ( ?, ?, ?, ?, ?)";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, promocion.getNombre());
		statement.setDouble(2, promocion.getCosto());
		statement.setDouble(3, promocion.getTiempo());
		statement.setDouble(4, promocion.getCupo());
		statement.setDouble(5, promocion.getTipoPromocion());

		int rows = statement.executeUpdate();
		ResultSet rs = statement.getGeneratedKeys();
		rs.next();
		promocion.setId(rs.getInt(1));

		return rows;

	}
}
