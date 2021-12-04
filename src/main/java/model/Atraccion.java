package model;

import java.util.HashMap;
import java.util.Map;

public class Atraccion extends Ofertable {
	private int idAtraccion;
	private String nombre;
	private double costo;
	private double tiempo;
	protected int cupo;
	private int tipo;
	private Map<String, String> errors;

	public Atraccion(int id, Object object, Object object2, Object object3, Object object4) {
		this.idAtraccion = id;

	}

	public Atraccion(int idAtraccion, String nombre, double costo, double tiempo, int cupo, int tipo) {
		this.idAtraccion = idAtraccion;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo = tipo;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (costo <= 0) {
			errors.put("costo", "Debe ser positivo");
		}
		if (tiempo <= 0) {
			errors.put("duracion", "Debe ser positivo");
		}
		if (cupo <= 0) {
			errors.put("cupo", "Debe ser positivo");
		}
	}

	@Override
	public String toString() {
		return this.nombre + ", Duraci�n: " + this.tiempo + "hs, Cupo m�ximo: " + this.cupo + ", Tipo: " + this.tipo
				+ ", Costo Original: $" + this.costo + ".\n";
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getCupo() {
		return this.cupo;
	}

	public double getTiempo() {
		return tiempo;
	}

	public double getCosto() {
		return this.costo;
	}

	@Override
	public int getTipoAtraccion() {

		return tipo;
	}

	@Override
	public void actualizarCupo() {
		this.cupo -= 1;
	}

	public void actualizarCupo(int i) {
		this.cupo -= i;
	}

	@Override
	public boolean hayCupo() {
		return this.cupo > 0;
	}

	public boolean hayCupo(int i) {
		return cupo >= i;
	}

	@Override
	public int getIdPromo() {

		return 0;
	}

	@Override
	public int getIdAtraccion() {

		return idAtraccion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	@Override
	public double calcularCosto(double datoExtra) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actualizarCupo(Atraccion[] atraccionesEnPromocion) {
		// TODO Auto-generated method stub

	}

}