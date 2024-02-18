<%-- 
    Document   : adminOrdersView.jsp
    Created on : Feb 18, 2024, 2:55:07 AM
    Author     : DAGIM
--%>

<%@page import="connection.DBConnection"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <base href="/WebApplication1${pageContext.request.contextPath}">
        <title>Admin Order-view Page</title>
        <link href="../style.css" rel="stylesheet" type="text/css"/>
        <link href="/WebApplication1/boot/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <jsp:include page="../JSP/adminNav.jsp"></jsp:include>
    <body>
        <h1>Hello ADMIN ${pageContext.request.contextPath}</h1>
    <div class="container">
        <div class="table-responsive">
                <table class="table  table-hover table-striped table-bordered">
                    <thead>

                        <tr>
                            <th>No.</th>
                            <th>Order ID</th>
                            <th>Ordered Date</th>
                            <th>status</th>
                            <th>customer ID</th>
                            <th>Total Price</th>
                            <th>approve</th>
                        </tr>
                    </thead> 
                    <%
                        int index = 0; // column index
                        //Getting all products
                        ResultSet totalProduct = DBConnection.getResultFromSqlQuery("SELECT * FROM CustomerOrderTotalCost");
                        while (totalProduct.next()) {
                            index++;
                    %>
                    <tr class="rem1">
                        <td class="invert"><%=index%></td>
                        <td class="invert"><%=totalProduct.getInt(1)%></td>
                        <td class="invert"><%=totalProduct.getDate(2)%></td>
                        
                        
                                                    <%
                                //If order is delivered
                                if (totalProduct.getString(3).equals("Delivered")) {
                            %>
                            <td><p class="badge badge-success bg-success"><strong>Delivered</strong></p></td>
                            <%
                            } else {
                            %>
                            <td><span class="badge bg-danger"><strong>Pending</strong></span></td>
                            <%
                                }
                            %>
                       
                        <td class="invert"><%=totalProduct.getString(4)%></td>
                        <td class="invert"><%=totalProduct.getDouble(5)%></td>
                        <td class="invert">
                            <form action="" method="POST">
                                <input name="id"  type="hidden" value="<%=totalProduct.getString(1)%>"/>
                                <button class="btn btn-link" type="submit">approve</button>
                            </form>
                            
                        </td>
                    </tr>
                    <%
                        }
                    %>

                </table>
            </div>
    </div>
        
    <!--scripts-->
        <script src="/WebApplication1/boot/jquery-3.7.1.min.js" type="text/javascript"></script>
        <script src="/WebApplication1/boot/popper.min.js" type="text/javascript"></script>
        <script src="/WebApplication1/boot/bootstrap.min.js" type="text/javascript"></script>
    </body>
   
</html>
