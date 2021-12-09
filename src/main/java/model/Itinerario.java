package model;

public class Itinerario {
	private int idUsuario;
	private int idPromocion;
	private int idAtraccion;

	public Itinerario(int idUsuario, int idPromocion, int idAtraccion) {
		super();
		this.idUsuario = idUsuario;
		this.idPromocion = idPromocion;
		this.idAtraccion = idAtraccion;
	}

	public Itinerario() {

	}

	public Itinerario(int idUsuario, int idPromo) {
		super();
		this.idPromocion=idPromo;
		this.idUsuario=idUsuario;
	}
	public Itinerario(Integer idUsuario, int idAtraccion) {
		super();
		this.idUsuario = idUsuario;
		this.idAtraccion = idAtraccion;
	}


	public int getIdUsuario() {
		return idUsuario;
	}

	public  int getIdPromocion() {
		return idPromocion;
	}

	public  int getIdAtraccion() {
		return idAtraccion;
	}

	@Override
	public String toString() {
		return "Itinerario [idUsuario=" + idUsuario + ", idPromocion=" + getIdPromocion() + ", idAtraccion="
				+ idAtraccion + "]";
	}

}
