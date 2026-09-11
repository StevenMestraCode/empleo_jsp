<%-- 
    Document   : listarEmpleo
    Created on : 07/09/2026 07:08:13 AM
    Author     : Steven Mestra
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@page import="modelo.empleo" %>

<%
    empleo listado[] = (empleo[]) session.getAttribute("empleo_listar");
    String mensaje = null;
    if (listado == null || listado.length <= 0) {
        mensaje = "Resultado: 0 Empleos encontrados en el Sistema";
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Listar Empleos</title>
    </head>
    <body>
        <center>
            <h1>Todos los Empleos en el Sistema</h1>
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
                        <th>Categoría</th>
                        <th>Área</th>
                        <th>Empresa</th>
                        <th>Nivel</th>
                        <th>Sueldo</th>
                        <th>Funciones</th>
                        <th>Cargo Jefe</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int contador = 0;
                        for (empleo trabajo : listado) {
                            contador++;
                    %>
                    <tr>
                        <td><%= contador %></td>
                        <td><%= trabajo.getId() %></td>
                        <td><%= trabajo.getNombre() %></td>
                        <td><%= trabajo.getCategoria() %></td>
                        <td><%= trabajo.getAreaTrabajo() %></td>
                        <td><%= trabajo.getEmpresa() %></td>
                        <td><%= trabajo.getNivel() %></td>
                        <td><%= trabajo.getSueldo() %></td>
                        <td><%= trabajo.getFunciones() %></td>
                        <td><%= trabajo.getCargoJefe() %></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
                <hr/>
            <!-- Botón para volver al menú principal -->
            <form action="<%=request.getContextPath()%>/index.jsp" method="get">
                <input type="submit" value="Volver al Menú Principal"/>
            </form>
            <hr/>
            <%
                }
            %>
            <br>
            <a href="../../index.jsp">« VOLVER AL MENÚ</a>
        </center>
    </body>
</html>
