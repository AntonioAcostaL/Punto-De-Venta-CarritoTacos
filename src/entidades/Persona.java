package entidades;

import java.io.Serializable;

public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String Nombre;
	protected String Telefono;
	private String Contraseña;
	
	public Persona(String Nombre,String Telefono, String Contraseña) {
		this.Nombre=Nombre;
		this.Telefono=Telefono;
		this.Contraseña=Contraseña;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		this.Telefono = telefono;
	}
	public String getContraseña() {
		return Contraseña;
	}
	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}
	
}
