package entidades;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	public ArrayList<Taco> listaTacos;
	
	public Menu() {
		
		listaTacos=new ArrayList<>();
	}
	public void agregarTaco(Taco t) {
		listaTacos.add(t);
		
	}
	public ArrayList<Taco> getListaTacos(){
		return listaTacos;
	}
}
