<%-- 
    Document   : modificar Usuario
    Created on : 7/09/2026, 10:18:15 PM
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
    <title>Modificar Usuario</title>
</head>
<body>
<center>
    <h1>Modificar Usuario</h1>
    <hr/>
    <!-- Formulario para buscar usuario -->
    <form action="<%=request.getContextPath()%>/usuario?accion=buscar&redir=modificar" method="post">
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
    <!-- Formulario de modificación si se encontró -->
    <%
        if (alguien != null) {
    %>
    <form action="<%=request.getContextPath()%>/usuario?accion=modificar" method="post">
        <input type="hidden" name="id" value="<%= alguien.getId()%>"/>
        <table>
            <tr><td>Clave:</td><td><input type="password" name="clave" value="<%= alguien.getClave()%>"/></td></tr>
            <tr><td>Nombre:</td><td><input type="text" name="nombre" value="<%= alguien.getNombre()%>"/></td></tr>
            <tr><td>Correo:</td><td><input type="text" name="correo" value="<%= alguien.getCorreo()%>"/></td></tr>
            <tr><td>Rol:</td>
                <td>
                    <select name="rol">
                        <option value="Administrador" <%= "Administrador".equals(alguien.getRol()) ? "selected" : ""%>>Administrador</option>
                        <option value="Cliente" <%= "Cliente".equals(alguien.getRol()) ? "selected" : ""%>>Cliente</option>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Guardar Cambios"/>
    </form>
    <hr/>
    <!-- Botón para volver al menú principal -->
    <form action="<%=request.getContextPath()%>/index.jsp" method="get">
        <input type="submit" value="Volver al Menú Principal"/>
    </form>
    <hr/>
    <%
        }
    %>
    <p style="color:#FF0000;">
        <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : ""%>
    </p>
    <%
        request.getSession().setAttribute("usuario_buscar", null);
    %>
</center>
</body>
</html>
