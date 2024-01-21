<%-- 
    Document   : myJsp
    Created on : Jan 16, 2024, 10:55:05 AM
    Author     : Dag
--%>

<%@page import="objects.Product"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import ="DAO.ProductsDao"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crash at my place</title>
       <link rel ="stylesheet" href="css/style.css"/>
        <link rel ="stylesheet" href="css/bootstrapcss/bootstrap.min.css"/>
        <script src="js/shop.js"  defer type="text/javascript"></script>
    </head>
    <body class="bg-primary">
        <h1>Hello World! ${1+1} ${request.getSession().setAttribute("user")}</h1>
            <h1>List of Fruits</h1>

    <c:set var="s" value="${89}" />
    <%
    List<Product> products = ProductsDao.getProducts();
    %>
    <ul>
        <c:forEach items= "${products}" var="product">
            <li>${product}</li>
        </c:forEach>
    </ul>
        <input type="text" class="name"/>
     
        <button class="btn">oma${s}</button>
        <p class="h4 text-info"><%= products  %></p>
       <!--scripts-->
    <script src="boot/jquery-3.7.1.min.js" type="text/javascript"></script>
    <script src="boot/popper.min.js" type="text/javascript"></script>
    <script src="boot/bootstrap.min.js" type="text/javascript"></script>        
    </body>
</html>
