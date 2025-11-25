package entidades;

import java.io.Serializable;

public class TacoPedido implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Taco taco;     // producto del menú
    private int cantidad;  // cuántos pidió
    private double subtotal;

    public TacoPedido(Taco taco, int cantidad) {
        this.taco = taco;
        this.cantidad = cantidad;
        this.subtotal = taco.getPrecio() * cantidad;
    }

    public Taco getTaco() {
        return taco;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }
}

