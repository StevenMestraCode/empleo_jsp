<%-- 
    Document   : agregar
    Created on : 7/09/2026, 10:19:02 PM
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
        <title>Agregar Empleo al Sistema</title>
    </head>
    <body>
        <center>
            <h1>Agregar Empleo</h1>
            <hr/>
            <form action="<%=request.getContextPath()%>/empleo?accion=agregar" method="post">
                <table>
                    <tr>
                        <th style="text-align: right;">ID:</th>
                        <td><input type="text" name="id" required/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Nombre:</th>
                        <td><input type="text" name="nombre" required/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Categoría:</th>
                        <td><input type="text" name="categoria"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Área de Trabajo:</th>
                        <td><input type="text" name="areaTrabajo"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Empresa:</th>
                        <td><input type="text" name="empresa"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Nivel:</th>
                        <td><input type="text" name="nivel"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Sueldo:</th>
                        <td><input type="text" name="sueldo"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Funciones:</th>
                        <td><input type="text" name="funciones"/></td>
                    </tr>
                    <tr>
                        <th style="text-align: right;">Cargo del Jefe:</th>
                        <td><input type="text" name="cargoJefe"/></td>
                    </tr>
                    <tr>
                        <th><input type="submit" value="GUARDAR"/></th>
                        <th><input type="reset" value="LIMPIAR"/></th>
                    </tr>
                </table>
            </form>
                <hr/>
            <!-- Botón para volver al menú principal -->
            <form action="<%=request.getContextPath()%>/index.jsp" method="get">
                <input type="submit" value="Volver al Menú Principal"/>
            </form>
            <hr/>
            <hr/>
            <!-- Mostrar mensaje dinámico -->
            <p style="color:#FF0000;">
                <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %>
            </p>
        </center>
    </body>
</html>

