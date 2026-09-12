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

    private empleo trabajo;
    private ConexionBaseDatos baseDatos;

    public CRUDempleo() throws Exception {
        this.trabajo = new empleo();
        this.baseDatos = new ConexionBaseDatos();
    }

    // CREATE
    public void agregarEmpleo() throws Exception {
        if (trabajo.getId() == null || trabajo.getId().isEmpty()) {
            throw new Exception("El ID es necesario y debe ser mayor a 0");
        }
        String sqlInsert = "INSERT INTO empleo "
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
            throw new Exception("Error al agregar el empleo " + trabajo.getId()
                    + " <br/> Explicación: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    // UPDATE
    public void modificarEmpleo() throws Exception {
        if (trabajo.getId() == null || trabajo.getId().isEmpty()) {
            throw new Exception("El ID es necesario y debe ser mayor a 0");
        }
        String sqlUpdate = "UPDATE empleo SET nombre=?, categoria=?, areaTrabajo=?, empresa=?, nivel=?, sueldo=?, funciones=?, cargoJefe=? WHERE id=?";
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
            throw new Exception("Error al actualizar el empleo " + trabajo.getId()
                    + " <br/> Explicación: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    // DELETE
    public void eliminarEmpleo(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new Exception("El ID es necesario y debe ser mayor a 0");
        }
        String sqlDelete = "DELETE FROM empleo WHERE id=?";
        try {
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlDelete);
            sentenciaSQL.setString(1, id);
            int filas = baseDatos.actualizar(sentenciaSQL);
            if (filas == 0) {
                throw new Exception("No se encontró empleo con ID " + id);
            }
        } catch (Exception error) {
            throw new Exception("Error al eliminar el empleo " + id
                    + " <br/> Explicación: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }

    // READ: consultar por ID
    public empleo consultarEmpleo(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new Exception("El ID es necesario y debe ser mayor a 0");
        }
        empleo trabajo = null;
        ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM empleo WHERE id=?";
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
                throw new Exception("No existe empleo con ID " + id);
            }
        } finally {
            if (baseDatos != null) {
                baseDatos.desconectar();
            }
        }
    }

    // READ: listar todos
    public empleo[] listarTodos() throws Exception {
        empleo trabajo = null;
        ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM empleo";
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
            return listado;
        } finally {
            if (baseDatos != null) {
                baseDatos.desconectar();
            }
        }
    }

    // READ: listar por área
    public empleo[] listarPorAreaTrabajo(String areaTrabajo) throws Exception {
        empleo trabajo = null;
        ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM empleo WHERE areaTrabajo=?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            sentenciaSQL.setString(1, areaTrabajo);
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            resultado.last();
            empleo[] listado = new empleo[resultado.getRow()];
            resultado.beforeFirst();
            int i = 0;
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
                listado[i++] = trabajo;
            }
            return listado;
        } finally {
            if (baseDatos != null) {
                baseDatos.desconectar();
            }
        }
    }

    // READ: listar por empresa
    public empleo[] listarPorEmpresa(String empresa) throws Exception {
        empleo trabajo = null;
        ConexionBaseDatos baseDatos = null;
        String sqlSelect = "SELECT * FROM empleo WHERE empresa=?";
        try {
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            sentenciaSQL.setString(1, empresa);
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            resultado.last();
            empleo[] listado = new empleo[resultado.getRow()];
            resultado.beforeFirst();
            int i = 0;
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
                listado[i++] = trabajo;
            }
            return listado;
        } catch (Exception error) {
            throw new Exception("Error al listar empleos por empresa: " + error.getMessage());
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
