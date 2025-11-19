package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Credenciales de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/tpi_programacion2";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    static {
        try {
            // Se carga el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Se arroja una excepcion en caso de que el driver no este disponible
            throw new RuntimeException("Error: No se encontro el Driver JDBC", e);
        }
    }
    
    /**
     * Método para obtener una conexión a la base de datos.
     * @return una conexión Connection si efectivamente esta es eexitosa
     * @throws SQLException si hay un problema al conectarse
     */
    public static Connection getConnection() throws SQLException {
        // Validacion para asegurarse que las credenciales necesarias no estén vacias
        if(URL == null || URL.isEmpty() || USER == null || USER.isEmpty() ) {
            throw new SQLException("Configuracion de la base de datos incompleta o invalida");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
