<%-- 
    Document   : Register
    Created on : Jan 29, 2024, 3:45:02 PM
    Author     : DAGIM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JSP Page</title>
        <link href="boot/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-light px-3 bg-light">
          <a class="navbar-brand" href="#">LALA</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                <a class="nav-link" href="/WebApplication1">Home <span class="sr-only">(cur)</span></a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">About</a>
              </li>
              <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
              </li>
            </ul>
          </div>
        </nav>
    <!--main section-->
       <div class="container-fluid px-4 py-5 my-5 text-center">
        <div class="mb-4">
            <div>
                <h2 class="display-2 fw-bold text-dark">Welcome TO<span class="text-primary"> qwerty   </span>Shop</h2>
            </div>
        </div>
        <div id="main-bg" class=" container-fluid position-absolute lc-block d-grid gap-2 d-sm-flex justify-content-sm-center">
            
        </div>
    </div>
   
    <!--register form-->
    <div class="container w-50 text-dark" style="width: clamp(300px,50%,500px);min-width: 300px">
        <h2 class="h2">please fill the fields to Register</h2>
        <form action="register" class="p-0 p-md-4" method="POST">
        <!-- Email input -->
        <div class="form-outline mb-4">
            <input type="text" id="name" name="user_name" class="form-control" required/>
            <label class="form-label" for="user_name" >User Name</label>
        </div>
        <div class="form-outline mb-4">
            <input type="text" id="name" name="name" class="form-control" required/>
            <label class="form-label" for="name" >Full Name</label>
        </div>
        <div class="form-outline mb-4">
            <input type="number" max="100" id="name" name="age" class="form-control" required/>
            <label class="form-label" for="name" >Age</label>
        </div>

        <div class="form-outline mb-4">
            <input type="text" id="city" name="city" class="form-control" required/>
            <label class="form-label" for="city" >City</label>
        </div>
        <div class="form-group mt-2">
            
            <select name="gender" class="form-control" id="gender">
                <option value="male">Male</option>
                <option value="female">Female</option>
            </select>
            <label for="gender">Gender:</label>
        </div>        
        <div class="form-outline mb-4">
            <input type="number" max="9999999999" id="name" name="phone" class="form-control" required/>
            <label class="form-label" for="name" >phone</label>
        </div>        
        <!-- Password input -->
        <div class="form-outline mb-4">
          <input type="password" id="password" name="password" class="form-control" required />
          <label class="form-label" for="password">Password</label>
        </div>
        

        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4">Register   </button>
        
        <!-- Register buttons -->
        <div class="text-center">
          <p>Are you a member? <a href="/WebApplication1">Login</a></p>
        </div>

    </form>
    </div>
    
    
        
    <!--scripts-->
    <script src="boot/jquery-3.7.1.min.js" type="text/javascript"></script>
    <script src="boot/popper.min.js" type="text/javascript"></script>
    <script src="boot/bootstrap.min.js" type="text/javascript"></script>    
    </body>
</html>
