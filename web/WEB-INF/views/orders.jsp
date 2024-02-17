<%-- 
    Document   : orders.jsp
    Created on : Feb 17, 2024, 4:29:28 PM
    Author     : DAGIM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("loggedIn") == null) {
    response.sendRedirect("/WebApplication1");
    return;
} %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
