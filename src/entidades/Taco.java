package entidades;

import java.io.Serializable;

public class Taco implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String tipo;
	public double precio;
	private String nombre;
	private String descripcion;
	
	public Taco(String tipo,double precio,String nombre,String descripcion) {
		this.tipo=tipo;
		this.precio=precio;
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
	}
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setPrecio(double precio) {
		this.precio=precio;
	}
	public double getPrecio() {
		return precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
