<%-- 
    Document   : listarUsuario
    Created on : 07/09/2026 07:08:13 AM
    Author     : Steven Mestra
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@page import="modelo.usuario" %>

<%
    usuario listado[] = (usuario[]) session.getAttribute("usuario_listar");
    String mensaje = null;
    if (listado == null || listado.length <= 0) {
        mensaje = "Resultado: 0 Usuarios encontrados en el Sistema";
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Listar Usuarios</title>
    </head>
    <body>
        <center>
            <h1>Todos los Usuarios en el Sistema</h1>
            <%
                if (mensaje != null) {
                    out.print(mensaje);
                } else {
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>Item</th>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Correo</th>
                        <th>Rol</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int contador = 0;
                        for (usuario alguien : listado) {
                            contador++;
                    %>
                    <tr>
                        <td><%= contador %></td>
                        <td><%= alguien.getId() %></td>
                        <td><%= alguien.getNombre() %></td>
                        <td><%= alguien.getCorreo() %></td>
                        <td><%= alguien.getRol() %></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <%
                }
            %>
            <br>
            <a href="../../index.jsp">« VOLVER AL MENÚ</a>
        </center>
    </body>
</html>
