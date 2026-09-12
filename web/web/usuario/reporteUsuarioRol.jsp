<%-- 
    Document   : reporteUsuarioRol
    Created on : 7/09/2026, 10:44:02 PM
    Author     : Steven Mestra
--%>
<%@page import="modelo.usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario_login") == null) {
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    usuario[] listado = (usuario[]) request.getSession().getAttribute("usuario_listarRol");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte Usuarios por Rol</title>
</head>
<body>
<center>
    <h1>Usuarios por Rol</h1>
    <hr/>
    <!-- Formulario para escribir el rol -->
    <form action="<%=request.getContextPath()%>/usuario?accion=listarPorRol" method="post">
        <label>Escriba el rol:</label>
        <input type="text" name="rol"/>
        <input type="submit" value="Consultar"/>
    </form>
    <hr/>
    <%
        if (listado != null && listado.length > 0) {
    %>
    <table border="1">
        <tr><th>ID</th><th>Nombre</th><th>Correo</th><th>Rol</th></tr>
        <% for (usuario u : listado) { %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getNombre() %></td>
            <td><%= u.getCorreo() %></td>
            <td><%= u.getRol() %></td>
        </tr>
        <% } %>
    </table>
    <hr/>
    <% } %>
    <p style="color:red;"><%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %></p>
    <hr/>
    <form action="<%=request.getContextPath()%>/index.jsp" method="get">
        <input type="submit" value="Volver al Menú Principal"/>
    </form>
</center>
</body>
</html>
