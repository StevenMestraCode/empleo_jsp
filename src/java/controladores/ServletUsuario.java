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
import utilidades.EnviarCorreo;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ServletUsuario", urlPatterns = {"/usuario"})
public class ServletUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        try {
            if ("agregar".equals(accion)) {
                CRUDusuario crud = new CRUDusuario();
                crud.getAlguien().setId(request.getParameter("id"));
                crud.getAlguien().setClave(request.getParameter("clave"));
                crud.getAlguien().setNombre(request.getParameter("nombre"));
                crud.getAlguien().setCorreo(request.getParameter("correo"));
                crud.getAlguien().setRol(request.getParameter("rol"));
                crud.agregarUsuario();
                response.sendRedirect("web/usuario/agregar.jsp?mensaje=Usuario agregado correctamente");

            } else if ("buscar".equals(accion)) {
                String id = request.getParameter("id");
                try {
                    CRUDusuario crud = new CRUDusuario();
                    usuario alguien = crud.consultarUsuario(id);
                    // Guardamos el usuario encontrado en sesión con clave genérica
                    request.getSession().setAttribute("usuario_buscar", alguien);
                    response.sendRedirect("web/usuario/buscar.jsp?mensaje=Usuario encontrado");
                } catch (Exception e) {
                    response.sendRedirect("web/usuario/buscar.jsp?mensaje=Error al consultar usuario: " + e.getMessage());
                }
            } else if ("buscarModificar".equals(accion)) {
                String id = request.getParameter("id");
                try {
                    CRUDusuario crud = new CRUDusuario();
                    usuario alguien = crud.consultarUsuario(id);
                    // Guardamos el usuario encontrado en sesión con clave específica
                    request.getSession().setAttribute("usuario_buscarModificar", alguien);
                    response.sendRedirect("web/usuario/modificar.jsp?mensaje=Usuario encontrado");
                } catch (Exception e) {
                    response.sendRedirect("web/usuario/modificar.jsp?mensaje=Error al consultar usuario: " + e.getMessage());
                }
            } else if ("modificar".equals(accion)) {
                try {
                    CRUDusuario crud = new CRUDusuario();
                    crud.getAlguien().setId(request.getParameter("id"));
                    crud.getAlguien().setClave(request.getParameter("clave"));
                    crud.getAlguien().setNombre(request.getParameter("nombre"));
                    crud.getAlguien().setCorreo(request.getParameter("correo"));
                    crud.getAlguien().setRol(request.getParameter("rol"));
                    crud.modificarUsuario();
                    response.sendRedirect("web/usuario/modificar.jsp?mensaje=Usuario modificado correctamente");
                } catch (Exception e) {
                    response.sendRedirect("web/usuario/modificar.jsp?mensaje=Error al modificar usuario: " + e.getMessage());
                }
            } else if ("buscarEliminar".equals(accion)) {
                // Buscar usuario y mostrarlo en eliminar.jsp
                String id = request.getParameter("id");
                try {
                    CRUDusuario crud = new CRUDusuario();
                    usuario alguien = crud.consultarUsuario(id);
                    request.getSession().setAttribute("usuario_buscarEliminar", alguien);
                    response.sendRedirect("web/usuario/eliminar.jsp?mensaje=Usuario encontrado");
                } catch (Exception e) {
                    response.sendRedirect("web/usuario/eliminar.jsp?mensaje=Error al consultar usuario: " + e.getMessage());
                }

            } else if ("eliminar".equals(accion)) {
                try {
                    CRUDusuario crud = new CRUDusuario();
                    crud.getAlguien().setId(request.getParameter("id"));
                    crud.eliminarUsuario();
                    response.sendRedirect("web/usuario/eliminar.jsp?mensaje=Usuario eliminado correctamente");
                } catch (Exception e) {
                    response.sendRedirect("web/usuario/eliminar.jsp?mensaje=Error al eliminar usuario: " + e.getMessage());
                }

            } else if ("listar".equals(accion)) {
                CRUDusuario crud = new CRUDusuario();
                usuario[] listado = crud.listarTodos();
                request.getSession().setAttribute("usuario_listar", listado);
                response.sendRedirect("web/usuario/listar.jsp");

            } else if ("inicioSesion".equals(accion)) {
                CRUDusuario crud = new CRUDusuario();
                usuario alguien = crud.inicioSesion(request.getParameter("correo"), request.getParameter("clave"));
                request.getSession().setAttribute("usuario_login", alguien);
                response.sendRedirect("index.jsp?mensaje=Bienvenido " + alguien.getNombre());

            } else if ("listarPorRol".equals(accion)) {
                CRUDusuario crud = new CRUDusuario();
                usuario[] listado = crud.listarPorRol(request.getParameter("rol"));
                request.getSession().setAttribute("usuario_listarRol", listado);
                response.sendRedirect("web/usuario/reporteUsuarioRol.jsp");

            } else if ("listarPorNombre".equals(accion)) {
                CRUDusuario crud = new CRUDusuario();
                usuario[] listado = crud.listarPorNombre(request.getParameter("nombre"));
                request.getSession().setAttribute("usuario_listarNombre", listado);
                response.sendRedirect("web/usuario/reporteUsuariosNombre.jsp");

            } else if ("recuperarClave".equals(accion)) {
                String correo = request.getParameter("correo");
                CRUDusuario crud = new CRUDusuario();
                usuario u = crud.buscarPorCorreo(correo);

                if (u != null) {
                    String claveTemporal = u.getClave();
                    try {
                        EnviarCorreo.enviar(correo, "Recuperación de clave",
                                "Tu clave actual es: " + claveTemporal);
                        response.sendRedirect("web/usuario/login.jsp?mensaje=Clave enviada al correo");
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.sendRedirect("web/usuario/recuperarClave.jsp?mensaje=Error al enviar correo");
                    }
                } else {
                    response.sendRedirect("web/usuario/recuperarClave.jsp?mensaje=Correo no registrado");
                }

            } else if ("cerrarSesion".equals(accion)) {
                try {
                    request.getSession().invalidate(); // invalida toda la sesión
                    response.sendRedirect("web/usuario/login.jsp?mensaje=Sesion cerrada correctamente");
                } catch (Exception e) {
                    response.sendRedirect("web/mensaje.jsp?mensaje=Error al cerrar sesión: " + e.getMessage());
                }
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
        return "ServletUsuario: controla las acciones CRUD de Usuario";
    }
}
