package views;

import java.util.Scanner;

public class GestorTienda {
	
	public static Scanner sc = new Scanner(System.in);
	public static ProductView productview = new ProductView();
	public static ClienteView clienteview = new ClienteView();
	public static PedidoView pedidoview = new PedidoView();

	public static void main(String[] args) {
		
		System.out.println("Bienvenido al mejor gestor de tiendas :)");
		
		int opcion;
		
		do {
			opcion = mostrarMenuYLeerOpcion();
	
			switch(opcion) {
				case 1 -> {
					productview.iniciar();
				}
				case 2 -> {
					clienteview.iniciar();
				}
				case 3 -> {
					pedidoview.iniciar();
				}
			
			}
		
		} while(opcion != 4);	
		
	}

	private static int mostrarMenuYLeerOpcion() {
		System.out.println("-- Menu Inicial --");
		System.out.println("1. Gestión de Productos");
		System.out.println("2. Gestión de Clientes");
		System.out.println("3. Gestión de Pedidos");
		System.out.println("4. Salir \n");
		System.out.println("Introduce una opción");
		int opcion = sc.nextInt();
		sc.nextLine();
		return opcion;
	}

}
