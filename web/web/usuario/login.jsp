<%-- 
    Document   : login
    Created on : 2/09/2026, 07:07:51 AM
    Author     : Steven Mestra
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Capturar mensaje dinámico
    String mensaje = request.getParameter("mensaje");

    // Si ya hay sesión activa, redirigir al menú principal
    if (request.getSession().getAttribute("usuario_login") != null) {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inicio de Sesión</title>
</head>
<body>
<center>
    <h1>Inicio de Sesión</h1>
    <hr/>
    <!-- Formulario de login -->
    <form action="<%=request.getContextPath()%>/usuario?accion=inicioSesion" method="post">
        <table>
            <tr>
                <th style="text-align: right;">Correo:</th>
                <td><input type="text" name="correo" required/></td>
            </tr>
            <tr>
                <th style="text-align: right;">Clave:</th>
                <td><input type="password" name="clave" required/></td>
            </tr>
            <tr>
                <th><input type="submit" value="ENTRAR"/></th>
                <th><input type="reset" value="LIMPIAR"/></th>
            </tr>
        </table>
    </form>
    <hr/>
    <!-- Enlace para recuperar clave -->
    <p>
        ¿Olvidaste tu contraseña?
        <a href="<%=request.getContextPath()%>/web/usuario/recuperacionClave.jsp">Recuperar Clave</a>
    </p>
    <hr/>
    <!-- Mostrar mensaje dinámico -->
    <p style="color:#FF0000;">
        <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %>
    </p>
</center>
</body>
</html>