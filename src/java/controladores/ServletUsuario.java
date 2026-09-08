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
import modelo.CRUDusuario;
import modelo.usuario;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ServletUsuario", urlPatterns = {"/usuario"})
public class ServletUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     // Método central: recibe todas las peticiones y decide qué hacer
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion"); // capturar la acción enviada desde el formulario

        try {
            if (accion.equals("agregar")) {
                CRUDusuario crud = new CRUDusuario();
                crud.getAlguien().setId(request.getParameter("id"));
                crud.getAlguien().setClave(request.getParameter("clave"));
                crud.getAlguien().setNombre(request.getParameter("nombre"));
                crud.getAlguien().setCorreo(request.getParameter("correo"));
                crud.getAlguien().setRol(request.getParameter("rol"));
                crud.agregarUsuario();
                response.sendRedirect("web/usuario/agregar.jsp?mensaje=Usuario agregado correctamente");

            } else if (accion.equals("modificar")) {
                CRUDusuario crud = new CRUDusuario();
                crud.getAlguien().setId(request.getParameter("id"));
                crud.getAlguien().setClave(request.getParameter("clave"));
                crud.getAlguien().setNombre(request.getParameter("nombre"));
                crud.getAlguien().setCorreo(request.getParameter("correo"));
                crud.getAlguien().setRol(request.getParameter("rol"));
                crud.modificarUsuario();
                response.sendRedirect("web/usuario/modificar.jsp?mensaje=Usuario modificado correctamente");

            } else if (accion.equals("eliminar")) {
                CRUDusuario crud = new CRUDusuario();
                crud.getAlguien().setId(request.getParameter("id"));
                crud.eliminarUsuario();
                response.sendRedirect("web/usuario/eliminar.jsp?mensaje=Usuario eliminado correctamente");

            } else if (accion.equals("buscar")) {
                CRUDusuario crud = new CRUDusuario();
                usuario alguien = crud.consultarUsuario(request.getParameter("id"));
                request.getSession().setAttribute("usuario_buscar", alguien);
                response.sendRedirect("web/usuario/buscar.jsp");

            } else if(accion.equals("inicioSesion")){
                CRUDusuario crud= new CRUDusuario();
                usuario alguien = crud.inicioSesion(request.getParameter("correo"), request.getParameter("clave"));
                request.getSession().setAttribute("usuario_inicioSesion", alguien);
                response.sendRedirect("index.jsp?mensaje=Bienvenido " + alguien.getNombre());
            }else if (accion.equals("listar")) {
                CRUDusuario crud = new CRUDusuario();
                usuario[] listado = crud.listarTodos();
                request.getSession().setAttribute("usuario_listar", listado);
                response.sendRedirect("web/usuario/listar.jsp");

            } else {
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
        return "Servletusuario: controla las acciones CRUD de Usuario";
    }// </editor-fold>

}
