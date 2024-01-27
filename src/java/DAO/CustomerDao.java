/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.ProductsDao.connection;
import connection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import objects.Customer;

/**
 *
 * @author Dag
 */
public class CustomerDao {
    public static Connection connection = DBConnection.getConnection();
    
    public static ArrayList<Customer> getCustomers() throws SQLException{
    String query = "SELECT * FROM customers;";
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    
    ArrayList<Customer> customers = new ArrayList<>();
    while(result.next()){
        customers.add(getCustomerObj(result));
    }
    statement.close();
    result.close();
    DBConnection.closeConnection();
    return customers;
    }
    protected static Customer getCustomerObj(ResultSet result) throws SQLException{
        String id = result.getString("customer_id");
        String name = result.getString("customer_name");
        String password = result.getString("password_");
        String city = result.getString("city");
        String phone = result.getString("phone");
        int age = result.getInt("age");
        String gender = result.getString("gender");
        Customer customer = new Customer(name,id,password,city,phone,gender,age);
        
        return customer;
    
    }
    
    public static Customer getCustomer(String user_name) throws SQLException{
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM customers WHERE customer_id= ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,user_name);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return getCustomerObj(result);
        }
        return null;
    
    }
        public static Customer getCustomer(String user_name,String password) throws SQLException{
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM customers WHERE customer_id= ? AND password_= ?;";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,user_name);
        statement.setString(2,password);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return getCustomerObj(result);
        }
        return null;
        
    
    }
public static Customer updateCustomer(Customer new_customer,String user_name) {
        connection = DBConnection.getConnection();
        // SQL query to update customer information
        String updateQuery = "UPDATE customer SET customer_name=?, age=?, gender=?, phone=?, city=? WHERE customer_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            // Set parameters for the update query
            preparedStatement.setString(1, new_customer.getName());
            preparedStatement.setInt(2, new_customer.getAge());
            preparedStatement.setString(3, new_customer.getGender());
            preparedStatement.setString(4, new_customer.getPhone());
            preparedStatement.setString(5, new_customer.getCity());
            preparedStatement.setString(6, user_name);

            // Execute the update query
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                // Successfully updated the customer, return the updated customer
                return new_customer;
            } else {
                // No rows were updated, customer may not exist
                System.out.println("Customer with ID " + new_customer.getId() + " not found for update.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException appropriately
            return null;
        }
    }
    // this ts for testing purpose no use
    public static void main(String[] args) throws SQLException {
        CustomerDao.updateCustomer(new Customer("groot Guardian","i am groot"),"cus/1");
        System.out.println(CustomerDao.getCustomer("cus/2"));
    }
}
