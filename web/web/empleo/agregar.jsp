<%@page contentType="text/html" pageEncoding="UTF-8"%>
@WebServlet(name = "ServletEmpleo", urlPatterns = {"/empleo"})
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Agregar Empleo</title>
    </head>
    <body>
        <center>
            <h2>Formulario: Agregar Empleo</h2>
            <hr/>
            <form action="empleo" method="post">
                <!-- Campo oculto para indicar la acción -->
                <input type="hidden" name="accion" value="agregar"/>

                <table border="0" cellpadding="5">
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
                <input type="submit" value="Agregar Empleo"/>
            </form>
            <hr/>
        </center>
    </body>
</html>
