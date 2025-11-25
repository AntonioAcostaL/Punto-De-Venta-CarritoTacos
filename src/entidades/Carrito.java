package entidades;

import java.util.ArrayList;

public class Carrito {
	private ArrayList<Taco> producto;
	private double total;
	
	public Carrito() {
		producto=new ArrayList<>();
		setTotal(0);
	}
	public void agregarTaco(Taco taco) {
		producto.add(taco);
		setTotal(getTotal() + taco.getPrecio());
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public ArrayList<Taco> getProducto(){
		return producto;
	}
	
	
	
}
