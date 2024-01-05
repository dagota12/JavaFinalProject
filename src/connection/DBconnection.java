/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author BitCom Tech
 */
public class DBconnection {
    public Connection connection = null;
    private String name;
    private String password;
    private String url;
    
    public DBconnection(){
        // get connection fr
        name = "root";
        password = "admin123";
        url = "jdbc:mysql://localhost:3306/world";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,name,password);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    // over loading the constructor
    
        public DBconnection(String name, String password){
        // get connection fr
        this.name = name;
        this.password = password;
        url = "jdbc:mysql://localhost:3306/world";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,name,password);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Connection getConnection(){
        
        return this.connection;
    }
    public boolean closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
        
    }
    public static void main(String[] args) throws SQLException{

//     DBconnection db = new DBconnection();
//     Connection conn = db.getConnection();
//     Statement st = conn.createStatement();
//     ResultSet rs = st.executeQuery("SELECT * FROM city LIMIT 10");
//     while(rs.next()){
//         System.out.println(rs.getString("Name"));
//     }
//     
//     
//     
//     db.closeConnection();
     
     
     
    }
    
}
