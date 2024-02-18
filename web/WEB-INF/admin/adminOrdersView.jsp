<%-- 
    Document   : adminOrdersView.jsp
    Created on : Feb 18, 2024, 2:55:07 AM
    Author     : DAGIM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <base href="/WebApplication1${pageContext.request.contextPath}">
        <title>Admin Order-view Page</title>
        <link href="../style.css" rel="stylesheet" type="text/css"/>
        <link href="/WebApplication1/boot/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <jsp:include page="../JSP/adminNav.jsp"></jsp:include>
    <body>
        <h1>Hello World! ${pageContext.request.contextPath}</h1>
        
    <!--scripts-->
        <script src="/WebApplication1/boot/jquery-3.7.1.min.js" type="text/javascript"></script>
        <script src="/WebApplication1/boot/popper.min.js" type="text/javascript"></script>
        <script src="/WebApplication1/boot/bootstrap.min.js" type="text/javascript"></script>
    </body>
   
</html>
