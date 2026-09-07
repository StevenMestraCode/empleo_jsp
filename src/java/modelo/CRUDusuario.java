/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class CRUDusuario {
    // Atributo que representa un objeto usuario
    private usuario alguien;
    // Atributo para manejar la conexión con la base de datos
    private ConexionBaseDatos baseDatos;
    
    // Constructor: inicializa un usuario vacío y abre conexión a la BD
    public CRUDusuario() throws Exception{
        this.alguien = new usuario();
        this.baseDatos = new ConexionBaseDatos();
    }

    // CREATE: método para agregar un usuario a la BD
    public void agregarUsuario() throws Exception{
        // Validación: el ID no puede ser nulo ni vacío
        if (alguien.getId() == null || alguien.getId().isEmpty()){
            throw new Exception("El ID es necesario");
        }
        // Sentencia SQL para insertar un nuevo registro
        String sqlInsert = "INSERT INTO usuarios"
                + "(id, clave, nombre, correo, rol)"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            // Preparar la sentencia con parámetros
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlInsert);
            sentenciaSQL.setString(1, alguien.getId());
            sentenciaSQL.setString(2, alguien.getClave());
            sentenciaSQL.setString(3, alguien.getNombre());
            sentenciaSQL.setString(4, alguien.getCorreo());
            sentenciaSQL.setString(5, alguien.getRol());
            // Ejecutar el INSERT
            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            // Manejo de errores
            throw new Exception("Error al agregar el usuario" + alguien.getId()+
                    " <br/> explicacion: " + error.getMessage());
        }finally{
            // Cerrar conexión
            baseDatos.desconectar();
        }
    }
 
    // UPDATE: método para modificar datos de un usuario existente
    public void modificarUsuario() throws Exception{
        // Validación: el ID es obligatorio
        if (alguien.getId() == null || alguien.getId().isEmpty()){
            throw new Exception("El ID es necesario");
        }
        // Sentencia SQL para actualizar datos
        String sqlUpdate = "UPDATE usuarios"
                + "(setclave=?, nombre=?, correo=?, rol=?)"
                + "WHERE id=?";
        try {
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlUpdate);
            sentenciaSQL.setString(1, alguien.getId());
            sentenciaSQL.setString(2, alguien.getClave());
            sentenciaSQL.setString(3, alguien.getNombre());
            sentenciaSQL.setString(4, alguien.getCorreo());
            sentenciaSQL.setString(5, alguien.getRol());
            // Ejecutar el UPDATE
            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al actualizar el usuario" + alguien.getId()+
                    " <br/> explicacion: " + error.getMessage());
        }finally{
            baseDatos.desconectar();
        }
    }

    // DELETE: método para eliminar un usuario por su ID
    public void eliminarUsuario() throws Exception{
        if (alguien.getId() == null || alguien.getId().isEmpty()){
            throw new Exception("El ID es necesario");
        }
        // Sentencia SQL para borrar un registro
        String sqlUpdate = "DELETE usuarios"
                + "WHERE id=?";
        try {
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlUpdate);
            sentenciaSQL.setString(1, alguien.getId());
            // Ejecutar el DELETE
            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al eliminar el usuario" + alguien.getId()+
                    " <br/> explicacion: " + error.getMessage());
        }finally{
            baseDatos.desconectar();
        }
    }

    // LOGIN: método para iniciar sesión con correo y clave
    public usuario inicioSesion(String correo, String clave) throws Exception{
        if (correo == null || correo.isEmpty() || clave == null || clave.isEmpty()){
            throw new Exception("El correo y la clave son necesarias");
        }
        usuario alguien = null; ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM usuarios WHERE correo=? and clave=?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            // Asignar parámetros de login
            sentenciaSQL.setString(1, alguien.getCorreo());
            sentenciaSQL.setString(2, alguien.getClave());
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            // Si existe el usuario, llenar el objeto con sus datos
            if(resultado.next() == true){
                alguien = new usuario();
                alguien.setId(resultado.getString("id"));
                alguien.setClave(resultado.getString("clave"));
                alguien.setNombre(resultado.getString("nombre"));
                alguien.setCorreo(resultado.getString("correo"));
                alguien.setRol(resultado.getString("rol"));
                return alguien;
            }else{
                throw new Exception("Error al consultar el usuario"+ correo+ "<br> Explicacion: ");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage()+ "Error en Correo o en la contraseña");
        }finally{
            if(baseDatos != null){
                baseDatos.desconectar();
            }
        }
    }

    // READ: consultar un usuario por su ID
    public usuario consultarUsuario(String id) throws Exception{
        if (id == null || id.isEmpty()){
            throw new Exception("el Id es necesario para consultar Usuario");
        }
        usuario alguien = null; ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM usuarios WHERE correo=? and clave=?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            sentenciaSQL.setString(1, alguien.getId());
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            if(resultado.next() == true){
                alguien = new usuario();
                alguien.setId(resultado.getString("id"));
                alguien.setClave(resultado.getString("clave"));
                alguien.setNombre(resultado.getString("nombre"));
                alguien.setCorreo(resultado.getString("correo"));
                alguien.setRol(resultado.getString("rol"));
                return alguien;
            }else{
                throw new Exception("Error al consultar el usuario"+ id + "<br/> Explicacion: ");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage()+ "El usuario no existe en la base de datos");
        }finally{
            if(baseDatos != null){
                baseDatos.desconectar();
            }
        }
    }

    // READ: listar todos los usuarios de la tabla
    public usuario [] listarTodos()throws Exception{
        usuario alguien = null; ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM usuarios";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            // Mover cursor al final para contar filas
            resultado.last();
            usuario[] listado = new usuario[resultado.getRow()];
            resultado.beforeFirst();
            // Recorrer resultados y llenar el array
            while (resultado.next()== true) {                 
                alguien = new usuario();
                alguien.setId(resultado.getString("id"));
                alguien.setClave(resultado.getString("clave"));
                alguien.setNombre(resultado.getString("nombre"));
                alguien.setCorreo(resultado.getString("correo"));
                alguien.setRol(resultado.getString("rol"));
                listado[resultado.getRow()] = alguien;
            }
            if(listado.length <=0){
                throw new Exception("Error al listar todos los usuarios"+
                         "<br/> Explicacion: ");
            }
            return listado;
        } catch (Exception error) {
            throw new Exception(error.getMessage()+ "La base de datos esta vacia");
        } finally{
            if(baseDatos != null){
                baseDatos.desconectar();
            }
        }
    }

    public usuario getAlguien() {
        return alguien;
    }

    public void setAlguien(usuario alguien) {
        this.alguien = alguien;
    }

    public ConexionBaseDatos getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(ConexionBaseDatos baseDatos) {
        this.baseDatos = baseDatos;
    }
}
