package views;

import dao.PedidoDAO;
import java.util.Scanner;

public class PedidoView {
    private Scanner sc = new Scanner(System.in);
    private PedidoDAO pedidoDAO = new PedidoDAO();

    public void iniciar() {

        int opcion;
		do {
			opcion = this.mostrarMenu();
			switch(opcion) {
				case 1 -> {
					this.listarPedidos();
				}
				case 2 -> {
					this.newPedido();
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
    
    }
    

    private void newPedido() {
    
    }


    private void modificarPedido() {
    
    }


    private void cancelarPedido() {
    
    }
    
    

}
