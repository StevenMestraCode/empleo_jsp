<%-- 
    Document   : reporteUsuarioRol
    Created on : 7/09/2026, 10:44:02 PM
    Author     : Steven Mestra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario_login") == null) {
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    modelo.usuario[] listado = (modelo.usuario[]) request.getSession().getAttribute("usuario_reporte_rol");
%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Reporte Usuarios por Rol</title></head>
    <body>
        <center>
            <h1>Usuarios por Rol</h1>
            <form action="<%=request.getContextPath()%>/usuario?accion=reporteRol" method="post">
                <select name="rol">
                    <option value="Administrador">Administrador</option>
                    <option value="Cliente">Cliente</option>
                </select>
                <input type="submit" value="Consultar"/>
            </form>
            <hr/>
            <%
                if (listado != null) {
            %>
            <table border="1">
                <tr><th>ID</th><th>Nombre</th><th>Correo</th><th>Rol</th></tr>
                <%
                    for (modelo.usuario u : listado) {
                %>
                <tr>
                    <td><%= u.getId() %></td>
                    <td><%= u.getNombre() %></td>
                    <td><%= u.getCorreo() %></td>
                    <td><%= u.getRol() %></td>
                </tr>
                <%
                    }
                %>
            </table>
            <hr/>
            <!-- Botón para volver al menú principal -->
            <form action="<%=request.getContextPath()%>/index.jsp" method="get">
                <input type="submit" value="Volver al Menú Principal"/>
            </form>
            <hr/>
            <%
                }
            %>
            <p style="color:red;"><%= (mensaje!=null)?mensaje:"" %></p>
            
        </center>
    </body>
</html>
