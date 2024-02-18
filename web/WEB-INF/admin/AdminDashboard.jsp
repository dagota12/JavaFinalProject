<%-- 
    Document   : newjsp
    Created on : Jan 23, 2024, 1:34:07 PM
    Author     : Dagim
--%>
<% if (session.getAttribute("loggedIn") == null || (boolean) session.getAttribute("isAdmin") == false) {
        response.sendRedirect("/WebApplication1");
        return;
    }%>
<%@ page import="connection.*"%>
<%@ page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--<base href="/WebApplication1${pageContext.request.contextPath}">-->
        <title>Admin Dashboard Page</title>
        <link href="../style.css" rel="stylesheet" type="text/css"/>
        <link href="/WebApplication1/boot/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <jsp:include page ="../JSP/adminNav.jsp"></jsp:include>
<div class="content-wrapper">
                <div class="container">
                    <div class="row pad-botm">
                        <div class="col-md-12">
                            <h4 class="header-line">ADMIN DASHBOARD</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-4 col-xs-6">
                            <div class="alert alert-info back-widget-set text-center">
                                <i class="fa fa-history fa-5x"></i>
                            <%
                                //Count product query
                                ResultSet totalProduct = DBConnection.getResultFromSqlQuery("select count(*) from products");
                                totalProduct.next();
                                int allProducts = totalProduct.getInt(1);
                            %>
                            <h3>
                                <%=allProducts%>
                            </h3>
                            Total Products
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6">
                        <div class="alert alert-success back-widget-set text-center">
                            <i class="fa fa-users fa-5x"></i>
                            <%
                                //Count customer query
                                ResultSet totalCustomer = DBConnection.getResultFromSqlQuery("select count(*) from customers");
                                totalCustomer.next();
                                int allCustomer = totalCustomer.getInt(1);
                            %>
                            <h3><%=allCustomer%></h3>
                            Total Customers
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6">
                        <div class="alert alert-warning back-widget-set text-center">
                            <i class="fa fa-recycle fa-5x"></i>
                            <%
                                //Count orders query
                                ResultSet totalOrders = DBConnection.getResultFromSqlQuery("select count(*) from orders");
                                totalOrders.next();
                                int allOrders = totalOrders.getInt(1);
                            %>
                            <h3><%=allOrders%></h3>
                            Total Orders
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <% DBConnection.closeConnection();%>
    <jsp:include page="adminProductsView.jsp"></jsp:include>
        <!--scripts-->
        <script src="/WebApplication1/boot/jquery-3.7.1.min.js" type="text/javascript"></script>
        <script src="/WebApplication1/boot/popper.min.js" type="text/javascript"></script>
        <script src="/WebApplication1/boot/bootstrap.min.js" type="text/javascript"></script>


    </body>
</html>
