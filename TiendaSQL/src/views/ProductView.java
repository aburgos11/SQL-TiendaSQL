package views;

import dao.ProductoDAO;
import java.util.List;
import java.util.Scanner;
import models.Producto;

public class ProductView {
	private Scanner sc = new Scanner(System.in);
	private ProductoDAO productoDAO = new ProductoDAO();
	
	public ProductView() {}
	
	public void iniciar() {
		int opcion;
		do {
			opcion = this.mostrarMenu();
			switch(opcion) {
				case 1 -> {
					this.listarProductos();
				}
				case 2 -> {
					this.addProducto();
				}
				case 3 -> {
					this.modificarProducto();
				}
				case 4 -> {
					this.eliminarProducto();
				}
			}			
		} while (opcion != 5);
	}
	
	private void listarProductos() {
		List<Producto> productos = this.productoDAO.getAll();

		for (Producto p : productos) {
			System.out.println(p);
		}
		
	}
	
	private void addProducto() {
		System.out.println("Introduce un nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Introduce un precio: ");
		double precio = sc.nextDouble();
		System.out.println("Introduce un stock: ");
		int stock = sc.nextInt();
		
		Producto p = new Producto(nombre, precio, stock);
				
		if(this.productoDAO.add(p)) {
			System.out.println("Producto Insertado correctamente");
		} else {
			System.out.println("Ha ocurrido un error!");
		}

	}

	private void modificarProducto() {
		System.out.println("Escribe el nombre del producto que quieres actualizar: ");
		String nombre = sc.nextLine();
		System.out.println("¿Qué quieres actualizar?");
		System.out.println("1. Actualizar precio.");
		System.out.println("2. Actualizar stock.");
		System.out.println("3. Actualizar los dos.");
		int opcion = sc.nextInt();
		sc.nextLine();

		switch (opcion) {
			case 1 -> {
				System.out.println("Escribe el nuevo precio");
				Double precio = sc.nextDouble();
				if(this.productoDAO.actualizarPrecio(nombre, precio)) {
					System.out.println("Precio actualizado correctamente");
				} else {
					System.out.println("Ha ocurrido un error!");
				}
			}
			case 2 -> {
				System.out.println("Escribe el nuevo stock");
				int stock = sc.nextInt();
				if(this.productoDAO.actualizarStock(nombre, stock)) {
					System.out.println("Stock actualizado correctamente");
				} else {
					System.out.println("Ha ocurrido un error!");
				}
			}
			case 3 -> {
				System.out.println("Escribe el nuevo precio");
				Double precio = sc.nextDouble();
				System.out.println("Escribe el nuevo stock");
				int stock = sc.nextInt();
				if(this.productoDAO.actualizarPrecio(nombre, precio) && this.productoDAO.actualizarStock(nombre, stock)) {
					System.out.println("Precio y stock actualizado correctamente");
				} else {
					System.out.println("Ha ocurrido un error!");
				}
			}
		}
	}

	private void eliminarProducto() {
		System.out.println("Introduce el nombre del producto que quieres eliminar: ");
		String nombre = sc.nextLine();

		if(this.productoDAO.deleteProducto(nombre)) {
			System.out.println("Producto eliminado correctamente");
		} else {
			System.out.println("Ha ocurrido un error!");
		}
	}



	private int mostrarMenu() {
		System.out.println("-- Gestión de productos --");
		System.out.println("1. Ver todos los productos");
		System.out.println("2. Añadir producto");
		System.out.println("3. Actualizar producto");
		System.out.println("4. Borrar producto");
		System.out.println("5. Salir");		
		int opcion = sc.nextInt();
		sc.nextLine();
		
		return opcion;
	}

	public Producto buscarProducto(String nombreProducto) {
		List<Producto> productos = this.productoDAO.getAll();

		for (Producto p : productos) {
			if(p.getNombre().equalsIgnoreCase(nombreProducto)) {
				return p;
			}
		}
		return null;
	}
	
}
