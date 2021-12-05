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
import model.Ofertable;

public class AtraccionDAO {

	public int insert(Atraccion atraccion) throws SQLException {
		String sql = "INSERT INTO atracciones (id, nombre, precio, duracion, cupo, id_tipo_atraccion) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, atraccion.getIdAtraccion());
		statement.setString(2, atraccion.getNombre());
		statement.setDouble(3, atraccion.getCosto());
		statement.setDouble(4, atraccion.getTiempo());
		statement.setDouble(5, atraccion.getCupo());
		statement.setDouble(6, atraccion.getTipoAtraccion());

		int rows = statement.executeUpdate();

		return rows;
	}

	public int updateCupo(Ofertable oferta) throws SQLException {
		String sql = "UPDATE atracciones SET cupo = ? WHERE id = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setDouble(1, oferta.getCupo());
		statement.setInt(2, oferta.getIdAtraccion());
		int rows = statement.executeUpdate();

		return rows;
	}

	public int update(Atraccion attraction) {
		try {
			String sql = "UPDATE atracciones SET nombre = ?, precio = ?, duracion = ?, cupo = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, attraction.getNombre());
			statement.setDouble(i++, attraction.getCosto());
			statement.setDouble(i++, attraction.getTiempo());
			statement.setInt(i++, attraction.getCupo());
			statement.setInt(i++, attraction.getIdAtraccion());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Atraccion attraction) {
		try {
			String sql = "DELETE FROM atracciones WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, attraction.getIdAtraccion());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public static int countAll() throws SQLException {
		String sql = "SELECT COUNT(1) AS TOTAL FROM atracciones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("TOTAL");

		return total;
	}

	// public int countAll() throws SQLException {
	// String sql = "SELECT COUNT(1) AS TOTAL FROM USERS";
	// Connection conn = ConnectionProvider.getConnection();
	// PreparedStatement statement = conn.prepareStatement(sql);
	// ResultSet resultados = statement.executeQuery();

	// resultados.next();
	// int total = resultados.getInt("TOTAL");

	// return total;
	// }

	public List<Atraccion> findAll() throws SQLException {
		String sql = "SELECT * FROM atracciones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		while (resultados.next()) {
			atracciones.add(toAtraccion(resultados));
		}

		return atracciones;
	}

	public static Atraccion getAtraccionPorId(int id) throws SQLException {
		Atraccion atraccion = null;
		String sql = "SELECT * FROM ATRACCIONES WHERE ID = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultados = statement.executeQuery();

		if (resultados.next()) {
			atraccion = toAtraccion(resultados);
		}

		return atraccion;
	}

	public Atraccion find(int id) {
		try {
			String sql = "SELECT * FROM atracciones WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultados = statement.executeQuery();

			Atraccion attraction = null;
			if (resultados.next()) {
				attraction = toAtraccion(resultados);
			}

			return attraction;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private static Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getInt(1), resultados.getString(2), resultados.getDouble(3),
				resultados.getDouble(4), resultados.getInt(5), resultados.getInt(6));
	}
}
