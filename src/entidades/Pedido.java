package entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idPedido;
    private Cliente cliente;
    private ArrayList<TacoPedido> tacos; 
    private double total;
    private String bebida;       // NUEVO
    private double extraBebida;
    private double totalFinal;
    private String codigoPago;   // código que se muestra al cliente
    
    public Pedido(String idPedido, Cliente cliente, ArrayList<TacoPedido> tacos,String bebida,double extraBebida) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.tacos = tacos;
        this.bebida = bebida;
        this.extraBebida = extraBebida;
        this.totalFinal = total + extraBebida;
        // calcular total automáticamente
        double suma = 0;
        for (TacoPedido tp : tacos) {
            suma += tp.getSubtotal();
        }
        this.total = suma;
        this.totalFinal = suma + extraBebida;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<TacoPedido> getTacos() {
        return tacos;
    }

    public String getBebida() { return bebida; }
    public double getExtraBebida() { return extraBebida; }

    public double getTotal() { return total; }
    public double getTotalFinal() { return totalFinal; }
    
    public String getCodigoPago() { return codigoPago; }
    public void setCodigoPago(String codigoPago) { this.codigoPago = codigoPago; }
    
    
    
}

	

