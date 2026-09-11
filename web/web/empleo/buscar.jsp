<%-- 
    Document   : buscar
    Created on : 2/09/2026, 07:07:51 AM
    Author     : Steven Mestra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar empleo</title>
    </head>
    <body>
         <center>
        <h1>Formulario: Buscar empleo</h1>
        <hr/>
        <form action="<%=request.getContextPath()%>/empleo?accion=buscar" method="POST">
            <input type="hidden" name="accion" value="buscar"/>
            <table border="0" cellspacing="5">
                <tr><td>ID:</td><td><input type="text" name="id" required></td></tr>
                    <tr><td>Nombre:</td><td><input type="text" name="nombre" required></td></tr>
                    <tr><td>Categoría:</td><td><input type="text" name="categoria"></td></tr>
                    <tr><td>Área de Trabajo:</td><td><input type="text" name="areaTrabajo"></td></tr>
                    <tr><td>Empresa:</td><td><input type="text" name="empresa"></td></tr>
                    <tr><td>Nivel:</td><td><input type="text" name="nivel"></td></tr>
                    <tr><td>Sueldo:</td><td><input type="text" name="sueldo"></td></tr>
                    <tr><td>Funciones:</td><td><input type="text" name="funciones"></td></tr>
                    <tr><td>Cargo del Jefe:</td><td><input type="text" name="cargoJefe"></td></tr>
            </table>
            <br/>
                <input type="submit" value="Buscar Empleo"/>
        </form>
        <hr/>
        <hr/>
            <!-- Botón para volver al menú principal -->
            <form action="<%=request.getContextPath()%>/index.jsp" method="get">
                <input type="submit" value="Volver al Menú Principal"/>
            </form>
            <hr/>
       </center>
    </body>
</html>
