<%-- 
    Document   : reporteUsuariosNombre
    Created on : 8/09/2026, 10:50:39 AM
    Author     : Steven Mestra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario_inicioSesion") == null) {
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    modelo.usuario[] listado = (modelo.usuario[]) request.getSession().getAttribute("usuario_listarNombre");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Reporte Usuarios por Nombre</title>
    </head>
    <body>
        <center>
            <h1>Usuarios por Nombre</h1>
            <form action="<%=request.getContextPath()%>/usuario?accion=listarPorNombre" method="post">
                <input type="text" name="nombre" placeholder="Ingrese nombre" required/>
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
                    <th>Correo</th>
                    <th>Rol</th>
                </tr>
                <%
                    for (modelo.usuario u : listado) {
                %>
                <tr>
                    <td><%= u.getId() %></td>
                    <td><%= u.getNombre() %></td>
                    <td><%= u.getCorreo() %></td>
                    <td><%= u.getRol() %></td>
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

