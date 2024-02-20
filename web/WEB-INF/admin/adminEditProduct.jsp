<%-- 
    Document   : adminEditProduct
    Created on : Feb 19, 2024, 7:50:13 PM
    Author     : davew
--%>
<%@ page import="connection.*"%>
<%@ page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div class="content-wrapper">
                <div class="container">
                    <div class="row pad-botm">
                        <div class="col-md-12">
                            <h4 class="header-line">Edit Product</h4>
                        </div>
                    </div>
                <%
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                    %>
                    <div class="alert alert-danger" id="success">${message}</div>
                
                <%
                    session.removeAttribute("message");
                    }
                %>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-info">
                                <div class="panel-heading">Edit Product</div>
                            <%
                                //Getting input from the admin
                                int id = (String)request.getAttribute("id") != null ? Integer.parseInt((String)request.getAttribute("id")): -1 ;
                                System.out.println("editing PRODUCT ID:"+id);
                                //Querying to database
                                ResultSet updateResult = DBConnection.getResultFromSqlQuery("select * from products where product_id='" + id + "' ");
                                while (updateResult.next()) {
                            %>
                            <div class="panel-body">
                                <form role="form" action="edit" enctype="multipart/form-data"
                                      method="POST" >
                                    <div class="form-group">
                                        <label>Product Id</label> <input class="form-control" type="text" name="pid" value="<%=updateResult.getString("product_id")%>" readonly />
                                    </div>
                                    <div class="form-group">
                                        <label>Enter Name</label> <input class="form-control" type="text" name="productName" value="<%=updateResult.getString("product_name")%>" />
                                    </div>
                                    <div class="form-group">
                                        <label>Price</label> <input class="form-control" type="text" name="price" value="<%=updateResult.getString("price")%>" />
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label> <input class="form-control" type="text" style="min-height: 100px;" name="description" value="<%=updateResult.getString("description")%>" />
                                    </div>
                                    <div class="form-group">
                                        <label>Attach Product Image</label> <input type="file" name="image" />
                                    </div>
                                    <button type="submit" class="btn btn-success">Update Product</button>
                                </form>
                            </div>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <
            <script src="assets/js/jquery-1.10.2.js"></script>
            <script src="assets/js/bootstrap.js"></script>
            <script src="assets/js/custom.js"></script>
        <%
            } else {
                response.sendRedirect("admin");
            }
        %>
    </body>
</html>
