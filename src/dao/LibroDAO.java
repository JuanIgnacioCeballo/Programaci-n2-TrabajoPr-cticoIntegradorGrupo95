package dao;


import java.util.List;
import modelos.Libro;
import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import modelos.FichaBibliografica;


public class LibroDAO implements GenericDAO<Libro>{

    @Override
    public void insert(Libro libro) throws Exception {
        FichaBibliografica ficha = libro.getFichaBibliografica();
        int fichaId = ficha.getId();
        String insertLibro = "INSERT INTO libros (eliminado, titulo, autor, editorial, anioEdicion, id_ficha) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(insertLibro, Statement.RETURN_GENERATED_KEYS);) {
            sentencia.setBoolean(1, false);
            sentencia.setString(2, libro.getTitulo());
            sentencia.setString(3, libro.getAutor());
            sentencia.setString(4, libro.getEditorial());
            sentencia.setInt(5, libro.getAnioEdicion());
            sentencia.setInt(6, fichaId);
            
            sentencia.executeUpdate();
            
            try (ResultSet rs = sentencia.getGeneratedKeys()) {
                if(rs.next()) {
                    libro.setId(rs.getInt(1));
                    System.out.println("Libro insertado con id: "+libro.getId());
                } else {
                    throw new SQLException("Error: La inserción de el libro falló");
                }
            }
        }
    }

    @Override
    public void update(Libro libro) throws Exception {
        String updateLibro = "UPDATE libros SET titulo = ?, autor = ?, editorial = ?, anioEdicion = ? WHERE id_libro = ?";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(updateLibro);) {
            sentencia.setString(1, libro.getTitulo());
            sentencia.setString(2, libro.getAutor());
            sentencia.setString(3, libro.getEditorial());
            sentencia.setInt(4, libro.getAnioEdicion());
            sentencia.setInt(5, libro.getId()); // Representa el valor agregado a la clausula WHERE id_libro = 
            
            // Ejecuta el update 
            int respuesta = sentencia.executeUpdate();
            
            // Si no recibe rows es porque el id ingresado no existe
            if(respuesta == 0) {
                throw new SQLException("No existe ficha con id: "+libro.getId());
            }
            
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String delete = "UPDATE libros SET eliminado = ? WHERE id_libro = ?";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(delete); ) {
            sentencia.setBoolean(1, true);
            sentencia.setInt(2, id);
            
            // Ejecuta el eliminado lógico 
            int response = sentencia.executeUpdate();
            
            // Si no recibe rows es porque el id ingresado no existe
            if(response == 0) {
                throw new SQLException("No existe libro con id: "+id);
            }
            
        }
        
    }

    @Override
    public Libro getById(int id) throws Exception {
        // Select con join para obtener los datos de libro para crearlo y de la ficha para crearlo también
        String getByIdQuery = "SELECT " +
            "l.id_libro, l.eliminado AS libro_eliminado, l.titulo, l.autor, l.editorial, l.anioEdicion, " +
            "f.id_ficha, f.eliminado AS ficha_eliminado, f.isbn, f.clasificacion_Dewey, " +
            "f.estanteria, f.idioma " +
            "FROM libros l " +
            "INNER JOIN fichasbibliograficas f ON l.id_ficha = f.id_ficha " +
            "WHERE l.id_libro = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(getByIdQuery); ) {
            sentencia.setInt(1, id);
            
            // Se lanza la query dentro de un bloque try
            try (ResultSet respuesta = sentencia.executeQuery()) {
                // se valida si la respuesta posee una row (Es decir, si la query devolvió algo)
                if(respuesta.next()) {
                    // Se crea un objeto libro en memoria (y a su vez ficha también) con los datos obtenidos en la respuesta (ResultSet respuesta)
                    return new Libro(
                        respuesta.getInt("id_libro"),
                        respuesta.getBoolean("libro_eliminado"),
                        respuesta.getString("titulo"),
                        respuesta.getString("autor"),
                        respuesta.getString("editorial"),
                        respuesta.getInt("anioEdicion"),
                        respuesta.getInt("id_ficha"),
                        respuesta.getBoolean("ficha_eliminado"),
                        respuesta.getString("isbn"),
                        respuesta.getString("clasificacion_Dewey"),
                        respuesta.getString("estanteria"),
                        respuesta.getString("idioma")
                    );
                } else {
                    return null;
                }
            }
        }
        
    }
     /*
    @Override
    public int getById(int id) throws Exception {
        String query = "SELECT id_ficha FROM libros WHERE id_libro = ?";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(getByIdQuery); ) {
            sentencia.setInt(1, id);
            
        }
    }
    */

    @Override
    public List<Libro> getAll() throws Exception {
        List<Libro> listaLibros = new ArrayList<>();
        String queryGetAll = "SELECT " +
            "l.id_libro, l.eliminado AS libro_eliminado, l.titulo, l.autor, l.editorial, l.anioEdicion, " +
            "f.id_ficha, f.eliminado AS ficha_eliminado, f.isbn, f.clasificacion_Dewey, " +
            "f.estanteria, f.idioma " +
            "FROM libros l " +
            "INNER JOIN fichasbibliograficas f ON l.id_ficha = f.id_ficha";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(queryGetAll);) {
            
            // Se ejecuta la query
            ResultSet respuesta = sentencia.executeQuery();
            
            while(respuesta.next()) {
                Libro libro = new Libro(
                    respuesta.getInt("id_libro"),
                    respuesta.getBoolean("libro_eliminado"),
                    respuesta.getString("titulo"),
                    respuesta.getString("autor"),
                    respuesta.getString("editorial"),
                    respuesta.getInt("anioEdicion"),
                    respuesta.getInt("id_ficha"),
                    respuesta.getBoolean("ficha_eliminado"),
                    respuesta.getString("isbn"),
                    respuesta.getString("clasificacion_Dewey"),
                    respuesta.getString("estanteria"),
                    respuesta.getString("idioma")
                );
                
                listaLibros.add(libro);
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al obtner lista de todos los libros"+e);
        }
        
        return listaLibros;
    }
    
    @Override
    public void recuperar(int id) throws Exception {
        
    }
    
    @Override
    public List<Libro> getAllActivos() throws Exception {
        List<Libro> listaLibros = new ArrayList<>();
        String queryGetAll = "SELECT " +
            "l.id_libro, l.eliminado AS libro_eliminado, l.titulo, l.autor, l.editorial, l.anioEdicion, " +
            "f.id_ficha, f.eliminado AS ficha_eliminado, f.isbn, f.clasificacion_Dewey, " +
            "f.estanteria, f.idioma " +
            "FROM libros l " +
            "INNER JOIN fichasbibliograficas f ON l.id_ficha = f.id_ficha " +
            "WHERE l.eliminado = 0";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(queryGetAll);) {
            
            // Se ejecuta la query
            ResultSet respuesta = sentencia.executeQuery();
            
            while(respuesta.next()) {
                Libro libro = new Libro(
                    respuesta.getInt("id_libro"),
                    respuesta.getBoolean("libro_eliminado"),
                    respuesta.getString("titulo"),
                    respuesta.getString("autor"),
                    respuesta.getString("editorial"),
                    respuesta.getInt("anioEdicion"),
                    respuesta.getInt("id_ficha"),
                    respuesta.getBoolean("ficha_eliminado"),
                    respuesta.getString("isbn"),
                    respuesta.getString("clasificacion_Dewey"),
                    respuesta.getString("estanteria"),
                    respuesta.getString("idioma")
                );
                
                listaLibros.add(libro);
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al obtner lista de todos los libros"+e);
        }
        
        return listaLibros;
    }
    
    @Override
    public Libro getByIdActivo(int id) throws Exception {
        // Select con join para obtener los datos de libro para crearlo y de la ficha para crearlo también
        String getByIdQuery = "SELECT " +
            "l.id_libro, l.eliminado AS libro_eliminado, l.titulo, l.autor, l.editorial, l.anioEdicion, " +
            "f.id_ficha, f.eliminado AS ficha_eliminado, f.isbn, f.clasificacion_Dewey, " +
            "f.estanteria, f.idioma " +
            "FROM libros l " +
            "INNER JOIN fichasbibliograficas f ON l.id_ficha = f.id_ficha " +
            "WHERE l.id_libro = ? AND l.eliminado = 0";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(getByIdQuery); ) {
            sentencia.setInt(1, id);
            
            // Se lanza la query dentro de un bloque try
            try (ResultSet respuesta = sentencia.executeQuery()) {
                // se valida si la respuesta posee una row (Es decir, si la query devolvió algo)
                if(respuesta.next()) {
                    // Se crea un objeto libro en memoria (y a su vez ficha también) con los datos obtenidos en la respuesta (ResultSet respuesta)
                    return new Libro(
                        respuesta.getInt("id_libro"),
                        respuesta.getBoolean("libro_eliminado"),
                        respuesta.getString("titulo"),
                        respuesta.getString("autor"),
                        respuesta.getString("editorial"),
                        respuesta.getInt("anioEdicion"),
                        respuesta.getInt("id_ficha"),
                        respuesta.getBoolean("ficha_eliminado"),
                        respuesta.getString("isbn"),
                        respuesta.getString("clasificacion_Dewey"),
                        respuesta.getString("estanteria"),
                        respuesta.getString("idioma")
                    );
                } else {
                    return null;
                }
            }
        }
        
    }
    
}
