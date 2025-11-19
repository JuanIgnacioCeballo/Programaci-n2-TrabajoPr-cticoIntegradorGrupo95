# Sistema de Gestión de Biblioteca  
Proyecto Java + JDBC + MySQL

## Descripción del Dominio
El sistema implementa una gestión básica de una biblioteca.  
Los elementos principales del dominio son:

- **Libro**: representa una obra física disponible en la biblioteca. Contiene título, autor, editorial, año de edición y su ficha bibliográfica asociada.
- **FichaBibliografica**: contiene metadatos normalizados como ISBN, clasificación Dewey, estantería e idioma.
- **Base**: clase abstracta que proporciona los atributos `id` y `eliminado`, permitiendo implementar borrado lógico en todas las entidades.

El sistema permite realizar operaciones CRUD (Create, Read, Update, Delete lógico) tanto para **Libros** como para **Fichas Bibliográficas**, utilizando una arquitectura en capas:

- **DAO** — Acceso a datos con JDBC  
- **Service** — Lógica de negocio, validaciones y reglas del dominio  
- **AppMenu** — Interfaz de consola interactiva para el usuario  

---

## Requisitos

### Tecnologías necesarias
- **Java 17** o superior  
- **MySQL 8** o superior  
- **JDBC Driver:** `mysql-connector-j-8.x.x.jar` (debe estar en el classpath)  
- **IDE recomendado:** IntelliJ, NetBeans o Eclipse (opcional)

### Base de Datos
Para crear las tablas ejecutar el archivo: creacionTablas.sql

Este archivo crea:
- La base de datos
- La tabla `fichasBibliograficas`
- La tabla `libros` con su clave foránea

---

## Credenciales de Prueba
- **Host:** localhost  
- **Puerto:** 3306  
- **Base:** biblioteca  
- **Usuario:** root  
- **Contraseña:** *(vacía o la configurada en tu servidor MySQL)*

---

## Flujo de Uso del Programa

### Al iniciar el programa se muestra un menú con opciones para:
- Gestionar Libros  
- Gestionar Fichas Bibliográficas  
- Listar elementos  
- Crear, modificar o eliminar entidades  
- Salir del sistema  

### Validaciones del sistema
El sistema controla y captura:
- Números no válidos  
- Strings vacíos  
- ISBN con formato incorrecto  
- IDs inexistentes  
- Registros marcados como eliminados  
- Datos repetidos o sin cambios (en modificaciones)

### Flujo interno de las capas
- Todas las operaciones invocan a los **Service**, responsables de las validaciones y la lógica de negocio.  
- Los **DAO** ejecutan sentencias SQL utilizando `PreparedStatement`, implementando las operaciones CRUD con MySQL.
