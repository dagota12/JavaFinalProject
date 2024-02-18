<%-- 
    Document   : AdminCustomersView
    Created on : Feb 18, 2024, 3:51:25 PM
    Author     : DAGIM
--%>

<%@page import="connection.DBConnection"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("loggedIn") == null || (boolean) session.getAttribute("isAdmin") == false) {
        response.sendRedirect("/WebApplication1/admin");
        return;
    }%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
       
        <title>Admin Customer-view Page</title>
        <link href="../style.css" rel="stylesheet" type="text/css"/>
        <link href="/WebApplication1/boot/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../JSP/adminNav.jsp"></jsp:include>
        <div class="container">
                <h3>My Customers</h3>
                <div class="table-responsive">
                    <table class="table  table-hover table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Customer ID</th>
                                <th>Name</th>
                                <th>age</th>
                                <th>gender</th>
                                <th>phone</th>
                                <th>city</th>
                                <th>total-orders</th>
                                <th>remove</th>
                            </tr>
                        </thead>
                        <%
                            int index = 0; // column index
                            //Getting all products
                            ResultSet totalCustomer = DBConnection.getResultFromSqlQuery("SELECT * FROM AdminCustomersView;");
                            while (totalCustomer != null && totalCustomer.next()) {
                                index++;
                        %>
                        <tr class="rem1">
                            <td class="invert"><%=index%></td>
                            <td class="invert"><%=totalCustomer.getString(1)%></td>
                            <td class="invert"><%=totalCustomer.getString(2)%></td>
                            <td class="invert"><%=totalCustomer.getInt(3)%></td>
                            <td class="invert"><%=totalCustomer.getString(4)%></td>
                            <td class="invert"><%=totalCustomer.getString(5)%></td>
                            <td class="invert"><%=totalCustomer.getString(6)%></td>
                            <td class="invert"><%=totalCustomer.getInt(7)%></td>
                            <td class="invert">
                                <!-- Button trigger modal -->
                                <button data-id="<%=totalCustomer.getString(1)%>" type="button" class="btn delete-btn btn-danger">
                                  Delete Customer
                                </button>
                            </td>
                        </tr>
                        <%
                            }
                        %>

                    </table>
                </div>
            </div>
<!-- customer delete confirmation Modal -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="confirmDeleteModalLabel">Confirmation</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Are you sure you want to delete this customer?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-danger" id="confirmDeleteBtn">Delete</button>
      </div>
    </div>
  </div>
</div>   
<!-- success/failed deletion Modal -->
<div class="modal" id="resultModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Operation Result</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal Body -->
            <div class="modal-body" id="resultModalBody">
                <!-- Dynamic content will be inserted here -->
            </div>

            <!-- Modal Footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

    <!--scripts-->
    
    <script src="/WebApplication1/boot/jquery-3.7.1.min.js" type="text/javascript"></script>
    <script src="/WebApplication1/boot/popper.min.js" type="text/javascript"></script>
    <script src="/WebApplication1/boot/bootstrap.min.js" type="text/javascript"></script>
    

<!-- Include this script in your HTML file -->
<script>
    $(document).ready(function() {
        // Variable to store the customer ID
        var customerIdToDelete;

        // Handle delete button click
        $('.delete-btn').on('click', function() {
            // Get the customer ID from the data-id attribute in the button
            console.log($(this).data())
            customerIdToDelete = $(this).data('id');

            // Show the modal
            $('#confirmDeleteModal').modal('show');
        });

        // Handle confirm delete button click
        $('#confirmDeleteBtn').on('click', function() {
            // Perform the delete operation here
            // Send a POST request to your server to delete the customer
            console.log(customerIdToDelete);
            $.ajax({
                url: '/WebApplication1/admin/customers/delete', // Replace with the actual endpoint
                type: 'POST',
                data: { user_name: customerIdToDelete }, // Pass the customer ID to the server
                success: function(response) {
                    // Handle the success response (e.g., refresh the page)
                    console.log('Customer deleted successfully');
                          
                    showModal('Customer deleted successfully', true);
                },
                error: function(xhr, status, error) {
                    // Handle the error response
                    showModal('Error deleting customer: ' + xhr.responseText, false);
                }
            });

            // Close the modal
            $('#confirmDeleteModal').modal('hide');
        });
        
    function showModal(message, isSuccess) {
    var modal = $('#resultModal');
    var modalBody = $('#resultModalBody');

    // Update modal content and style based on success or failure
    modalBody.text(message);
    modalBody.removeClass('text-success text-danger');
    
    if (isSuccess) {
        modalBody.addClass('text-success');
        location.reload();
    } else {
        modalBody.addClass('text-danger');
    }

    // Show the modal
    modal.modal('show');
}

    });
</script>

    </body>
</html>
