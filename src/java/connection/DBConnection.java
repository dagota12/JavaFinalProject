/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Customer;

/**
 *
 * @author Dag
 */
public class DBConnection {
    public static Connection connection = null;
    public static String name;
    public static String password;
    public static String url;
    
    public DBConnection() throws ClassNotFoundException{
        name = "root";
        password = "admin123";
        url = "jdbc:mysql://localhost:3306/shoppingSystem";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,name,password);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            public DBConnection(String name, String password){
        // get connection fr
        this.name = name;
        this.password = password;
        url = "jdbc:mysql://localhost:3306/shoppingSystem";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,name,password);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static synchronized Connection getConnection(){
        name = "root";
        password = "admin123";
        url = "jdbc:mysql://localhost:3306/shoppingSystem";
                if (connection == null) {
            try {
                // Load the JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Create the connection
                connection = DriverManager.getConnection(url, name, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately
            }
        }
        return connection;
        
    }
    public static synchronized boolean closeConnection(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately
            } finally {
                connection = null;
            }
        }
        return true;
        
    }
    public ArrayList<Customer> getCust() throws SQLException{
        ArrayList<Customer> cus = new ArrayList();
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery("SELECT customer_name,password_ FROM customers");
        while(rs.next()){
        String name = rs.getString("customer_name");
        String pwd = rs.getString("password_");
        cus.add(new Customer(name,pwd));
        }
        return cus;
    }
        public static ResultSet getResultFromSqlQuery(String SqlQueryString) {
        //Creating Resultset object
        ResultSet rs = null;
        try {
            //Checking whether the connection is null or null
            if (connection == null) {
                getConnection();
            }
            //Querying the query
            rs = connection.createStatement().executeQuery(SqlQueryString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       // closeConnection(); //close the connection
        return rs;
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBConnection db = new DBConnection();
        
        ArrayList<Customer> c = db.getCust();
        System.out.println(c);

       
    }    
}
