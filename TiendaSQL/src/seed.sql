CREATE DATABASE IF NOT EXISTS tienda;

USE tienda;

CREATE TABLE IF NOT EXISTS productos (
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(255),
	precio DECIMAL(7,2),
	stock INT
);

CREATE TABLE IF NOT EXISTS clientes (
	id INT PRIMARY KEY AUTO_INCREMENT,
	dni VARCHAR(15) UNIQUE,
	nombre VARCHAR(255),
	telefono VARCHAR(15),
	direccion TEXT
);

CREATE TABLE IF NOT EXISTS pedidos (
	id INT PRIMARY KEY AUTO_INCREMENT,
	id_cliente INT,
	id_producto INT,
	cantidad INT,
	fecha DATE,
	FOREIGN KEY (id_cliente) REFERENCES clientes(id),
	FOREIGN KEY (id_producto) REFERENCES productos(id)
);