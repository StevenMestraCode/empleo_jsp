<%-- 
    Document   : eliminar
    Created on : 2/09/2026, 07:07:51 AM
    Author     : Steven Mestra
--%>
<%@page import="modelo.empleo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario_login") == null) {
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    empleo trabajo = (empleo) request.getSession().getAttribute("empleo_buscar");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Eliminar Empleo</title>
    </head>
    <body>
        <center>
            <h1>Eliminar Empleo</h1>
            <hr/>
            <!-- Formulario para buscar empleo -->
            <form action="<%=request.getContextPath()%>/empleo?accion=buscar&redir=eliminar" method="post">
                <table>
                    <tr>
                        <td style="text-align: right;">ID:</td>
                        <td><input type="text" name="id"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Buscar"/></td>
                        <td><input type="reset" value="Limpiar"/></td>
                    </tr>
                </table>
            </form>
            <hr/>
            <!-- Mostrar datos si se encontró -->
            <%
                if (trabajo != null) {
            %>
            <table>
                <tr><td>Nombre:</td><td><%= trabajo.getNombre() %></td></tr>
                <tr><td>Empresa:</td><td><%= trabajo.getEmpresa() %></td></tr>
                <tr><td>Categoría:</td><td><%= trabajo.getCategoria() %></td></tr>
                <tr><td>Área:</td><td><%= trabajo.getAreaTrabajo() %></td></tr>
                <tr><td>Nivel:</td><td><%= trabajo.getNivel() %></td></tr>
                <tr><td>Sueldo:</td><td><%= trabajo.getSueldo() %></td></tr>
            </table>
            <form action="empleo?accion=eliminar" method="post">
                <input type="hidden" name="id" value="<%= trabajo.getId() %>"/>
                <input type="submit" value="Eliminar Empleo"/>
            </form>
                <hr/>
            <!-- Botón para volver al menú principal -->
            <form action="<%=request.getContextPath()%>/index.jsp" method="get">
                <input type="submit" value="Volver al Menú Principal"/>
            </form>
            <hr/>
            <%
                }
            %>
            <p style="color:#FF0000;">
                <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %>
            </p>
            <%
                request.getSession().setAttribute("empleo_buscar", null);
            %>
        </center>
    </body>
</html>
