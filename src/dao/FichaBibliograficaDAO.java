
package dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.FichaBibliografica;


public class FichaBibliograficaDAO implements GenericDAO<FichaBibliografica>{

    @Override
    public void insert(FichaBibliografica ficha) throws Exception {
        String insertFicha = "INSERT INTO fichasbibliograficas (eliminado, isbn, clasificacion_Dewey, estanteria, idioma) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(insertFicha, Statement.RETURN_GENERATED_KEYS);) {
            sentencia.setBoolean(1, false);
            sentencia.setString(2, ficha.getIsbn());
            sentencia.setString(3, ficha.getClasificacion_Dewey());
            sentencia.setString(4, ficha.getEstanteria());
            sentencia.setString(5, ficha.getIdioma());
            
            sentencia.executeUpdate();
            
            try (ResultSet rs = sentencia.getGeneratedKeys()) {
                // System.out.println(rs);
                if(rs.next()) {
                    ficha.setId(rs.getInt(1));
                    System.out.println("Ficha insertada con id: "+ficha.getId());
                } else {
                    throw new SQLException("Error: La inserción de la ficha falló");
                }
            }
        }
    }

    @Override
    public void update(FichaBibliografica ficha) throws Exception {
        String updateFicha = "UPDATE fichasbibliograficas SET isbn = ?, clasificacion_Dewey = ?, estanteria = ?, idioma = ? WHERE id_ficha = ?";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(updateFicha);) {
            sentencia.setString(1, ficha.getIsbn());
            sentencia.setString(2, ficha.getClasificacion_Dewey());
            sentencia.setString(3, ficha.getEstanteria());
            sentencia.setString(4, ficha.getIdioma());
            sentencia.setInt(5, ficha.getId()); // Corresponde a la condicion WHERE id = ...
            
            // Ejecuta el update 
            int respuesta = sentencia.executeUpdate();
            
            // Si no recibe rows es porque el id ingresado no existe
            if(respuesta == 0) {
                throw new SQLException("No existe ficha con id: "+ficha.getId());
            }
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String delete = "UPDATE fichasbibliograficas SET eliminado = ? WHERE id_ficha = ?";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(delete);){
            sentencia.setBoolean(1, true);
            sentencia.setInt(2, id);
            
            // Ejecutar el eliminado lógico
            int response = sentencia.executeUpdate();
            
            // Si no recibe rows es porque el id ingresado no existe
            if(response == 0) {
                throw new SQLException("No existe ficha con id: "+id);
            }
        }
        
    }

    @Override
    public FichaBibliografica getById(int id) throws Exception {
        String getByIdQuery = "SELECT * FROM fichasbibliograficas WHERE id_ficha = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(getByIdQuery); ) {
            sentencia.setInt(1, id);
            
            // Se lanza la query dentro de un bloque try
            try (ResultSet respuesta = sentencia.executeQuery()) {
                // se valida si la respuesta posee una row (Es decir, si la query devolvió algo)
                if(respuesta.next()) {
                    // Se crea un objeto libro (en memoria) con los datos obtenidos en la respuesta (ResultSet respuesta)
                    return new FichaBibliografica(
                        id,
                        respuesta.getBoolean("eliminado"),
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

    @Override
    public List<FichaBibliografica> getAll() throws Exception {
        List<FichaBibliografica> listaFichas = new ArrayList<>();
        
        String queryGetAll = "SELECT * FROM fichasbibliograficas";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(queryGetAll);) {
            
            // Se ejecuta la query
            ResultSet respuesta = sentencia.executeQuery();
            
            // Por cada row presente en la respuesta de la query se mapea cada columna en una variable individual
            while(respuesta.next()) {
                int idFicha = respuesta.getInt("id_ficha");
                boolean eliminado = respuesta.getBoolean("eliminado");
                String isbn = respuesta.getString("isbn");
                String clasificacion = respuesta.getString("clasificacion_Dewey");
                String estanteria = respuesta.getString("estanteria");
                String idioma = respuesta.getString("idioma");
                
                // con las variables se crea un objeto Ficha
                FichaBibliografica ficha = new FichaBibliografica(idFicha, eliminado, isbn, clasificacion, estanteria, idioma);
                
                // Se agrega esa cada ficha a la lista de fichas
                listaFichas.add(ficha);
            }
           
        } catch (SQLException e) {
            throw new Exception("Error al obtener todas las fichas bibliográficas", e);
        }
        
        return listaFichas;
    }

    @Override
    public void recuperar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public FichaBibliografica getByIdActivo(int id) throws Exception {
        String getByIdQuery = "SELECT * FROM fichasbibliograficas WHERE id_ficha = ? AND eliminado = 0";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(getByIdQuery); ) {
            sentencia.setInt(1, id);
            
            // Se lanza la query dentro de un bloque try
            try (ResultSet respuesta = sentencia.executeQuery()) {
                // se valida si la respuesta posee una row (Es decir, si la query devolvió algo)
                if(respuesta.next()) {
                    // Se crea un objeto libro (en memoria) con los datos obtenidos en la respuesta (ResultSet respuesta)
                    return new FichaBibliografica(
                        id,
                        respuesta.getBoolean("eliminado"),
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
    
    @Override
    public List<FichaBibliografica> getAllActivos() throws Exception {
        List<FichaBibliografica> listaFichas = new ArrayList<>();
        
        String queryGetAll = "SELECT * FROM fichasbibliograficas WHERE eliminado = 0";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement sentencia = conn.prepareStatement(queryGetAll);) {
            
            // Se ejecuta la query
            ResultSet respuesta = sentencia.executeQuery();
            
            // Por cada row presente en la respuesta de la query se mapea cada columna en una variable individual
            while(respuesta.next()) {
                int idFicha = respuesta.getInt("id_ficha");
                boolean eliminado = respuesta.getBoolean("eliminado");
                String isbn = respuesta.getString("isbn");
                String clasificacion = respuesta.getString("clasificacion_Dewey");
                String estanteria = respuesta.getString("estanteria");
                String idioma = respuesta.getString("idioma");
                
                // con las variables se crea un objeto Ficha
                FichaBibliografica ficha = new FichaBibliografica(idFicha, eliminado, isbn, clasificacion, estanteria, idioma);
                
                // Se agrega esa cada ficha a la lista de fichas
                listaFichas.add(ficha);
            }
           
        } catch (SQLException e) {
            throw new Exception("Error al obtener todas las fichas bibliográficas", e);
        }
        
        return listaFichas;
    }
    
}
