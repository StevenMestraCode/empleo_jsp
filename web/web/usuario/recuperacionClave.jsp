<%-- 
    Document   : recuperacionContraseña
    Created on : 8/09/2026, 10:53:20 AM
    Author     : Steven Mestra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Recuperar Clave</title>
    </head>
    <body>
        <center>
            <h1>Recuperar Clave</h1>
            <form action="<%=request.getContextPath()%>/usuario?accion=recuperarClave" method="post">
                <table>
                    <tr>
                        <td>Correo:</td>
                        <td><input type="email" name="correo" required/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Enviar Clave"/></td>
                        <td><input type="reset" value="Limpiar"/></td>
                    </tr>
                </table>
            </form>
                <hr/>
            <!-- Botón para volver al menú principal -->
            <form action="<%=request.getContextPath()%>/index.jsp" method="get">
                <input type="submit" value="Volver al Menú Principal"/>
            </form>
            <hr/>
            <p style="color:red;">
                <%= (request.getParameter("mensaje")!=null)?request.getParameter("mensaje"):"" %>
            </p>
        </center>
    </body>
</html>

