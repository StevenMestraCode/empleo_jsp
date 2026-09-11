<%-- 
    Document   : mensaje
    Created on : 7/09/2026, 10:18:15 PM
    Author     : Steven Mestra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje del sistema</title>
    </head>
    <body>
    <center>
        <h1>
            <%=request.getParameter("mensaje")%>
        </h1>
        <hr>
        <a href="usuario/login.jsp"><<< Volver :::</a>
    </center>
    </body>
</html>
