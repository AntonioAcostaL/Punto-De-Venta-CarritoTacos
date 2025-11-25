package entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Pedido> pedidos;
	
	public Cliente(String Nombre,String Telefono,String Contraseña) {
		super(Nombre,Telefono,Contraseña);
		pedidos=new ArrayList<>();
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public void agregarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

}
