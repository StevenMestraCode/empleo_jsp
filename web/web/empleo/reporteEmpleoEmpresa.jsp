<%-- 
    Document   : reporteEmpleoEmpresa
    Created on : 7/09/2026, 10:53:47 PM
    Author     : Steven Mestra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.empleo"%>
<%
    if (request.getSession().getAttribute("usuario_login") == null) {
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    empleo[] listado = (empleo[]) request.getSession().getAttribute("empleo_reporte_empresa");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte Empleos por Empresa</title>
</head>
<body>
<center>
    <h1>Empleos por Empresa</h1>
    <form action="<%=request.getContextPath()%>/empleo?accion=reporteEmpresa" method="post">
        <input type="text" name="empresa" placeholder="Nombre de la empresa" required/>
        <input type="submit" value="Consultar"/>
    </form>
    <hr/>
    <%
        if (listado != null && listado.length > 0) {
    %>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Empresa</th>
            <th>Categoría</th>
            <th>Área de Trabajo</th>
            <th>Nivel</th>
            <th>Sueldo</th>
            <th>Funciones</th>
            <th>Cargo del Jefe</th>
        </tr>
        <%
            for (empleo e : listado) {
        %>
        <tr>
            <td><%= e.getId() %></td>
            <td><%= e.getNombre() %></td>
            <td><%= e.getEmpresa() %></td>
            <td><%= e.getCategoria() %></td>
            <td><%= e.getAreaTrabajo() %></td>
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
        } else if (mensaje != null) {
    %>
    <p style="color:red;"><%= mensaje %></p>
    <%
        }
        // Limpiar la sesión para evitar datos viejos
        request.getSession().setAttribute("empleo_reporte_empresa", null);
    %>
</center>
</body>
</html>
