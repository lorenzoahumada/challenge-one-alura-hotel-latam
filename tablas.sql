CREATE DATABASE hotel;

USE hotel;

CREATE TABLE RESERVAS (ID INT NOT NULL auto_increment, 
FECHA_ENTRADA DATE NOT NULL, 
FECHA_SALIDA DATE NOT NULL,  
VALOR VARCHAR(100) NOT NULL, 
FORMA_PAGO VARCHAR(100) NOT NULL,
PRIMARY KEY(ID));

DROP TABLE RESERVAS;
DROP TABLE HUESPEDES;

CREATE TABLE HUESPEDES (ID INT NOT NULL auto_increment, 
NOMBRE VARCHAR(100) NOT NULL, 
APELLIDO VARCHAR(100) NOT NULL, 
FECHA_DE_NACIMIENTO DATE NOT NULL, 
NACIONALIDAD VARCHAR(100) NOT NULL, 
TELEFONO INT NOT NULL, 
ID_RESERVA INT NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY (ID_RESERVA) REFERENCES RESERVAS(ID));

INSERT RESERVAS (FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO) VALUES ('2024-01-10', '2024-01-17', '10000', 'Debito');
INSERT HUESPEDES (NOMBRE, APELLIDO, FECHA_DE_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA) VALUES ('Jorge', 'Perez', '1980-10-01', 'Argelino', 123132341, 1);

SELECT * FROM RESERVAS;
SELECT * FROM HUESPEDES;

SHOW databases

