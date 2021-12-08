package model;

import java.util.Arrays;

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
	protected String[] atraccionesPromo;

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

	public double calcularDuracion(Atraccion[] atraccionesEnPromocion) {
		for (int i = 0; i < atraccionesEnPromocion.length; i++) {
			this.tiempo += atraccionesEnPromocion[i].getTiempo();
		}
		return this.tiempo;
	}

	public Promocion(String nombrePromocion) {
		this.nombre = nombrePromocion;
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

	public void actualizarCupo(Atraccion[] atraccionesEnPromocion) {

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
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public int getIdAtraccion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actualizarCupo() {
		// TODO Auto-generated method stub

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

	@Override
	public boolean hayCupo() {
		// TODO Auto-generated method stub
		return false;
	}
}