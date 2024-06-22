/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package logins;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.sql.*;
import java.math.BigDecimal;


import java.time.LocalDate;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import connection.DBConnection;
import objects.CartItem;
/**
 *
 * @author Dag
 */
public class Buy extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>We are buying Buy</title>");
        out.println("</head>");
        out.println("<body>");

        // Retrieve the JSON payload from the request body
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonPayload = sb.toString();

        // Parse the JSON payload
        JSONObject jsonObject = new JSONObject(jsonPayload);

        // Retrieve the value of the "username" field from the JSON
        String username = jsonObject.getString("name");
        out.println("<h1>Hi " + username + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }
} 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    // Get the cart JSON from the request
    BufferedReader reader = request.getReader();
    StringBuilder jsonBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        jsonBuilder.append(line);
    }
    String cartJson = jsonBuilder.toString();

    // Convert the cart JSON to a List of CartItem objects
    Gson gson = new Gson();
    ArrayList<CartItem> cart = gson.fromJson(cartJson, new TypeToken<List<CartItem>>() {}.getType());

    try (Connection connection = DBConnection.getConnection()) {
        // Insert the order into the "orders" table
        int orderId = insertOrderIntoOrdersTable(connection, request.getSession().getAttribute("user_name").toString());
        System.out.println("order id " + orderId);

        // Insert each cart item into the "order_items" table
        for (CartItem item : cart) {
            insertCartItemIntoOrderItemsTable(connection, orderId, item);
        }

        System.out.println(cart);
        // Send a response indicating the success of the operation
        // response.getWriter().write("Order placed successfully!");
        DBConnection.closeConnection();
    } catch (SQLException e) {
        // Handle the exception
        e.printStackTrace();
    }
}

private int insertOrderIntoOrdersTable(Connection connection, String customerId) {
    System.out.println("inserting to orders table");
    int orderId = -1;
    try {
        // Insert the order into the "orders" table
        String insertOrderQuery = "INSERT INTO orders (customer_id, ordered_date, status) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertOrderQuery,
                Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, customerId);
        preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
        preparedStatement.setString(3, "Pending");
        preparedStatement.executeUpdate();

        // Get the generated order ID
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            orderId = generatedKeys.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("ERROR: inserting to orders table");
        e.printStackTrace();
        // Handle the exception
    }
    System.out.println("DONE inserting to orders table");
    return orderId;
}

private void insertCartItemIntoOrderItemsTable(Connection connection, int orderId, CartItem item) {
    System.out.println("inserting to orders-item table");
    try {
        // Insert the cart item into the "order_items" table
        String insertCartItemQuery = "INSERT INTO ordered_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertCartItemQuery);
        preparedStatement.setInt(1, orderId);
        preparedStatement.setInt(2, item.getId());
        preparedStatement.setInt(3, item.getCount());
        preparedStatement.setBigDecimal(4, BigDecimal.valueOf(item.getPrice()));
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        System.out.println("ERROR: inserting to orders-item table");
        e.printStackTrace();
        // Handle the exception
    }
    System.out.println("DONE inserting to orders-item table");
}


}
