<%-- 
    Document   : adminProductsView
    Created on : Feb 18, 2024, 1:33:34 AM
    Author     : DAGIM
--%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="connection.*"%>
<%@ page import="java.sql.*"%>
<% if (session.getAttribute("loggedIn") == null || (boolean) session.getAttribute("isAdmin") == false) {
        response.sendRedirect("/WebApplication1/admin");
        return;
    }%>

<div class="container"> 
    <div class="top_nav_right">
        <div class="cart box_1">

            <%
                ResultSet resultCount = DBConnection.getResultFromSqlQuery("select count(*) from products");
                resultCount.next();
                int count = resultCount.getInt(1);
            %>
            <h3>


            </h3>
        </div>
    </div>
</div>

<div class="page-head">
    <div class="container">
        <h3>Products</h3>
    </div>
</div>

<div class="electronics">
    <div class="container">
        <div class="ele-bottom-grid">
            <h3>
                Our Products
            </h3>
            <div class="total">
                <i class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></i>
                (
                <%=count%>
                items )
            </div>
            <div class="table-responsive">
                <table class="table  table-hover table-striped table-bordered">
                    <thead>

                        <tr>
                            <th>No.</th>
                            <th>product ID</th>
                            <th>Product Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>stocked_in_date</th>
                            <th>description</th>
                            <th>image</th>
                            <th>edit</th>
                        </tr>
                    </thead> 
                    <%
                        int index = 0; // column index
                        //Getting all products
                        ResultSet totalProduct = DBConnection.getResultFromSqlQuery("select * from products");
                        while (totalProduct.next()) {
                            index++;
                    %>
                    <tr class="rem1">
                        <td class="invert"><%=index%></td>
                        <td class="invert"><%=totalProduct.getString(1)%></td>
                        <td class="invert"><%=totalProduct.getString(2)%></td>
                        <td class="invert"><%=totalProduct.getDouble(3)%></td>
                        <td class="invert"><%=totalProduct.getInt(4)%></td>
                        <td class="invert"><%=totalProduct.getDate(5)%></td>
                        <td class="invert"><%=totalProduct.getString(6)%></td>
                        <td class="invert">
                            <img class="img-fluid" src="${pageContext.request.contextPath}/images/products/<%=totalProduct.getString(7)%>" alt="alt"/>
                        </td>
                            <td class="invert">
                                <form action="admin/products/edit" method="GET">
                                    <input type="text"name="id" value="<%=totalProduct.getString(1)%>" hidden/>
                                    <input type="submit" class="btn-link" value="edit">
                                </form>
                                </td>
                    </tr>
                    <%
                        }
                    %>

                </table>
            </div>

        </div>
    </div>
</div>
