<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->

<html lang = "en">
    <head>
        <title>shopping system</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel ="stylesheet" href="css/bootstrapcss/bootstrap.min.css"/>
        <link href="css/style.css" rel="stylesheet"/>
        

        <!--<script src="js/jquery-3.7.1.min.js" type="text/javascript"></script>-->
        <!--<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>-->
        <!--<script src="js/bootstrap.bundle.js" type="text/javascript"></script>-->
        <!--<script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>-->
        
        <!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>-->
    </head>
    
<body class="bg-light text-light">
        <nav class="navbar navbar-expand-lg navbar-light px-3 bg-light">
          <a class="navbar-brand" href="#">Pick up</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                <a class="nav-link" href="#">Home</a>
              </li>

              <li class="nav-item">
                <a class="nav-link" href="/WebApplication1">customer</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">About</a>
              </li>              
            </ul>
          </div>
        </nav>

    <div class="container-fluid px-4 py-5 my-5 text-center">
        <div class="mb-4">
            <div>
                <h2 class="display-2 fw-bold text-dark">Welcome TO<span class="text-primary"> our   </span>Shop</h2>
            </div>
        </div>
        <div id="main-bg" class=" container-fluid position-absolute lc-block d-grid gap-2 d-sm-flex justify-content-sm-center">
            
        </div>
    </div>
   
    <!--login form-->
    <div class="container w-50 text-dark" style="width: clamp(300px,50%,500px);min-width: 300px">
        <h2 class="h2">For Admin</h2>
        <form action="admin" class="p-0 p-md-4" method="POST">
        <!-- Email input -->
        <div class="form-outline mb-4">
            <input type="text" id="name" name="name" class="form-control" required/>
            <label class="form-label" for="name" >User Name</label>
        </div>

        <!-- Password input -->
        <div class="form-outline mb-4">
          <input type="password" id="password" name="password" class="form-control" required />
          <label class="form-label" for="password">Password</label>
        </div>
        

        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>
        
        <!-- Register buttons -->
        <div class="text-center">
          <p>Not an admin? <a href="/WebApplication1">go to customers login</a></p>
        </div>
        <div class="text-center">
          <p>or admin <a href="admin">admin..</a></p>
        </div>
    </form>
    </div>    
        <!--scripts-->
        <script src="boot/jquery-3.7.1.min.js" type="text/javascript"></script>
        <script src="boot/popper.min.js" type="text/javascript"></script>
        <script src="boot/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
