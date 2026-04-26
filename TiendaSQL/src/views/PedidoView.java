package views;

import dao.PedidoDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Cliente;
import models.Pedido;
import models.Producto;

public class PedidoView {
	private Scanner sc = new Scanner(System.in);
	private PedidoDAO pedidoDAO = new PedidoDAO();
	private ProductView productView = new ProductView();
	private ClienteView clienteView = new ClienteView();

	public void iniciar() {

		int opcion;
		do {
			opcion = this.mostrarMenu();
			switch (opcion) {
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

		if(!pedidos.isEmpty()) {
			
			for (Pedido p : pedidos) {
				System.out.println(p);
			}

		} else {
			System.out.println("No hay pedidos registrados actualmente");
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
		LocalDate fecha = LocalDate.now();

		Pedido p = new Pedido(cliente, producto, cantidad, fecha);

		if (this.pedidoDAO.addPedido(p)) {
			System.out.println("Pedido agregado correctamente");
		} else {
			System.out.println("Ha ocurrido un error!");
		}

	}

	private void modificarPedido() { 
		/*No se puede modificar ni el id, ni el cliente, ni el producto ya que sería cancelarlo.
		La fecha es automática. Se puede modificar la cantidad del producto */
		
		System.out.println("¿Para qué cliente quieres modificar el pedido? Ingresa su DNI");
		String dni = sc.nextLine();
		Cliente cliente = clienteView.buscarCliente(dni);
		int id_pedido = buscarPedidoModificar(cliente);
		System.out.println("Ingresa la nueva cantidad deseada. En caso de querer cancelar el pedido ve al apartado 'candelar pedido'");
		int cantidad = sc.nextInt();
		sc.nextLine();
		LocalDate fecha = LocalDate.now();

		if (this.pedidoDAO.actualizarPedido(id_pedido, cantidad, fecha)) {
			System.out.println("Precio actualizado correctamente");
		} else {
			System.out.println("Ha ocurrido un error!");
		}

	}

	private void cancelarPedido() {
		System.out.println("Introduce el DNI del cliente:");
		String dni = sc.nextLine();
		Cliente cliente = clienteView.buscarCliente(dni);

		if (cliente != null) {
			Integer id_pedido = buscarPedidoModificar(cliente);
			if (id_pedido != null) {
				if (this.pedidoDAO.cancelarPedido(id_pedido)) {
					System.out.println("Pedido eliminado correctamente");
				} else {
					System.out.println("No se pudo eliminar el pedido");
				}
			}
		} else {
			System.out.println("Cliente no encontrado.");
		}
	}

	private Integer buscarPedidoModificar(Cliente cliente) {

		List<Pedido> pedidos = this.pedidoDAO.listarPedidos();
		List<Pedido> pedidosEncontrados = new ArrayList<>();

		for (Pedido p : pedidos) {
			if (p.getCliente() == cliente) {
				pedidosEncontrados.add(p);
			}
		}

		if (!pedidosEncontrados.isEmpty()) {
			System.out.println("Estos son los pedidos de este cliente que hemos encontrado:");
			int contador = 1;
			for (Pedido p : pedidosEncontrados) {
				System.out.println(contador + ". " + p);
				contador++;
			}
			System.out.println("¿Qué pedido quieres modificar?");
			int opcion = sc.nextInt();
			sc.nextLine();

			if (opcion - 1 > pedidosEncontrados.size() || opcion - 1 < 0) {
				System.out.println("No hay una opcion " + opcion);
			} else {
				Pedido pedido = pedidosEncontrados.get(opcion - 1);
				int id_pedido = pedido.getId();
				return id_pedido;
			}
		} else {
			System.out.println("No se han encontrado pedidos para este cliente");
		}
		return null;
	}

}
