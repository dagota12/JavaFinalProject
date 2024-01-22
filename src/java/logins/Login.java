/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package logins;

import DAO.ProductsDao;
import java.io.IOException;
/** java.io.IOException
 * javax.servlet.servletException
 * javax.servlet.annotation,httpServlet,HttpServletRequest,HttpServletResponse
*/
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import objects.Product;

/**
 *
 * @author Dag
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getParameter("name") + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("not Authorized");
        
        }
       // processRequest(request, response);
    }

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
            throws ServletException, IOException{
        PrintWriter out;
        out = response.getWriter();
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            String user_name = request.getParameter("name");
            String url,name,pwd;
            name = "root";
            pwd = "admin123";
            url = "jdbc:mysql://localhost:3306/shoppingSystem";
            Connection con = DriverManager.getConnection(url,name,pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM customers WHERE customer_id= ?;");
            ps.setString(1, request.getParameter("name"));
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                RequestDispatcher dispac = request.getRequestDispatcher("myJsp.jsp");
                request.getSession().setAttribute("user", user_name);
                
                List<String> myList = new ArrayList<>();
                myList.add("mine is blur");
                myList.add("Item 2");
                myList.add("Item 3");
                ArrayList<Product> prods = ProductsDao.getProducts();
                request.setAttribute("myList", prods);
                
                dispac.forward(request, response);
            }else{
                out.println("Wrong Name");
            }
            
//        String password = request.getParameter("password");
//processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
