<%-- 
    Document   : eliminar Usuario
    Created on : 2/09/2026, 07:07:51 AM
    Author     : Steven Mestra
--%>
<%@page import="modelo.usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario_login") == null) {
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    usuario alguien = (usuario) request.getSession().getAttribute("usuario_buscar");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Eliminar Usuario</title>
    </head>
    <body>
        <center>
            <h1>Eliminar Usuario</h1>
            <hr/>
            <!-- Formulario para buscar usuario -->
            <form action="<%=request.getContextPath()%>/usuario?accion=buscar&redir=eliminar" method="post">
                <table>
                    <tr>
                        <td style="text-align: right;">ID:</td>
                        <td><input type="text" name="id"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Buscar"/></td>
                        <td><input type="reset" value="Limpiar"/></td>
                    </tr>
                </table>
            </form>
            <hr/>
            <!-- Mostrar datos si se encontró -->
            <%
                if (alguien != null) {
            %>
            <table>
                <tr><td>Nombre:</td><td><%= alguien.getNombre() %></td></tr>
                <tr><td>Correo:</td><td><%= alguien.getCorreo() %></td></tr>
                <tr><td>Rol:</td><td><%= alguien.getRol() %></td></tr>
            </table>
            <form action="usuario?accion=eliminar" method="post">
                <input type="hidden" name="id" value="<%= alguien.getId() %>"/>
                <input type="submit" value="Eliminar Usuario"/>
            </form>
            <%
                }
            %>
            <p style="color:#FF0000;">
                <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %>
            </p>
            <%
                request.getSession().setAttribute("usuario_buscar", null);
            %>
            <hr/>
            <!-- Botón para volver al menú principal -->
            <form action="<%=request.getContextPath()%>/index.jsp" method="get">
                <input type="submit" value="Volver al Menú Principal"/>
            </form>
            <hr/>
        </center>
    </body>
</html>
