/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.logins;

import DAO.ProductsDao;
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
@WebServlet(name = "AdminDashboard", urlPatterns = {"/admin/dashboard","/admin/logout"})
public class AdminDashboard extends HttpServlet {

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
        String urlPattern = request.getServletPath();
        System.out.println(urlPattern);
        if (urlPattern.equals("/admin/logout")) {
        processLogout(request,response);
        }else{
            processLogin(request, response);
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
        
        processRequest(request, response);
    }
    // login 
        private void processLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(true); // Get the session without creating a new one if it doesn't exist
                if(session != null && session.getAttribute("loggedIn") != null &&  (boolean)session.getAttribute("isAdmin")){
            ArrayList<Product> products = new ArrayList<>();
            try {
              
                products = ProductsDao.getProducts();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("products", products);     
            RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/admin/AdminDashboard.jsp");
            dispacher.forward(request,response);
        }else{
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/403.jsp");
            request.setAttribute("message", "Not AUTHORIZED please login first!");
            dispatcher.forward(request, response);
        }
    }
    private void processLogout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("ADMIN loggging out");
        request.getSession().invalidate(); // Invalidate the user session
        try { 
            response.sendRedirect("/WebApplication1/admin");
        } catch (IOException ex) {
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
