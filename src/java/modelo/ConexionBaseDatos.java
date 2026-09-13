/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBaseDatos {
    // Variables de entorno (Render)
    private String url = System.getenv("DB_URL");
    private String usuario = System.getenv("DB_USER");
    private String password = System.getenv("DB_PASS");

    // Driver de PostgreSQL
    private String driver = "org.postgresql.Driver";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet filasConsulta;

    // Constructor por defecto
    public ConexionBaseDatos() throws Exception {
        this.conectar();
    }

    // Constructor alternativo (si quieres pasar parámetros manualmente)
    public ConexionBaseDatos(String url, String usuario, String password) throws Exception {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        this.conectar();
    }

    // Método para conectar
    public void conectar() throws Exception {
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("✅ Conexión exitosa a PostgreSQL.");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Error de Driver: " + ex.getMessage());
        } catch (SQLException ex) {
            throw new Exception("Error de Conexión \n Código:" + ex.getErrorCode() +
                                " Explicación:" + ex.getMessage());
        }
    }

    // Método para ejecutar INSERT, UPDATE, DELETE
    public int actualizar(PreparedStatement sentencia) throws Exception {
        try {
            return sentencia.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException("Error al ejecutar sentencia BD \n Código:" +
                                   ex.getErrorCode() + " Explicación:" + ex.getMessage());
        }
    }

    // Método para ejecutar SELECT
    public ResultSet consultar(PreparedStatement sentencia) throws Exception {
        try {
            return sentencia.executeQuery();
        } catch (SQLException ex) {
            throw new SQLException("Error al ejecutar consulta BD: " + ex.getMessage());
        }
    }

    // Método para cerrar conexión
    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("🔒 Conexión cerrada.");
            }
        } catch (SQLException ex) {
            conexion = null;
        }
    }

    // Método para crear una sentencia preparada
    public PreparedStatement crearSentencia(String sql) throws Exception {
        try {
            return conexion.prepareStatement(sql);
        } catch (SQLException ex) {
            throw new SQLException("Error al crear sentencia \n Código:" +
                                   ex.getErrorCode() + " Explicación:" + ex.getMessage());
        }
    }

    // Getters y Setters
    public String getDriver() { return driver; }
    public void setDriver(String driver) { this.driver = driver; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Connection getConexion() { return conexion; }
    public void setConexion(Connection conexion) { this.conexion = conexion; }

    public PreparedStatement getSentencia() { return sentencia; }
    public void setSentencia(PreparedStatement sentencia) { this.sentencia = sentencia; }

    public ResultSet getFilasConsulta() { return filasConsulta; }
    public void setFilasConsulta(ResultSet filasConsulta) { this.filasConsulta = filasConsulta; }
}
