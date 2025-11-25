package entidades;

public class Empleado extends Persona{
	//public String Nombre;
	private String idEmpleado;
	
	public Empleado(String Nombre,String Telefono,String Contraseña,String idEmpleado) {
		super(Nombre,Telefono,Contraseña);
		this.setIdEmpleado(idEmpleado);
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
}
