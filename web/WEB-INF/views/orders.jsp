<%-- 
    Document   : orders.jsp
    Created on : Feb 17, 2024, 4:29:28 PM
    Author     : DAGIM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="connection.DBConnection"%>
<%@page import ="java.sql.*"%>
<% if (session.getAttribute("loggedIn") == null) {
    response.sendRedirect("/WebApplication1");
    return;
} %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JSP Page</title>
        <link rel ="stylesheet" href="css/bootstrapcss/bootstrap.min.css"/>
    </head>
 <body>
    <body>
<jsp:include page="../JSP/nav.jsp"></jsp:include>

               
                    <div class="top_nav_right">
                        <div class="cart box_1">
                         
                            <%
                                //Getting count of products of cart
                                ResultSet resultCount = DBConnection.getResultFromSqlQuery("select count(*) from CustomerOrders where customer_id='" + session.getAttribute("user_name") + "'");
                                resultCount.next();
                                int count = resultCount.getInt(1);
                            %>
                            <h3>
                                <div class="total">
                                    <i class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></i>
                                    (
                                    <%=count%>
                                    items )
                                </div>
                            </h3>
                    </div>
                </div>

        <div class="checkout">
            <div class="container">
                <h3>My Orders</h3>
                <div class="table-responsive">
                    <table class="table  table-hover table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>product Name</th>
                                <th>ordered Date</th>
                                <th>Price</th>
                                <th>status</th>
                            </tr>
                        </thead>
                        <%
                            int index = 0; // column index
                            //Getting all products
                            ResultSet totalProduct = DBConnection.getResultFromSqlQuery("select * from CustomerOrders where customer_id='" + session.getAttribute("user_name") + "' ");
                            while (totalProduct.next()) {
                                index++;
                        %>
                        <tr class="rem1">
                            <td class="invert"><%=index%></td>
                            <td class="invert"><%=totalProduct.getString(2)%></td>
                            <td class="invert"><%=totalProduct.getString(3)%></td>
                            <td class="invert"><%=totalProduct.getDouble(4)%></td>
                            <%
                                //If order is delivered
                                if (totalProduct.getString(5).equals("Delivered")) {
                            %>
                            <td><p class="badge badge-success"><strong>Delivered</strong></p></td>
                            <%
                            } else {
                            %>
                            <td><span class="badge bg-danger"><strong>Pending</strong></span></td>
                            <%
                                }
                            %>
                        </tr>
                        <%
                            }
                        %>

                    </table>
                </div>
            </div>
        </div>
                <!--scripts-->
        <script src="boot/jquery-3.7.1.min.js" type="text/javascript"></script>
        <script src="boot/popper.min.js" type="text/javascript"></script>
        <script src="boot/bootstrap.min.js" type="text/javascript"></script>
        <%
         DBConnection.closeConnection();
        %>
    </body>
</html>
