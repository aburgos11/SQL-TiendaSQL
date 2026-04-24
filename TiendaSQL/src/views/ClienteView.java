package views;

import dao.ClienteDAO;
import java.util.Scanner;

public class ClienteView {
    private Scanner sc = new Scanner(System.in);
	private ClienteDAO clienteDAO = new ClienteDAO();

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
				case 3 -> {
					this.modificarCliente();
				}
				case 4 -> {
					this.eliminarCliente();
				}
			}			
		} while (opcion != 5);

    }



    
    private int mostrarMenu() {
        System.out.println("-- Gestión de Clientes --");
		System.out.println("1. Ver todos los clientes");
		System.out.println("2. Añadir cliente");
		System.out.println("3. Modificar cliente");
		System.out.println("4. Eliminar cliente");
		System.out.println("5. Salir");		
		int opcion = sc.nextInt();
		sc.nextLine();
		
		return opcion;
	}


    private void listarClientes() {
    
    }


    private void addCliente() {
    
    }

    
    private void modificarCliente() {
    
    }


    private void eliminarCliente() {
    
    }
    
    

    
}
