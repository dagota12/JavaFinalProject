/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package logins;

import DAO.CustomerDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objects.Customer;

/**
 *
 * @author DAGIM
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispacher = request.getRequestDispatcher("WEB-INF/views/Register.jsp");
        dispacher.forward(request, response);
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
        Customer new_customer = this.createCustomerFromRequest(request);
        System.out.println(new_customer);
        
        if(CustomerDao.insertCustomer(new_customer)){
        response.sendRedirect("/WebApplication1");
        }else{
            
        }
    }

private Customer createCustomerFromRequest(HttpServletRequest request) {
        // Retrieve parameters from the request
        String name = request.getParameter("name");
        String user_name = request.getParameter("user_name");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        
        // Parse age from the request and handle any potential exceptions
        int age = 0;
        try {
            age = Integer.parseInt(request.getParameter("age"));
        } catch (NumberFormatException e) {
            System.out.println("Number ERROR on: Age");
        }

        // Create and return a new Customer object
        
        return new Customer(name, user_name, password, city, phone, gender, age);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
