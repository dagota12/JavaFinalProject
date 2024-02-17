/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import connection.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import objects.Product;
/**
 *
 * @author Dag
 */
public class ProductsDao {
    public static Connection connection = DBConnection.getConnection();
    public ProductsDao() throws ClassNotFoundException{
        connection = DBConnection.getConnection();
        
    }
    public static ArrayList<Product> getProducts() throws SQLException{
        connection = DBConnection.getConnection();
        ArrayList<Product> list = new ArrayList<>();
        Statement statement = connection.createStatement();
       
        ResultSet result = statement.executeQuery("SELECT * FROM products");
        while(result.next()){
            String name = result.getString("product_name");
            float price = Math.round((float)result.getFloat("price"));
            String desc = result.getString("description");
            int id = result.getInt("product_id");
            list.add(new Product(name,price,desc,id));          
        }
            statement.close();
            result.close();
            DBConnection.closeConnection();
        return list;
    }
    public static void main(String[] args) throws SQLException {
        ArrayList<Product> products = ProductsDao.getProducts();
        System.out.println(products);
    }
    
}
