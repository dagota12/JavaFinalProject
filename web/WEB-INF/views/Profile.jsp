<%-- 
    Document   : Profile.jsp
    Created on : Jan 24, 2024, 12:28:48 AM
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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Profile</title>
        <link href="boot/bootstrap.min.css" rel="stylesheet" type="text/css"/>
          <style>
            #profileForm {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                z-index: 1000;
                /* opacity: 0; */
                transition: all 0.3s ease; 
            }

            #profileForm.show {
                opacity: 1 !important; 
            }
        </style>
    </head>
    <body>
<nav class="navbar navbar-expand-lg navbar-light px-3 bg-light">
    <a class="navbar-brand" href="#" style="font-weight: 500; font-size: 1.5rem;">
        Pick <span class="text-success font-weight-bold" style="font-weight: 700;">UP</span>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="dashboard">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="profile">Profile</a>
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

        <section class="container mt-3">
            <div class="row">
          <div class="col-lg-4">
            <div class="card mb-4">
              <div class="card-body text-center">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                  class="rounded-circle img-fluid" style="width: 150px;">
                <h5 class="my-3">${customer.name}</h5>
                <div class="d-flex justify-content-center mb-2">
                  <button type="button" class="btn btn-primary" onclick="toggleForm()" class="btn btn-outline-primary ms-1">Edit Profile</button>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-8">
            <div class="card mb-4">
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Full Name</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">${customer.name}</p>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Gender</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">${customer.getGender() != null ? customer.getGender():"Not Avaliable" }</p>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Phone</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">${customer.getPhone() != null ? customer.getPhone():"Not Avaliable" }</p>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Age</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">${customer.getAge() != null ? customer.getAge():"Not Avaliable" }</p>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Address</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">${customer.getCity() != null ? customer.getCity():"Not Avaliable" }</p>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>
        </section>
    <!--edit profile form template-->
        <div id="profileForm" class="container" style="width: clamp(300px,50%,500px);">
          
      
          <div  class="card w-full">
              <div class="card-body">
                  <button type="button" class="close" aria-label="Close" onclick="toggleForm()">
                      <span aria-hidden="true">&times;</span>
                  </button>
      
                  <form action="profile" method="POST">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input  name="name" type="text" class="form-control" id="name" placeholder="Enter your name" required>
                    </div>
<!--                    <div class="form-group">
                        <label for="id">ID:</label>
                        <input type="text" class="form-control" id="id" placeholder="Enter your ID">
                    </div>-->
                    <div class="form-group">
                        <label for="city">City:</label>
                        <input  name="city" type="text" class="form-control" id="city" placeholder="Enter your city">
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input name="phone" type="tel" class="form-control" id="phone" placeholder="Enter your phone number">
                    </div>
                    <div class="form-group">
                        <label for="gender">Gender:</label>
                        <select name="gender" class="form-control" id="gender">
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input name="password" type="password" class="form-control" id="password" placeholder="Enter your password">
                    </div>
                    <div class="form-group">
                        <label for="age">Age:</label>
                        <input  name="age" type="number" class="form-control" id="age" placeholder="Enter your age">
                    </div>
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                </form>
      
                  
              </div>
              
          </div>
      </div>
    <!--    script-->
       <script>
    function toggleForm() {
      $("#profileForm").toggle();
    }
</script>
    <script src="boot/jquery-3.7.1.min.js" type="text/javascript"></script>
    <script src="boot/popper.min.js" type="text/javascript"></script>
    <script src="boot/bootstrap.min.js" type="text/javascript"></script>
    </body>
    


</html>
