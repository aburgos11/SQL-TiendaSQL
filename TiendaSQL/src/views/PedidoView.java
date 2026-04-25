package views;

import dao.PedidoDAO;
import java.util.List;
import java.util.Scanner;
import models.Cliente;
import models.Pedido;
import models.Producto;

public class PedidoView {
    private Scanner sc = new Scanner(System.in);
    private PedidoDAO pedidoDAO = new PedidoDAO();
	private ProductView productView;
	private ClienteView clienteView;

    public void iniciar() {

        int opcion;
		do {
			opcion = this.mostrarMenu();
			switch(opcion) {
				case 1 -> {
					this.listarPedidos();
				}
				case 2 -> {
					this.addPedido();
				}
				case 3 -> {
					this.modificarPedido();
				}
				case 4 -> {
					this.cancelarPedido();
				}
			}			
		} while (opcion != 5);
        
    }



    private int mostrarMenu() {
        System.out.println("-- Gestión de Pedidos --");
		System.out.println("1. Ver todos los pedidos");
		System.out.println("2. Nuevo pedido");
		System.out.println("3. Modificar pedido");
		System.out.println("4. Cancelar pedido");
		System.out.println("5. Salir");		
		int opcion = sc.nextInt();
		sc.nextLine();
		
		return opcion;
	}


    private void listarPedidos() {
		List<Pedido> pedidos = this.pedidoDAO.listarPedidos();

		for (Pedido p : pedidos) {
			System.out.println(p);
		}
    }
    

    private void addPedido() {
		System.out.println("Introduce el dni del cliente que realiza el pedido: ");
		String dni = sc.nextLine();
		Cliente cliente = clienteView.buscarCliente(dni);
		System.out.println("Introduce el nombre del producto: ");
		String nombreProducto = sc.nextLine();
		Producto producto = productView.buscarProducto(nombreProducto);
		System.out.println("Introduce la cantidad: ");
		int cantidad = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduce la fecha: ");
		String fecha = sc.nextLine();
		
		Pedido p = new Pedido(cliente, producto, cantidad, fecha);
				
		if(this.pedidoDAO.addPedido(p)) {
			System.out.println("Pedido agregado correctamente");
		} else {
			System.out.println("Ha ocurrido un error!");
		}

    }
	
	
    private void modificarPedido() {
		
	}
	
	
    private void cancelarPedido() {
		
	}

	private void buscarCliente(String dni) {

	}

	
    
    
	
}
