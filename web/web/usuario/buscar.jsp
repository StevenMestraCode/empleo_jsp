<%-- 
    Document   : buscar
    Created on : 2/09/2026, 07:06:26 AM
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
        <title>Buscar Usuario</title>
    </head>
    <body>
        <center>
            <h1>Buscar Usuario</h1>
            <hr/>
            <!-- Formulario para buscar usuario -->
            <form action="<%=request.getContextPath()%>/usuario?accion=buscar" method="post">
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
            <table border="1">
                <tr><td>ID:</td><td><%= alguien.getId() %></td></tr>
                <tr><td>Nombre:</td><td><%= alguien.getNombre() %></td></tr>
                <tr><td>Correo:</td><td><%= alguien.getCorreo() %></td></tr>
                <tr><td>Rol:</td><td><%= alguien.getRol() %></td></tr>
            </table>
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
