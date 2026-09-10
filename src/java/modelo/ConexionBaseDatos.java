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

/**
 *
 * @author ASUS
 */
public class ConexionBaseDatos {
    protected String driver = "com.mysql.jdbc.Driver";
    protected String url = "jdbc:mysql://";
    protected String IPServidor = "localhost:";
    protected int puerto = 3306;
    protected String nombreBD = "empleodb";
    protected String complemento = "?zeroDateTimeBehavior=convertToNull";
    protected String Usuario = "estudiante";
    protected String password = "1234";
    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet filasConsulta;
    
    public ConexionBaseDatos ( ) throws Exception{
       url = url+IPServidor+puerto+"/"+nombreBD+complemento;
       this.conectar();
    }
    public ConexionBaseDatos (String driver, String url, String IPServidor, int puerto, String nombreBD, 
            String complemento, String Usuario, String password ) throws Exception{
        this.driver = driver;
        this.Usuario = Usuario;
        this.IPServidor = IPServidor;
        this.complemento = complemento;
        this.nombreBD = nombreBD;
        this.password = password;
        this.puerto = puerto;
        this.url = url;
        this.conectar();
    }
    
    public void conectar () throws Exception{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
                throw new Exception("Error de Driver "+ex.getMessage());
        } 
        try {
            conexion =DriverManager.getConnection(url, Usuario, password);
        }catch (SQLException ex) {
        throw new Exception("Error de Conexión \n Código:" + ex.getErrorCode() +
                            " Explicación:" + ex.getMessage());
        }
    } 
     public int actualizar(PreparedStatement sentencia) throws Exception {
    try {
        int res = sentencia.executeUpdate();
        return res;
    }
    catch (SQLException ex) {
        throw new SQLException("Error al ejecutar sentencia BD Conexion \n Codigo:"
            + ex.getErrorCode() + " Explicacion:" + ex.getMessage());
    }
}
     // Método para ejecutar consultas SELECT
     public ResultSet consultar(PreparedStatement sentencia) throws Exception {
         try {
             ResultSet filasBD = sentencia.executeQuery(); // solo para SELECT
             return filasBD;
         } catch (SQLException ex) {
             throw new SQLException("Error al ejecutar sentencia BD Conexion "
                + ex.getMessage());
         }
     }

// Método para cerrar la conexión

     public void desconectar() {
         try {
             conexion.close(); // libera recursos
         } catch (SQLException ex) {
        conexion = null; // si falla, se asegura de limpiar la referencia
         }
     }

// Método para crear una sentencia preparada

     public PreparedStatement crearSentencia(String sql) throws Exception {
         try {
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             return sentencia;
         } catch (SQLException ex) {
             throw new SQLException("Error de Sentencia DB \n Codigo:"
                + ex.getErrorCode() + " Explicacion:" + ex.getMessage());
         }
     }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIPServidor() {
        return IPServidor;
    }

    public void setIPServidor(String IPServidor) {
        this.IPServidor = IPServidor;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public PreparedStatement getSentencia() {
        return sentencia;
    }

    public void setSentencia(PreparedStatement sentencia) {
        this.sentencia = sentencia;
    }

    public ResultSet getFilasConsulta() {
        return filasConsulta;
    }

    public void setFilasConsulta(ResultSet filasConsulta) {
        this.filasConsulta = filasConsulta;
    }
}
