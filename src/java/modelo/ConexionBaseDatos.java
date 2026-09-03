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
    protected String driver = "com.mysql.cj.jdbc.Driver";
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
       url = url+IPServidor+puerto+nombreBD+complemento;
       
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
     
}
