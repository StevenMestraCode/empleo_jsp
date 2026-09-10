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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
            if (accion.equals("agregar")) {
                CRUDempleo crud = new CRUDempleo();
                crud.getTrabajo().setId((request.getParameter("id")));
                crud.getTrabajo().setNombre(request.getParameter("nombre"));
                crud.getTrabajo().setCategoria(request.getParameter("categoria"));
                crud.getTrabajo().setAreaTrabajo(request.getParameter("areaTrabajo"));
                crud.getTrabajo().setEmpresa(request.getParameter("empresa"));
                crud.getTrabajo().setNivel(request.getParameter("nivel"));
                crud.getTrabajo().setSueldo(request.getParameter("sueldo"));
                crud.getTrabajo().setFunciones(request.getParameter("funciones"));
                crud.getTrabajo().setCargoJefe(request.getParameter("cargoJefe"));
                response.sendRedirect("web/empleo/agregar.jsp?mensaje=Empleo agregado correctamente");

            } else if (accion.equals("modificar")) {
                CRUDempleo crud = new CRUDempleo();
                crud.getTrabajo().setId((request.getParameter("id")));
                crud.getTrabajo().setNombre(request.getParameter("nombre"));
                crud.getTrabajo().setCategoria(request.getParameter("categoria"));
                crud.getTrabajo().setAreaTrabajo(request.getParameter("areaTrabajo"));
                crud.getTrabajo().setEmpresa(request.getParameter("empresa"));
                crud.getTrabajo().setNivel(request.getParameter("nivel"));
                crud.getTrabajo().setSueldo(request.getParameter("sueldo"));
                crud.getTrabajo().setFunciones(request.getParameter("funciones"));
                crud.getTrabajo().setCargoJefe(request.getParameter("cargoJefe"));
                response.sendRedirect("web/empleo/modificar.jsp?mensaje=Empleo modificado correctamente");

            } else if (accion.equals("eliminar")) {
                CRUDempleo crud = new CRUDempleo();
                crud.getTrabajo().setId(request.getParameter("id"));
                crud.eliminarEmpleo();
                response.sendRedirect("web/empleo/eliminar.jsp?mensaje=Empleo eliminado correctamente");

            } else if (accion.equals("buscar")) {
                CRUDempleo crud = new CRUDempleo();
                empleo trabajo = crud.consultarEmpleo(request.getParameter("id"));
                request.getSession().setAttribute("empleo_buscar", trabajo);
                response.sendRedirect("web/empleo/buscar.jsp");

            } else if (accion.equals("listar")) {
                CRUDempleo crud = new CRUDempleo();
                empleo[] listado = crud.listarTodos();
                request.getSession().setAttribute("empleo_listar", listado);
                response.sendRedirect("web/empleo/listar.jsp");

            } else if (accion.equals("reporteEmpresa")) {
                String empresa = request.getParameter("empresa");
                CRUDempleo crud = new CRUDempleo();
                empleo[] listado = crud.listarPorEmpresa(empresa);
                request.getSession().setAttribute("empleo_reporte_empresa", listado);
                response.sendRedirect("web/empleo/reporteEmpleoEmpresa.jsp");
            } else if (accion.equals("reporteAreatrabajo")) {
                String areaTrabajo = request.getParameter("areaTrabajo");
                CRUDempleo crud = new CRUDempleo();
                empleo[] listado = crud.listarPorAreaTrabajo(areaTrabajo);
                request.getSession().setAttribute("empleo_reporte_area_trabajo", listado);
                response.sendRedirect("web/empleo/reporteEmpleoAreaTrabajo.jsp");
            }else {
                response.sendRedirect("web/mensaje.jsp?mensaje=Acción no reconocida");
            }

        } catch (Exception e) {
            response.sendRedirect("web/mensaje.jsp?mensaje=" + e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "ServletEmpleo: controla las acciones CRUD de empleos";
    }// </editor-fold>

}
