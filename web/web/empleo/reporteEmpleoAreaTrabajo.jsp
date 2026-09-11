<%-- 
    Document   : reporteEmpleoAreaTrabajo
    Created on : 8/09/2026, 10:52:46 AM
    Author     : Steven Mestra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensaje = request.getParameter("mensaje");
    modelo.empleo[] listado = (modelo.empleo[]) request.getSession().getAttribute("empleo_reporte_area_trabajo");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Reporte Empleos por Área de Trabajo</title>
    </head>
    <body>
        <center>
            <h1>Reporte de Empleos por Área de Trabajo</h1>
            <form action="<%=request.getContextPath()%>/empleo?accion=reporteAreatrabajo" method="post">
                <input type="text" name="areaTrabajo" placeholder="Ingrese área de trabajo" required/>
                <input type="submit" value="Consultar"/>
            </form>
            <hr/>
            <%
                if (listado != null) {
            %>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Categoría</th>
                    <th>Área de Trabajo</th>
                    <th>Empresa</th>
                    <th>Nivel</th>
                    <th>Sueldo</th>
                    <th>Funciones</th>
                    <th>Cargo del Jefe</th>
                </tr>
                <%
                    for (modelo.empleo e : listado) {
                %>
                <tr>
                    <td><%= e.getId() %></td>
                    <td><%= e.getNombre() %></td>
                    <td><%= e.getCategoria() %></td>
                    <td><%= e.getAreaTrabajo() %></td>
                    <td><%= e.getEmpresa() %></td>
                    <td><%= e.getNivel() %></td>
                    <td><%= e.getSueldo() %></td>
                    <td><%= e.getFunciones() %></td>
                    <td><%= e.getCargoJefe() %></td>
                </tr>
                <%
                    }
                %>
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
            <p style="color:red;"><%= (mensaje!=null)?mensaje:"" %></p>
        </center>
    </body>
</html>
