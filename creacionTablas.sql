CREATE DATABASE IF NOT EXISTS tpi_programacion2;
use tpi_programacion2;

CREATE TABLE fichasBibliograficas (
id_ficha int(11) NOT NULL UNIQUE AUTO_INCREMENT,
eliminado tinyint(1) NOT NULL,
isbn VARCHAR(13) NOT NULL UNIQUE,
clasificacion_Dewey VARCHAR(20) NOT NULL,
estanteria VARCHAR(20) NOT NULL,
idioma VARCHAR(30) NOT NULL,
PRIMARY KEY (id_ficha)
);

CREATE TABLE libros (
id_libro INT(11) PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
eliminado TINYINT(1) NOT NULL,
titulo VARCHAR(150) NOT NULL UNIQUE,
autor VARCHAR(120) NOT NULL,
editorial VARCHAR(100) NOT NULL,
anioEdicion INT(4) NOT NULL,
id_ficha INT(11) NOT NULL,
CONSTRAINT fk_id_ficha FOREIGN KEY (id_ficha) REFERENCES fichasBibliograficas (id_ficha) ON DELETE CASCADE
);



