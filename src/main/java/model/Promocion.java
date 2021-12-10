package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Promocion extends Ofertable {

	protected int tipoPromo;
	protected int tipoAtraccion;
	protected String nombre;
	protected Atraccion[] atraccionesEnPromocion;
	protected double datoExtra;
	protected String atracciones;
	protected double costo;
	protected int id;
	protected int cupo;
	protected double tiempo;
	protected boolean estado;
	protected String imagen;
	protected String[] atraccionesPromo;
	private Map<String, String> errors;

	public Promocion(int idPromo, String nombrePromocion, int tipoPromo, int tipoAtraccion, double datoExtra,
			Atraccion[] atraccionesEnPromocion) {
		super();
		this.tipoPromo = tipoPromo;
		this.id = idPromo;
		this.nombre = nombrePromocion;
		this.atraccionesEnPromocion = atraccionesEnPromocion;
		this.tipoAtraccion = tipoAtraccion;
		this.costo = this.calcularCosto(datoExtra);
	}

	public Promocion(String nombrePromocion, int tipoPromo, int tipoAtraccion, double datoExtra,
			Atraccion[] atraccionesEnPromocion) {
		super();
		this.tipoPromo = tipoPromo;
		this.nombre = nombrePromocion;
		this.atraccionesEnPromocion = atraccionesEnPromocion;
		this.tipoAtraccion = tipoAtraccion;
		this.costo = this.calcularCosto(datoExtra);
	}

	public Promocion(int id, Object object, Object object2, Object object3, Object object4) {
		this.id = id;

	}

	public Promocion(int idPromo, String nombrePromocion, int tipoPromo, int tipoAtraccion, double datoExtra,
			Atraccion[] atraccionesEnPromocion, boolean estado) {
		super();
		this.tipoPromo = tipoPromo;
		this.id = idPromo;
		this.nombre = nombrePromocion;
		this.atraccionesEnPromocion = atraccionesEnPromocion;
		this.tipoAtraccion = tipoAtraccion;
		this.costo = this.calcularCosto(datoExtra);
		this.estado = estado;
	}

	public double calcularDuracion(Atraccion[] atraccionesEnPromocion) {
		for (int i = 0; i < atraccionesEnPromocion.length; i++) {
			this.tiempo += atraccionesEnPromocion[i].getTiempo();
		}
		return this.tiempo;
	}

	public Promocion(String nombrePromocion) {
		this.nombre = nombrePromocion;
	}

	public Promocion(int idPromo, String nombrePromocion, int tipoPromo, int tipoAtraccion, double datoExtra,
			boolean estado, Atraccion[] atraccionesEnPromocion) {
		super();
		this.tipoPromo = tipoPromo;
		this.id = idPromo;
		this.nombre = nombrePromocion;
		this.atraccionesEnPromocion = atraccionesEnPromocion;
		this.tipoAtraccion = tipoAtraccion;
		this.costo = this.calcularCosto(datoExtra);
		this.estado = estado;
	}

	public double calcularCosto(double datoExtra) {

		switch (tipoPromo) {

		case 1:
			this.costo = datoExtra;
			break;
		case 2:
			this.costo = sumaPrecio(getAtraccionesEnPromocion()) * (1 - datoExtra);
			break;
		case 3:

			this.costo = sumaPrecio(getAtraccionesEnPromocion());

			break;
		}

		return costo;
	}

	public boolean esPromocion() {
		return this instanceof Promocion;

	}

	public double sumaPrecio(Atraccion[] atraccionesPromo) {
		double precioTotal = 0;

		for (int i = 0; i < atraccionesEnPromocion.length; i++) {
			precioTotal += atraccionesEnPromocion[i].getCosto();
		}
		return precioTotal;
	}

	public double calcularTiempo(Atraccion[] atraccionesEnPromocion) {
		double tiempoTotal = 0;
		for (int i = 0; i < atraccionesEnPromocion.length; i++) {
			tiempoTotal += atraccionesEnPromocion[i].getTiempo();
		}
		return tiempoTotal;
	}

	public int calcularCupo(Atraccion[] atraccionesEnPromocion) {
		int cupo = 0;
		for (int i = 0; i < atraccionesEnPromocion.length; i++) {
			cupo += atraccionesEnPromocion[i].getCupo();
		}
		return cupo;
	}

	public void actualizarCupo() {
		Atraccion[] atraccionesEnPromocion = this.atraccionesEnPromocion;
		for (int i = 0; i < atraccionesEnPromocion.length; i++) {
			atraccionesEnPromocion[i].actualizarCupo();
		}
	}

	public void imprimirPromociones(Promocion[] promociones) {
		for (int i = 0; i < promociones.length; i++) {
			this.toString();
		}
	}

	@Override
	public double getTiempo() {
		return calcularDuracion(atraccionesEnPromocion);
	}

	public int getTipoAtraccion() {
		return this.tipoAtraccion;
	}

	public boolean canHost(int x) {
		int i = 0;
		boolean hayCupo = true;
		while (i < this.getAtraccionesEnPromocion().length) {
			if (getAtraccionesEnPromocion()[i].canHost(x))
				i++;
			else {
				hayCupo = false;
				break;
			}
		}
		return hayCupo;
	}

	public int getCupo() {

		return this.calcularCupo(this.atraccionesEnPromocion);

	}

	public String getNombre() {
		return this.nombre;
	}

	public String getImagen() {
		return this.imagen;
	}

	public double getCosto() {
		return this.costo;
	}

	public Atraccion[] getAtraccionesEnPromocion() {
		return this.atraccionesEnPromocion;
	}

	public String getAtracciones() {
		return this.atracciones;
	}

	@Override
	public int getIdPromo() {
		return this.id;
	}

	@Override
	public int getIdAtraccion() {
		return 0;
	}

	public void setTipoPromo(int tipoPromo) {
		this.tipoPromo = tipoPromo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDatoExtra(double datoExtra) {
		this.datoExtra = datoExtra;
	}

	public void host(int i) {
		this.cupo -= i;
	}

	@Override
	public String toString() {
		return "Promocion [idPromo=" + id + ", tipoPromo=" + tipoPromo + ", nombrePromocion=" + nombre
				+ ", atraccionesEnPromocion=" + Arrays.toString(atraccionesEnPromocion) + ", costo=" + costo + ", cupo="
				+ cupo + ", datoExtra=" + datoExtra + "]";
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (nombre == null) {
			errors.put("Nombre", "No puede ser nulo");
		}
		if (1 <= tipoPromo && tipoPromo <= 4) {
			errors.put("Tipo de promoción", "Debe ser un número entre 1 y 4");
		}
		if (tipoAtraccion <= 0) {
			errors.put("Tipo de Atracción", "Debe ser un número entre 1 y 4");
		}
		if (datoExtra == 0) {
			errors.put("Tipo de descuento", "No puede ser 0");
		}
		if (atraccionesEnPromocion.length == 0) {
			errors.put("Atracciones en promoción", "No pueden ser 0");
		}

	}

	public int getTipoPromocion() {
		return this.tipoPromo;
	}

	public void setId(int id) {
		this.id = id;

	}

	@Override
	public boolean hayCupo() {
		return false;
	}

	public boolean estaDeshabilitado() {
		return estado;
	}
}