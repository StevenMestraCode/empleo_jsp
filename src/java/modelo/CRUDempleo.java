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
public class CRUDempleo {
    // Atributo que representa un objeto empleo
    private empleo trabajo;
    // Atributo para manejar la conexión con la base de datos
    private ConexionBaseDatos baseDatos;
    
    // Constructor: inicializa un empleo vacío y abre conexión a la BD
    public CRUDempleo() throws Exception {
        this.trabajo = new empleo();
        this.baseDatos = new ConexionBaseDatos();
    }

    // CREATE: método para agregar un empleo a la BD
    public void agregarEmpleo() throws Exception {
        if (trabajo.getId()== null || trabajo.getId().isEmpty()) {
            throw new Exception("El ID es necesario y debe ser mayor a 0");
        }
        String sqlInsert = "INSERT INTO empleos "
                + "(id, nombre, categoria, areaTrabajo, empresa, nivel, sueldo, funciones, cargoJefe) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlInsert);
            sentenciaSQL.setString(1, trabajo.getId());
            sentenciaSQL.setString(2, trabajo.getNombre());
            sentenciaSQL.setString(3, trabajo.getCategoria());
            sentenciaSQL.setString(4, trabajo.getAreaTrabajo());
            sentenciaSQL.setString(5, trabajo.getEmpresa());
            sentenciaSQL.setString(6, trabajo.getNivel());
            sentenciaSQL.setString(7, trabajo.getSueldo());
            sentenciaSQL.setString(8, trabajo.getFunciones());
            sentenciaSQL.setString(9, trabajo.getCargoJefe());
            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al agregar el empleo " + trabajo.getId() +
                    " <br/> Explicación: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    // UPDATE: método para modificar datos de un empleo existente
    public void modificarEmpleo() throws Exception {
        if (trabajo.getId()== null || trabajo.getId().isEmpty() ) {
            throw new Exception("El ID es necesario y debe ser mayor a 0");
        }
        String sqlUpdate = "UPDATE empleos "
                + "SET nombre=?, categoria=?, areaTrabajo=?, empresa=?, nivel=?, sueldo=?, funciones=?, cargoJefe=? WHERE id=?";
        try {
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlUpdate);
            sentenciaSQL.setString(1, trabajo.getNombre());
            sentenciaSQL.setString(2, trabajo.getCategoria());
            sentenciaSQL.setString(3, trabajo.getAreaTrabajo());
            sentenciaSQL.setString(4, trabajo.getEmpresa());
            sentenciaSQL.setString(5, trabajo.getNivel());
            sentenciaSQL.setString(6, trabajo.getSueldo());
            sentenciaSQL.setString(7, trabajo.getFunciones());
            sentenciaSQL.setString(8, trabajo.getCargoJefe());
            sentenciaSQL.setString(9, trabajo.getId());
            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al actualizar el empleo " + trabajo.getId() +
                    " <br/> Explicación: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    // DELETE: método para eliminar un empleo por su ID
    public void eliminarEmpleo() throws Exception {
        if (trabajo.getId()== null || trabajo.getId().isEmpty()) {
            throw new Exception("El ID es necesario y debe ser mayor a 0");
        }
        String sqlDelete = "DELETE FROM empleos WHERE id=?";
        try {
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlDelete);
            sentenciaSQL.setString(1, trabajo.getId());
            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al eliminar el empleo " + trabajo.getId() +
                    " <br/> Explicación: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    // READ: consultar un empleo por su ID
    public empleo consultarEmpleo(String id) throws Exception {
        if (trabajo.getId()== null || trabajo.getId().isEmpty()) {
            throw new Exception("El ID es necesario y debe ser mayor a 0");
        }
        empleo trabajo = null; ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM empleos WHERE id=?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            sentenciaSQL.setString(1, id);
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            if (resultado.next()) {
                trabajo = new empleo();
                trabajo.setId(resultado.getString("id"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setCategoria(resultado.getString("categoria"));
                trabajo.setAreaTrabajo(resultado.getString("areaTrabajo"));
                trabajo.setEmpresa(resultado.getString("empresa"));
                trabajo.setNivel(resultado.getString("nivel"));
                trabajo.setSueldo(resultado.getString("sueldo"));
                trabajo.setFunciones(resultado.getString("funciones"));
                trabajo.setCargoJefe(resultado.getString("cargoJefe"));
                return trabajo;
            } else {
                throw new Exception("Error al consultar el empleo " + id + "<br/> Explicación: No existe");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage() + " El empleo no existe en la base de datos");
        } finally {
            if (baseDatos != null) {
                baseDatos.desconectar();
            }
        }
    }

    // READ: listar todos los empleos de la tabla
    public empleo[] listarTodos() throws Exception {
        empleo trabajo = null; ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM empleos";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            resultado.last();
            empleo[] listado = new empleo[resultado.getRow()];
            resultado.beforeFirst();
            int index = 0;
            while (resultado.next()) {
                trabajo = new empleo();
                trabajo.setId(resultado.getString("id"));
                trabajo.setNombre(resultado.getString("nombre"));
                trabajo.setCategoria(resultado.getString("categoria"));
                trabajo.setAreaTrabajo(resultado.getString("areaTrabajo"));
                trabajo.setEmpresa(resultado.getString("empresa"));
                trabajo.setNivel(resultado.getString("nivel"));
                trabajo.setSueldo(resultado.getString("sueldo"));
                trabajo.setFunciones(resultado.getString("funciones"));
                trabajo.setCargoJefe(resultado.getString("cargoJefe"));
                listado[index++] = trabajo;
            }
            if (listado.length <= 0) {
                throw new Exception("Error al listar todos los empleos <br/> Explicación: No hay registros");
            }
            return listado;
        } catch (Exception error) {
            throw new Exception(error.getMessage() + " La base de datos está vacía");
        } finally {
            if (baseDatos != null) {
                baseDatos.desconectar();
            }
        }
    }

    public empleo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(empleo trabajo) {
        this.trabajo = trabajo;
    }

    public ConexionBaseDatos getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(ConexionBaseDatos baseDatos) {
        this.baseDatos = baseDatos;
    }
}
