package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class GestorCarrito {
    private Menu menu;
 
    public static ArrayList<Pedido> pedidos = new ArrayList<>();
    // NUEVO: listas de usuarios
    public static ArrayList<Cliente> clientes = new ArrayList<>();
    public static ArrayList<Empleado> empleados = new ArrayList<>();
    // MENÚ DE TACOS
    public static ArrayList<Taco> menuTacos = new ArrayList<>();

    public static Map<String, Pedido> pedidosPorCodigo = new HashMap<>();

    
    public GestorCarrito() {}
    private static final String archivo_C = "clientes.dat";
    private static final String ARCHIVO_PEDIDOS = "pedidos.dat";
    private static final String ARCHIVO_TACOS = "tacos.dat";
    
    public static void datosPrueba() {
        // Solo crear cliente de prueba si la lista está vacía
        if (clientes.isEmpty()) {
            clientes.add(new Cliente("cliente1", "1234567890", "1234"));
        }

        // Asegurar que siempre exista el admin
        if (empleados.isEmpty()) {
            empleados.add(new Empleado("admin", "0000000000", "admin123", "ADM001"));
        }
    }

    public static void datosPruebaTacos() {
        if (menuTacos.isEmpty()) {
            menuTacos.add(new Taco("Mariscos", 30, "Taco de Camarón", "Taco con camarón y vegetales"));
            menuTacos.add(new Taco("Mariscos", 35, "Taco de Pulpo", "Taco de pulpo a la plancha"));
            menuTacos.add(new Taco("Mariscos", 40, "Taco Mixto", "Combinación de mariscos"));
            guardarTacos(); // para que se guarden en tacos.dat
        }
    }

    // búsqueda de cliente stream
    public static Cliente buscarCliente(String nombre, String contraseña) {
        return clientes.stream()
                .filter(c -> c.getNombre().equals(nombre) &&
                             c.getContraseña().equals(contraseña))
                .findFirst()
                .orElse(null);
    }



    // búsqueda de empleado stream
    public static Empleado buscarEmpleado(String nombre, String contraseña) {
        return empleados.stream()
                .filter(e -> e.getNombre().equals(nombre) &&
                             e.getContraseña().equals(contraseña))
                .findFirst()
                .orElse(null);
    }


    public static void registrarCliente(Cliente c) {
        clientes.add(c);
        guardarClientes();   // guarda automáticamente después de registrar
    }

    
    

    // Getters y setters existentes
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public static void guardarClientes() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo_C))) {

            // 1) Guardamos cuántos clientes hay
            dos.writeInt(clientes.size());

            // 2) Escribimos los campos básicos de cada cliente
            for (Cliente c : clientes) {
                dos.writeUTF(c.getNombre());
                dos.writeUTF(c.getTelefono());
                dos.writeUTF(c.getContraseña());  // asegúrate de tener este getter
            }

            System.out.println("Clientes guardados en " + archivo_C);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void guardarPedidos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PEDIDOS))) {
            oos.writeObject(pedidos);
            System.out.println("Pedidos guardados.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void cargarClientes() {
        clientes.clear();  // limpiamos la lista en memoria

        File f = new File(archivo_C);
        if (!f.exists()) {
            System.out.println("No existe " + archivo_C + ", aún no hay clientes guardados.");
            return;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {

            int n = dis.readInt();  // cuántos clientes hay

            for (int i = 0; i < n; i++) {
                String nombre = dis.readUTF();
                String tel    = dis.readUTF();
                String pass   = dis.readUTF();

                clientes.add(new Cliente(nombre, tel, pass));
            }

            System.out.println("Se cargaron " + clientes.size() + " clientes desde " + archivo_C);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public static void cargarPedidos() {
        File f = new File(ARCHIVO_PEDIDOS);
        if (!f.exists()) {
            System.out.println("No hay pedidos guardados aún.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PEDIDOS))) {
            pedidos = (ArrayList<Pedido>) ois.readObject();
            System.out.println("Pedidos cargados: " + pedidos.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // logica tacos----
    public static void agregarTaco(Taco t) {
        menuTacos.add(t);
        guardarTacos();
    }

    public static void eliminarTaco(Taco t) {
        menuTacos.remove(t);
        guardarTacos();
    }
    
    public static void guardarTacos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_TACOS))) {
            oos.writeObject(menuTacos);
            System.out.println("Tacos guardados: " + menuTacos.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void cargarTacos() {
        File f = new File(ARCHIVO_TACOS);
        if (!f.exists()) {
            System.out.println("No hay archivo de tacos todavía.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            menuTacos = (ArrayList<Taco>) ois.readObject();
            System.out.println("Tacos cargados: " + menuTacos.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}