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
    // Usamos la misma clave que el servlet guarda
    usuario alguien = (usuario) request.getSession().getAttribute("usuario_buscarEliminar");
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
    <form action="<%=request.getContextPath()%>/usuario?accion=buscarEliminar" method="post">
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
        <tr><td>ID:</td><td><%= alguien.getId() %></td></tr>
        <tr><td>Nombre:</td><td><%= alguien.getNombre() %></td></tr>
        <tr><td>Correo:</td><td><%= alguien.getCorreo() %></td></tr>
        <tr><td>Rol:</td><td><%= alguien.getRol() %></td></tr>
    </table>
    <form action="<%=request.getContextPath()%>/usuario?accion=eliminar" method="post">
        <input type="hidden" name="id" value="<%= alguien.getId() %>"/>
        <input type="submit" value="Eliminar Usuario"/>
    </form>
    <hr/>
    <%
        }
    %>
    <p style="color:#FF0000;">
        <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %>
    </p>
    <%
        // Limpiamos la sesión para evitar que se quede cargado
        request.getSession().setAttribute("usuario_buscarEliminar", null);
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
