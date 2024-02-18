/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import admin.Admin;
import connection.DBConnection;
import java.sql.*;

/**
 *
 * @author DAGIM
 */
public class AdminDao {
    public static Connection connection = DBConnection.getConnection();
    public static Admin getAdmin(String user_name, String password) throws SQLException {
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM admins WHERE admin_id= ? and password_ = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,user_name);
        statement.setString(2,password);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return getAdminObj(result);
        }
        DBConnection.closeConnection();
        return null;
    }

    private static Admin getAdminObj(ResultSet result) throws SQLException {
        String id = result.getString("admin_id");
        String name = result.getString("admin_name");
        String password = result.getString("password_");
        String city = result.getString("city");
        String phone = result.getString("phone");
        int age = result.getInt("age");
        String gender = result.getString("gender");
        Admin customer = new Admin(name,id,password,city,phone,gender,age);
        
        return customer;
    }
    
}
