<%-- 
    Document   : index
    Created on : 10/09/2026, 03:52:26 PM
    Author     : Steven Mestra
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Validar si hay sesión activa de usuario
    if (request.getSession().getAttribute("usuario_login") == null) {
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Menú de la aplicación</title>
    </head>
    <body>
    <center>
        <h1>Menú de la aplicación</h1>
        <hr/>
        <h2>Usuarios</h2>
        <ul>
            <li><a href="<%=request.getContextPath()%>/web/usuario/agregar.jsp">Agregar Usuario</a></li>
            <li><a href="<%=request.getContextPath()%>/web/usuario/buscar.jsp">Buscar Usuario</a></li>
            <li><a href="<%=request.getContextPath()%>/web/usuario/modificar.jsp">Modificar Usuario</a></li>
            <li><a href="<%=request.getContextPath()%>/web/usuario/eliminar.jsp">Eliminar Usuario</a></li>
            <li><a href="<%=request.getContextPath()%>/usuario?accion=listar">Listar Usuarios</a></li>
            <li><a href="<%=request.getContextPath()%>/web/usuario/reporteUsuarioRol.jsp">Reporte por Rol</a></li>
            <li><a href="<%=request.getContextPath()%>/web/usuario/reporteUsuariosNombre.jsp">Reporte por Nombre</a></li>


        </ul>
        <hr/>
        <h2>Empleos</h2>
        <ul>
            <li><a href="<%=request.getContextPath()%>/web/empleo/agregar.jsp">Agregar Empleo</a></li>
            <li><a href="<%=request.getContextPath()%>/web/empleo/buscar.jsp">Buscar Empleo</a></li>
            <li><a href="<%=request.getContextPath()%>/web/empleo/modificar.jsp">Modificar Empleo</a></li>
            <li><a href="<%=request.getContextPath()%>/web/empleo/eliminar.jsp">Eliminar Empleo</a></li>
            <li><a href="<%=request.getContextPath()%>/empleo?accion=listar">Listar Empleos</a></li>
            <li><a href="<%=request.getContextPath()%>/web/empleo/reporteEmpleoEmpresa.jsp">Reporte por Empresa</a></li>
            <li><a href="<%=request.getContextPath()%>/web/empleo/reporteEmpleoAreaTrabajo.jsp">Reporte por Área de Trabajo</a></li>
        </ul>
        <hr/>
        <a href="<%=request.getContextPath()%>/usuario?accion=cerrarSesion">Cerrar Sesión</a>
    </center>
</body>
</html>
