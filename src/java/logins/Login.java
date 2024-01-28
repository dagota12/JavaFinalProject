/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package logins;

import DAO.CustomerDao;
import DAO.ProductsDao;
import java.io.IOException;
/** 
 * java.io.IOException
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
import javax.servlet.http.HttpSession;
import objects.Customer;
import objects.Product;

/**
 *
 * @author Dag
 */
@WebServlet(name = "Login", urlPatterns = {"/dashboard"})
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if((boolean)request.getSession(true).getAttribute("loggedIn")){
            ArrayList<Product> products = new ArrayList<>();
            try {
               // System.out.println(request.getSession(true).getAttribute("loggedIn"));
                products = ProductsDao.getProducts();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("products", products);         
            RequestDispatcher dispacher = request.getRequestDispatcher("myJsp.jsp");
            dispacher.forward(request,response);
        }else{
            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {
                out.println("not Authorized");
            }
        }
       // processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        PrintWriter out;
        out = response.getWriter();
        try {
            String user_name = request.getParameter("name");
            String password = request.getParameter("password");
            Customer customer = CustomerDao.getCustomer(user_name, password);
            
            if(customer != null){
                RequestDispatcher dispac = request.getRequestDispatcher("myJsp.jsp");
                
                HttpSession session = request.getSession(true);
                session.setAttribute("loggedIn", true);
                session.setAttribute("user_name", user_name);
                
                ArrayList<Product> products = ProductsDao.getProducts();
                request.setAttribute("products", products);
                
                dispac.forward(request, response);
            }else{
                
                out.println("Wrong user-name or password");
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
    }
    public static boolean validate_customer(String use){
    
    return false;
    }

}
