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
    usuario alguien = (usuario) request.getSession().getAttribute("usuario_buscarModificar");
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
        <form action="<%=request.getContextPath()%>/usuario?accion=buscarModificar" method="post">
            ID: <input type="text" name="id"/>
            <input type="submit" value="Buscar"/>
        </form>
        <hr/>
        <!-- Formulario de modificación si se encontró -->
        <%
            if (alguien != null) {
        %>
        <form action="<%=request.getContextPath()%>/usuario?accion=modificar" method="post">
            <input type="hidden" name="id" value="<%= alguien.getId()%>"/>
            Clave: <input type="password" name="clave" value="<%= alguien.getClave()%>"/><br/>
            Nombre: <input type="text" name="nombre" value="<%= alguien.getNombre()%>"/><br/>
            Correo: <input type="text" name="correo" value="<%= alguien.getCorreo()%>"/><br/>
            Rol:
            <select name="rol">
                <option value="Administrador" <%= "Administrador".equals(alguien.getRol()) ? "selected" : ""%>>Administrador</option>
                <option value="Cliente" <%= "Cliente".equals(alguien.getRol()) ? "selected" : ""%>>Cliente</option>
            </select><br/>
            <input type="submit" value="Guardar Cambios"/>
        </form>
        <hr/>
        <hr/>
        <!-- Botón para volver al menú principal -->
        <form action="<%=request.getContextPath()%>/index.jsp" method="get">
            <input type="submit" value="Volver al Menú Principal"/>
        </form>
        <hr/>
        <%
            }
        %>
        <p style="color:red;">
            <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : ""%>
        </p>
        <%
            request.getSession().setAttribute("usuario_buscarModificar", null);
        %>
    </center>
</body>
</html>