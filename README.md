# Sistema de Gestión de Biblioteca  
Proyecto Java + JDBC + MySQL

## Descripción del Dominio
El sistema implementa una gestión básica de una biblioteca.  
Los elementos principales del dominio son:

- **Libro**: representa una obra física disponible en la biblioteca.  
  Contiene información como título, autor, editorial, año de edición y su ficha bibliográfica.  
- **FichaBibliografica**: contiene metadatos normalizados como ISBN, clasificación Dewey, estantería e idioma.  
- **Base**: clase abstracta que provee `id` y `eliminado` para implementar borrado lógico en todas las entidades.

El sistema permite realizar operaciones CRUD (Create, Read, Update, Delete lógico) tanto para **Libros** como para **Fichas Bibliográficas**, respetando reglas de negocio y validaciones mediante un patrón de capas:

- **DAO** (Data Access Object)
- **Service** (lógica de negocio y validaciones)
- **AppMenu** (interfaz de consola que guía al usuario)

---

## Requisitos

### Tecnologías necesarias
- **Java 17** o superior  
- **MySQL 8** o superior  
- **JDBC Driver** (mysql-connector-j-8.x.x.jar) disponible en el classpath  
- **IDE**: IntelliJ, NetBeans o Eclipse (opcional)

### Base de Datos
- Ejecutar el archivo creacionTablas.sql para poder crear las tablas de la base de datos

## Credenciales de Prueba
- host: localhost
- puerto: 3306
- base: biblioteca
- usuario: root
- contraseña: (vacía o utilizada por el usuario)

## Flujo de Uso del Programa

. Al ejecutar, se muestra el AppMenu principal con opciones:
    - Gestionar Libros
    - Gestionar Fichas Bibliográficas
    - Listar elementos
    - Crear, modificar o eliminar entidades
    - Salir del sistema

. El sistema valida tipos de datos y captura excepciones:
    - Números no válidos
    - Strings vacíos
    - ISBN incorrecto
    - IDs inexistentes
    - Registros marcados como eliminados

. Todas las operaciones pasan por los Service, que son responsables de:
    - Validaciones
    - Reglas de negocio
    - Comunicación con DAOs

. Los DAOs ejecutan las consultas SQL contra MySQL utilizando PreparedStatement y el patrón CRUD.
