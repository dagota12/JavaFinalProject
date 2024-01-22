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
    <body class="bg-light">
        <nav class="navbar navbar-expand-lg navbar-light px-3 bg-light">
          <a class="navbar-brand" href="#">LALA</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
              </li>
              <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
              </li>
            </ul>
          </div>
        </nav>

        <h4>Store</h4>
   <main>
   <div class="container-fluid bg-trasparent my-4 p-3" style="position: relative;">
       <h1>ONce</h1>
      <div class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-4 g-3">
            <c:forEach var="product" items="${myList}">

         <div class="col">
            <div class="card h-100 shadow-sm">
               <img src="https://www.freepnglogos.com/uploads/notebook-png/download-laptop-notebook-png-image-png-image-pngimg-2.png" class="card-img-top" alt="..."> 
               <div class="card-body">
                  <div class="clearfix mb-3"> <span class="float-start badge rounded-pill bg-primary">${product.name}</span> <span class="float-end price-hp">${product.price}&euro;</span> </div>
                  <h5 class="card-title">${product.description}</h5>
                  <div class="text-center my-4"> <a href="#" class="btn btn-warning">Check offer</a> </div>
               </div>
            </div>
         </div>
      
            </c:forEach>
    </div>
   </div>
</main>

        <input type="text" class="name"/>

        <button class="btn">oma${s}</button>
        <!--scripts-->
        <script src="boot/jquery-3.7.1.min.js" type="text/javascript"></script>
        <script src="boot/popper.min.js" type="text/javascript"></script>
        <script src="boot/bootstrap.min.js" type="text/javascript"></script>        
    </body>
</html>
