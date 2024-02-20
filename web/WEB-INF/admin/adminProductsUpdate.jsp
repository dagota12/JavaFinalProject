<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin products-update</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin Dashboard Page</title>
        <link href="/WebApplication1/boot/style.css" rel="stylesheet" type="text/css"/>
        <link href="/WebApplication1/boot/bootstrap.min.css" rel="stylesheet" type="text/css"/
    </head>
    <body>
        <%
            //Checking whether admin in session or not
            if (session.getAttribute("isAdmin") != null && (boolean)session.getAttribute("isAdmin")) {
        %>
        <jsp:include page="../JSP/adminNav.jsp"></jsp:include>
            <div class="content-wrapper">
                <div class="container">
                    <div class="row pad-botm">
                        <div class="col-md-12">
                            <h4 class="header-line">Add Product</h4>
                        </div>
                    </div>
                <%
                    String message = (String) session.getAttribute("message");
                    if (message != null) {
                        session.removeAttribute("message");
                %>
                <div class="alert alert-danger" id="success">Product added successfully.</div>
                <%
                    }
                %>
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-info">
                            <div class="panel-heading">Add Product</div>
                            <div class="panel-body">
                                <form role="form" action="products/add" method="POST"
                                      enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label>Enter Name</label> <input class="form-control" type="text" name="productName" required />
                                    </div>
                                    <div class="form-group">
                                        <label>Price</label> <input class="form-control" type="number" name="price" required/>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label> <input class="form-control" type="text" style="min-height: 100px;" name="description" required/>
                                    </div>
<!--                                    <div class="form-group">
                                        <label>Stocked-in-date</label> <input class="form-control" type="date" style="min-height: 100px;" name="stockedInDate" required/>
                                    </div>-->
                                    <div class="form-group">
                                        <label>Attach Product Image</label> <input type="file" name="image" />
                                    </div>

                                    <button type="submit" class="btn btn-success" onclick="return confirm('Are you sure Do you want to add this product?');">Add Product</button>
                                    <button type="reset" class="btn btn-danger">Reset</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <!--add products-->
 
        <%
            } else {
                response.sendRedirect("/WebApplication1/admin");
            }
        %>
        <!--scripts-->
        <script src="/WebApplication1/boot/jquery-3.7.1.min.js" type="text/javascript"></script>
        <script src="/WebApplication1/boot/popper.min.js" type="text/javascript"></script>
        <script src="/WebApplication1/boot/bootstrap.min.js" type="text/javascript"></script>

    </body>
</html>