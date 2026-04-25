package views;

import java.util.Scanner;
import java.util.List;
import dao.ClienteDAO;
import models.Cliente;

public class ClienteView {
    private Scanner sc = new Scanner(System.in);
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    public ClienteView() {}
    
    public void iniciar() {
        int opcion;
        do {
            opcion = this.mostrarMenu();
            switch(opcion) {
                case 1 -> {
                    this.listarClientes();
                }
                case 2 -> {
                    this.addCliente();
                }
            }           
        } while (opcion != 5);
    }
    
    private void addCliente() {
        System.out.println("Introduce un nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce un email: ");
        String email = sc.nextLine();
        System.out.println("Introduce un teléfono: ");
        String telefono = sc.nextLine();
        
        Cliente c = new Cliente(nombre, email, telefono);
                
        if(this.clienteDAO.add(c)) {
            System.out.println("Cliente Insertado correctamente");
        } else {
            System.out.println("Ha ocurrido un error!");
        }
    }

    private void listarClientes() {
        List<Cliente> clientes = this.clienteDAO.getAll();

        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    private int mostrarMenu() {
        System.out.println("Gestión de clientes");
        System.out.println("1. Ver todos los clientes");
        System.out.println("2. Añadir cliente");
        System.out.println("3. Actualizar cliente");
        System.out.println("4. Borrar cliente");
        System.out.println("5. Salir");     
        int opcion = sc.nextInt();
        sc.nextLine();
        
        return opcion;
    }
}