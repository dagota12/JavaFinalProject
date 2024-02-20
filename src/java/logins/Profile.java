/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package logins;

import DAO.CustomerDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "Profile", urlPatterns = {"/profile"})
public class Profile extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Profile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Profile at " + request.getSession() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            String user_name = (String) request.getSession().getAttribute("user_name");
                    
            Customer customer = CustomerDao.getCustomer(user_name);
            request.setAttribute("customer", customer);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Profile.jsp");
            
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer form_customer = this.createCustomerFromRequest(request);
        CustomerDao.updateCustomer(form_customer, (String) request.getSession().getAttribute("user_name"));
        response.sendRedirect("profile");
    }
     
    private Customer createCustomerFromRequest(HttpServletRequest request) {
        // Retrieve parameters from the request
        String name = request.getParameter("name");
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
        return new Customer(name, city, phone, gender, password, age);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
