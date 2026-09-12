/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CRUDempleo;
import modelo.empleo;

/**
 *
 * @author ASUS
 */

@WebServlet(name = "ServletEmpleo", urlPatterns = {"/empleo"})
public class ServletEmpleo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");

        try {
            if ("agregar".equals(accion)) {
                CRUDempleo crud = new CRUDempleo();
                crud.getTrabajo().setId(request.getParameter("id"));
                crud.getTrabajo().setNombre(request.getParameter("nombre"));
                crud.getTrabajo().setCategoria(request.getParameter("categoria"));
                crud.getTrabajo().setAreaTrabajo(request.getParameter("areaTrabajo"));
                crud.getTrabajo().setEmpresa(request.getParameter("empresa"));
                crud.getTrabajo().setNivel(request.getParameter("nivel"));
                crud.getTrabajo().setSueldo(request.getParameter("sueldo"));
                crud.getTrabajo().setFunciones(request.getParameter("funciones"));
                crud.getTrabajo().setCargoJefe(request.getParameter("cargoJefe"));
                crud.agregarEmpleo();
                response.sendRedirect("web/empleo/agregar.jsp?mensaje=Empleo agregado correctamente");

            } else if ("buscarModificar".equals(accion)) {
                String id = request.getParameter("id");
                try {
                    CRUDempleo crud = new CRUDempleo();
                    empleo trabajo = crud.consultarEmpleo(id);
                    request.getSession().setAttribute("empleo_buscarModificar", trabajo);
                    response.sendRedirect("web/empleo/modificar.jsp?mensaje=Empleo encontrado");
                } catch (Exception e) {
                    response.sendRedirect("web/empleo/modificar.jsp?mensaje=Error al consultar empleo: " + e.getMessage());
                }

            } else if ("modificar".equals(accion)) {
                try {
                    CRUDempleo crud = new CRUDempleo();
                    crud.getTrabajo().setId(request.getParameter("id"));
                    crud.getTrabajo().setNombre(request.getParameter("nombre"));
                    crud.getTrabajo().setCategoria(request.getParameter("categoria"));
                    crud.getTrabajo().setAreaTrabajo(request.getParameter("areaTrabajo"));
                    crud.getTrabajo().setEmpresa(request.getParameter("empresa"));
                    crud.getTrabajo().setNivel(request.getParameter("nivel"));
                    crud.getTrabajo().setSueldo(request.getParameter("sueldo"));
                    crud.getTrabajo().setFunciones(request.getParameter("funciones"));
                    crud.getTrabajo().setCargoJefe(request.getParameter("cargoJefe"));
                    crud.modificarEmpleo();
                    response.sendRedirect("web/empleo/modificar.jsp?mensaje=Empleo modificado correctamente");
                } catch (Exception e) {
                    response.sendRedirect("web/empleo/modificar.jsp?mensaje=Error al modificar empleo: " + e.getMessage());
                }

            } else if ("buscarEliminar".equals(accion)) {
                String id = request.getParameter("id");
                try {
                    CRUDempleo crud = new CRUDempleo();
                    empleo trabajo = crud.consultarEmpleo(id);
                    request.getSession().setAttribute("empleo_buscarEliminar", trabajo);
                    response.sendRedirect("web/empleo/eliminar.jsp?mensaje=Empleo encontrado");
                } catch (Exception e) {
                    response.sendRedirect("web/empleo/eliminar.jsp?mensaje=Error al consultar empleo: " + e.getMessage());
                }

            } else if ("eliminar".equals(accion)) {
                try {
                    CRUDempleo crud = new CRUDempleo();
                    String id = request.getParameter("id");
                    crud.eliminarEmpleo(id);
                    response.sendRedirect("web/empleo/eliminar.jsp?mensaje=Empleo eliminado correctamente");
                } catch (Exception e) {
                    response.sendRedirect("web/empleo/eliminar.jsp?mensaje=Error al eliminar empleo: " + e.getMessage());
                }

            } else if ("listar".equals(accion)) {
                CRUDempleo crud = new CRUDempleo();
                empleo[] listado = crud.listarTodos();
                request.getSession().setAttribute("empleo_listar", listado);
                response.sendRedirect("web/empleo/listar.jsp");

            } else if ("reporteEmpresa".equals(accion)) {
                String empresa = request.getParameter("empresa");
                CRUDempleo crud = new CRUDempleo();
                empleo[] listado = crud.listarPorEmpresa(empresa);
                request.getSession().setAttribute("empleo_reporte_empresa", listado);
                response.sendRedirect("web/empleo/reporteEmpleoEmpresa.jsp");

            } else if ("reporteAreaTrabajo".equals(accion)) {
                String areaTrabajo = request.getParameter("areaTrabajo");
                CRUDempleo crud = new CRUDempleo();
                empleo[] listado = crud.listarPorAreaTrabajo(areaTrabajo);
                request.getSession().setAttribute("empleo_reporte_area_trabajo", listado);
                response.sendRedirect("web/empleo/reporteEmpleoAreaTrabajo.jsp");

            } else {
                response.sendRedirect("web/mensaje.jsp?mensaje=Acción no reconocida");
            }

        } catch (Exception e) {
            response.sendRedirect("web/mensaje.jsp?mensaje=" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ServletEmpleo: controla las acciones CRUD de empleos";
    }
}
