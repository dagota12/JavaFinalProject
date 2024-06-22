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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Shopping System</title>
        <link rel ="stylesheet" href="css/style.css"/>
        <link rel ="stylesheet" href="css/bootstrapcss/bootstrap.min.css"/>
        <script src="js/shop.js"  defer type="text/javascript"></script>
        
            <style>
        #profileForm {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 1000;
            opacity: 0; /* Set initial opacity to 0 */
            transition: opacity 0.3s ease; /* Add transition effect */
        }

        #profileForm.show {
            opacity: 1; /* Set opacity to 1 when the form is shown */
        }
    </style>
    </head>
    <body class="bg-light">
        <!--navbar-->
        <nav class="navbar navbar-expand-lg navbar-light px-3 bg-light">
            
    <a class="navbar-brand" href="/WebApplication1" style="font-weight: 500;">Pick<span class="text-success font-weight-bold" style="font-weight: 700;">UP</span></a>
              <button type="button" class="btn btn-success my-2 my-sm-0" data-toggle="modal"
               data-target="#staticBackdrop">
                <i class="fas fa-shopping-cart total-count"></i> Cart
            </button>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active ">
          <a class="nav-link" href="#">Home</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="profile">Profile</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="orders">Orders</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="logout" style="color: #d9534f;">Logout</a>
        </li>
      </ul>

    </div>

  </nav>

        <h4>Store</h4>
   <main>
   <div class="container-fluid bg-trasparent my-4 p-3" style="position: relative;">
       <h1>Welcome to our shopping System</h1>
      <div class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-4 g-3">
            <c:forEach var="product" items="${products}">

<div class="col">
   <div class="card h-100 shadow-sm">
      <img src="images/products/${product.getImage()}" class="card-img-top" style="max-height: 400px;object-fit: cover;" alt="...">
      <div class="card-body">
          
         <div class="clearfix mb-3">
            <span class="float-start badge rounded-pill bg-primary">${product.name}</span>
            <span class="float-end price-hp">${product.price} Etb.</span>
         </div>
         <h5 class="card-title">${product.description}</h5>
         <div class="text-center my-4">
            <button class="btn btn-warning add-to-cart default-btn" data-name="${product.name}" data-id = "${product.id}" data-price="${product.price}">Add to Cart</button>
         </div>
      </div>
   </div>
</div>
      
            </c:forEach>
    </div>
   </div>
</main>
        
        <!--cart model-->
        <!--  end of your HTML body -->
      <!-- Modal -->
      <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="staticBackdropLabel">Your Cart</h5>
                  <button type="button" class="close text-danger" data-dismiss="modal" aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div class="modal-body">
                  <table class="show-cart table">
        
                  </table>
                  <div class="grand-total">Total price:(Etb) <span class="total-cart"></span></div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-success" id="buy">Buy</button>
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  <!-- <button type="button" class="btn btn-danger clear-all">Clear All</button> -->
                </div>
            </div>
         </div>
      </div>
      <!--alert modal-->
      <div id="alertContainer" class="alert position-fixed d-none top-0" role="alert">
  <strong id="alertMessage"> hello world</strong>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>

        <!--scripts-->
        <script>
            var userId = '<%= session.getAttribute("user_name") %>';
        </script>
        <script src="boot/jquery-3.7.1.min.js" type="text/javascript"></script>
        <script src="boot/popper.min.js" type="text/javascript"></script>
        <script src="boot/bootstrap.min.js" type="text/javascript"></script>        
    </body>
</html>
