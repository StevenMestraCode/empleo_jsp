<%-- 
    Document   : modificar
    Created on : 2/09/2026, 07:08:13 AM
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
        <title>Modificar Empleo</title>
    </head>
    <body>
        <center>
            <h1>Modificar Empleo</h1>
            <hr/>
            <!-- Formulario para buscar empleo -->
            <form action="<%=request.getContextPath()%>/empleo?accion=buscar&redir=modificar" method="post">
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
            <!-- Formulario de modificación si se encontró -->
            <%
                if (trabajo != null) {
            %>
            <form action="<%=request.getContextPath()%>/empleo?accion=modificar" method="post">
                <input type="hidden" name="id" value="<%= trabajo.getId() %>"/>
                <table>
                    <tr><td>Nombre:</td><td><input type="text" name="nombre" value="<%= trabajo.getNombre() %>"/></td></tr>
                    <tr><td>Categoría:</td><td><input type="text" name="categoria" value="<%= trabajo.getCategoria() %>"/></td></tr>
                    <tr><td>Área de Trabajo:</td><td><input type="text" name="areaTrabajo" value="<%= trabajo.getAreaTrabajo() %>"/></td></tr>
                    <tr><td>Empresa:</td><td><input type="text" name="empresa" value="<%= trabajo.getEmpresa() %>"/></td></tr>
                    <tr><td>Nivel:</td><td><input type="text" name="nivel" value="<%= trabajo.getNivel() %>"/></td></tr>
                    <tr><td>Sueldo:</td><td><input type="text" name="sueldo" value="<%= trabajo.getSueldo() %>"/></td></tr>
                    <tr><td>Funciones:</td><td><input type="text" name="funciones" value="<%= trabajo.getFunciones() %>"/></td></tr>
                    <tr><td>Cargo del Jefe:</td><td><input type="text" name="cargoJefe" value="<%= trabajo.getCargoJefe() %>"/></td></tr>
                </table>
                <input type="submit" value="Modificar Empleo"/>
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
