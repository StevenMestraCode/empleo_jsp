<%-- 
    Document   : agregarUsuario
    Created on : 07/09/2026 10:19:20 PM
    Author     : Steven Mestra
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Validar si hay sesión activa de usuario
    if (request.getSession().getAttribute("usuario_login") == null) {
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Agregar Usuario al Sistema</title>
    </head>
    <body>
        <center>
            <h1>Agregar Usuario</h1>
            <hr/>
            <form action="<%=request.getContextPath()%>/usuario?accion=agregar" method="post">
                <table>
                    <tr>
                        <th style="text-align: right;">ID:</th>
                        <td><input type="text" name="id" required/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Clave:</th>
                        <td><input type="password" name="clave" required/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Nombre:</th>
                        <td><input type="text" name="nombre" required/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Correo:</th>
                        <td><input type="text" name="correo" required/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Rol:</th>
                        <td>
                            <select name="rol">
                                <option value="Administrador">Administrador</option>
                                <option value="Cliente">Cliente</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><input type="submit" value="GUARDAR"/></th>
                        <th><input type="reset" value="LIMPIAR"/></th>
                    </tr>
                </table>
            </form>
            <hr/>
            <hr/>
            <!-- Botón para volver al menú principal -->
            <form action="<%=request.getContextPath()%>/index.jsp" method="get">
                <input type="submit" value="Volver al Menú Principal"/>
            </form>
            <hr/>
            <!-- Mostrar mensaje dinámico -->
            <p style="color:#FF0000;">
                <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %>
            </p>
        </center>
    </body>
</html>
