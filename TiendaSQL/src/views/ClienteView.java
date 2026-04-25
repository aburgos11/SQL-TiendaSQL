package views;

import dao.ClienteDAO;
import java.util.List;
import java.util.Scanner;
import models.Cliente;

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
		List<Cliente> clientes = this.clienteDAO.listarClientes();

		for (Cliente c : clientes) {
			System.out.println(c);
		}
    }


    private void addCliente() {
		System.out.println("Introduce el DNI del cliente:");
		String dni = sc.nextLine();
		System.out.println("Introduce el nombre del cliente:");
		String nombre = sc.nextLine();
		System.out.println("Introduce el teléfono del cliente:");
		String telefono = sc.nextLine();
		System.out.println("Introduce la dirección del cliente:");
		String direccion = sc.nextLine();

		Cliente c = new Cliente(dni, nombre, telefono, direccion);

		if(this.clienteDAO.addCliente(c)) {
			System.out.println("Cliente añadido correctamente");
		} else {
			System.out.println("Ha ocurrido un error!");
		}
    }

    
    private void modificarCliente() {
		System.out.println("Escribe el dni del cliente que quieres actualizar: ");
		String dni = sc.nextLine();
		System.out.println("¿Qué quieres actualizar?");
		System.out.println("1. Actualizar teléfono.");
		System.out.println("2. Actualizar dirección.");
		System.out.println("Actualizar los dos.");
		int opcion = sc.nextInt();
		sc.nextLine();

		switch (opcion) {
			case 1 -> {
				System.out.println("Escribe el nuevo número de teléfono");
				String telefono = sc.nextLine();
				if(this.clienteDAO.modificarTelefono(dni, telefono)) {
					System.out.println("Teléfono actualizado correctamente");
				} else {
					System.out.println("Ha ocurrido un error!");
				}
			}
			case 2 -> {
				System.out.println("Escribe la nueva direccion");
				String direccion = sc.nextLine();
				if(this.clienteDAO.modificarDireccion(dni, direccion)) {
					System.out.println("Dirección actualizada correctamente");
				} else {
					System.out.println("Ha ocurrido un error!");
				}
			}
			case 3 -> {
				System.out.println("Escribe el nuevo número de teléfono");
				String telefono = sc.nextLine();
				System.out.println("Escribe la nueva dirección");
				String direccion = sc.nextLine();
				if(this.clienteDAO.modificarTelefono(dni, telefono) && this.clienteDAO.modificarDireccion(dni, direccion)) {
					System.out.println("Teléfono y dirección actualizado correctamente");
				} else {
					System.out.println("Ha ocurrido un error!");
				}
			}
		}
    }


    private void eliminarCliente() {
		System.out.println("Introduce el DNI del cliente que quieres eliminar: ");
		String dni = sc.nextLine();

		if(this.clienteDAO.eliminarClienrte(dni)) {
			System.out.println("Cliente eliminado correctamente");
		} else {
			System.out.println("Ha ocurrido un error!");
		}
    }
    
    

    
}
