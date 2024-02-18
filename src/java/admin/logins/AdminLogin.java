/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.logins;

import DAO.AdminDao;
import DAO.ProductsDao;
import admin.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logins.Login;
import objects.Product;

/**
 *
 * @author DAGIM
 */
@WebServlet(name = "AdminLogin", urlPatterns = {"/admin"})
public class AdminLogin extends HttpServlet {

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
            out.println("<title>Servlet AdminLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminLogin at " + request.getContextPath() + "</h1>");
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
        
        HttpSession session = request.getSession(); // Get the session without creating a new one
    if (session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("isAdmin") == false) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/adminLogin.jsp");
        dispatcher.forward(request, response);
    }else{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/AdminDashboard.jsp");
        dispatcher.forward(request, response);
    
    
    }


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
            throws ServletException, IOException {
        PrintWriter out;
        out = response.getWriter();
        try {
            String user_name = request.getParameter("name");
            String password = request.getParameter("password");
            
            Admin customer = AdminDao.getAdmin(user_name, password);
            
            if(customer != null){
                RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/admin/AdminDashboard.jsp");
                
                HttpSession session = request.getSession();
                session.setAttribute("loggedIn", true);
                session.setAttribute("isAdmin", true);
                session.setAttribute("user_name", user_name);
                
                ArrayList<Product> products = ProductsDao.getProducts();
                request.setAttribute("products", products);
                
                dispacher.forward(request, response);
            }else{
               String message = "Wrong User-Name Or Password";
               request.setAttribute("message", message);
               RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/403.jsp");
               dispatcher.forward(request, response);
                
                //out.println("Wrong user-name or password");
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
