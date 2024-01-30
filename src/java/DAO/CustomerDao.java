/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import  DAO.ProductsDao;
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
    /**
     * accepts a resultsrt
     * @return a customer object
     */
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
    //get a customer from DB with certain id
    public static Customer getCustomer(String user_name) throws SQLException{
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM customers WHERE customer_id= ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,user_name);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return getCustomerObj(result);
        }
        DBConnection.closeConnection();
        return null;
    
    }
    //get a customer from DB with certain id and password
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
        DBConnection.closeConnection();
        return null;
        
    
    }
    // update data of a customer wirh given id(user_name)
public static Customer updateCustomer(Customer new_customer,String user_name) {
        connection = DBConnection.getConnection();
        // SQL query to update customer information
        String updateQuery = "UPDATE customers SET customer_name=?, age=?, gender=?, phone=?, city=? WHERE customer_id=?";

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
            DBConnection.closeConnection();
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

public static boolean insertCustomer(Customer new_customer) {
    // Get a database connection
    connection = DBConnection.getConnection();
    if (connection == null) {
        return false;
    }
    
    // Prepare the SQL statement
    String sql = "INSERT INTO customers (customer_id, customer_name, password_, age, gender, phone, city) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        // Set the parameter values
        statement.setString(1, new_customer.getId());
        statement.setString(2, new_customer.getName());
        statement.setString(3, new_customer.getPassword());
        statement.setInt(4, new_customer.getAge());
        statement.setString(5, new_customer.getGender());
        statement.setString(6, new_customer.getPhone());
        statement.setString(7, new_customer.getCity());
        
        // Execute the statement
        int rowsInserted = statement.executeUpdate();
        
        // Check if the insertion was successful
        if (rowsInserted > 0) {
            return true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close the database connection
        DBConnection.closeConnection();

    }
    
    return false;
}
    // this ts for testing purpose no use
    public static void main(String[] args) throws SQLException {
        Customer c = new Customer("grootGuard","iamgroot");
        System.out.println(c);
        boolean t = CustomerDao.insertCustomer(c);
        System.out.println(t);
    }

}
