CREATE DATABASE IF NOT EXISTS banco;
USE banco;

CREATE TABLE clientes (
        id int(11) NOT NULL,
        nombre varchar(100) DEFAULT NULL,
        edad int(11) DEFAULT NULL
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE clientes ADD PRIMARY KEY (id);

ALTER TABLE clientes MODIFY id int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

CREATE TABLE cuentas (
        numero int(10) primary key,
        fecha varchar(20),
        saldo double,
        interes double,
        cliente int(3),
        tipoCuenta varchar(23));
