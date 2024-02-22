/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.logins;

import connection.DBConnection;
import connection.DatabaseUpdater;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author davew
 */
@WebServlet(name = "AdminProductsUpdate", urlPatterns = {"/admin/products","/admin/products/add","/admin/products/edit"})
public class AdminProductsUpdate extends HttpServlet {
    
    public static final String UPLOAD_DIRECTORY = "C:\\Users\\BitCom Tech\\Documents\\NetBeansProjects\\WebApplication1\\web\\images\\products\\";
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
            out.println("<title>Servlet AdminProductsUpdate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminProductsUpdate at " + request.getContextPath() + "</h1>");
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
        if (urlPattern.equals("/admin/products")) {
            processProductUpdate(request,response);
        }else if(urlPattern.equals("/admin/products/edit")){
            System.out.println("EDITING PRODUCT");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/adminEditProduct.jsp");
            request.setAttribute("id", request.getParameter("id"));
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
        String urlPattern = request.getServletPath();
        if (urlPattern.equals("/admin/products/add")) {
            processProductAdd(request,response);
        }else if(urlPattern.equals("/admin/products/edit")){
            processEdit(request,response);
        
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

    private void processProductUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/adminProductsUpdate.jsp");
            dispatcher.forward(request,response);

    }

private void processProductAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> items = upload.parseRequest(request);

                String productName = null;
                double price = 0.0;
                int quantity = 20;
                String description = null;
                String fileName = null;

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // Process regular form fields
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();

                        if (fieldName.equals("productName")) {
                            productName = fieldValue;
                        } else if (fieldName.equals("price")) {
                            System.out.println(fieldName);
                            price = Double.parseDouble(fieldValue);
                        } else if (fieldName.equals("quantity")) {
                            quantity = Integer.parseInt(fieldValue);
                        } else if (fieldName.equals("description")) {
                            description = fieldValue;
                        }
                    } else {
                        // Process file upload
                        String fieldName = item.getFieldName();
                        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
                        String extension = item.getName().substring(item.getName().lastIndexOf("."));
                        fileName = timeStamp + extension;
                        String filePath = UPLOAD_DIRECTORY + fileName;
                        File storeFile = new File(filePath);

                        // Save the file to disk
                        item.write(storeFile);
                    }
                }

                // Insert product into the database
                Connection connection = DBConnection.getConnection();
                String query = "INSERT INTO products (product_name, price, quantity, description, Image, stocked_in_date) VALUES (?, ?, ?, ?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, productName);
                    preparedStatement.setDouble(2, price);
                    preparedStatement.setInt(3, quantity);
                    preparedStatement.setString(4, description);
                    preparedStatement.setString(5, fileName);
                    preparedStatement.setDate(6, new java.sql.Date(System.currentTimeMillis()));

                    int affected = preparedStatement.executeUpdate();

                                        // If product inserted successfully in the database
                    if (affected > 0) {
                        String success = "Product added successfully.";
                        // Adding method in session.
                        request.getSession().setAttribute("message", success);
                        // Response send to the admin-add-product.jsp
                        response.sendRedirect("/WebApplication1/admin/products");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                        String fail = "Error inserting product:";
                        // Adding method in session.
                        request.getSession().setAttribute("message" + e.getMessage(), fail);
                        // Response send to the admin-add-product.jsp
                        response.sendRedirect("/WebApplication1/admin/products");
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
                response.getWriter().println("Error uploading file: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error processing request: " + e.getMessage());
            }
        } else {
            response.getWriter().println("Invalid request!");
        }
    }

    private void processEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
   if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            System.out.println("Processing Edit");
            try {
                List<FileItem> items = upload.parseRequest(request);
                int id = -1;
                String productName = null;
                double price = 0.0;
                int quantity = 20;
                String description = null;
                String fileName = null;

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // Process regular form fields
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();

                        if (fieldName.equals("productName")) {
                            productName = fieldValue;
                        } else if (fieldName.equals("price")) {
                            System.out.println(fieldName);
                            price = Double.parseDouble(fieldValue);
                        } else if (fieldName.equals("quantity")) {
                            quantity = Integer.parseInt(fieldValue);
                        } else if (fieldName.equals("description")) {
                            description = fieldValue;
                        } else if (fieldName.equals("pid")) {
                            id = Integer.parseInt(fieldValue);
                        }
                    } else {
                        // Process file upload
                        System.out.println("EDITING PRODUCT assignig Values");
                        String fieldName = item.getFieldName();
                        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
                        String extension = item.getName().substring(item.getName().lastIndexOf("."));
                        fileName = timeStamp + extension;

                        String filePath = UPLOAD_DIRECTORY + fileName;
                        File storeFile = new File(filePath);
                        
                        System.out.println("Finished PRODUCT assignig Values");
                        // Save the file to disk
                        item.write(storeFile);
                    }
                }

                // Insert product into the database
                Connection connection = DBConnection.getConnection();
                System.out.println("EDITING PRODUCT STARTED SQL query " + id);
                String query = "UPDATE products SET product_name = ?, price = ?,quantity = ?,stocked_in_date = ?,description = ?,image = ? WHERE product_id = ?;";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, productName);
                    preparedStatement.setDouble(2, price);
                    preparedStatement.setInt(3, quantity);
                    preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                    preparedStatement.setString(5, description);
                    preparedStatement.setString(6, fileName);
                    preparedStatement.setInt(7, id);

                    int affected = preparedStatement.executeUpdate();
                    
                    System.out.println("EDITING PRODUCT done executing Query");
                    // If product inserted successfully in the database
                    if (affected > 0) {
                        System.out.println("SUCCESS prduct EDIT");
                        String success = "Product edited successfully.";
                        // Adding method in session.
                        request.getSession().setAttribute("message", success);
                        // Response send to the admin-add-product.jsp
                        
                        response.sendRedirect("/WebApplication1/admin");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                        System.out.println("FAIL product EDIT");
                        String fail = "Error editing product:";
                        // Adding method in session.
                        request.getSession().setAttribute("message" + e.getMessage(), fail);
                        // Response send to the admin-add-product.jsp
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/adminEditProduct.jsp");
                        request.setAttribute("id", id);
                        request.setAttribute("message", "Error in Editing product");
                        dispatcher.forward(request,response);
                       
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
                response.getWriter().println("Error uploading file: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error processing request: " + e.getMessage());
            }
        } else {
            response.getWriter().println("Invalid request!");
        }

    }
        
    

}
