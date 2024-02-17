<%-- 
    Document   : 403
    Created on : Jan 30, 2024, 11:00:45 AM
    Author     : DAGIM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="id" dir="ltr">

<head>
     <meta charset="utf-8" />
     <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
     <meta name="description" content="" />
     <meta name="author" content="" />

     <!-- Title -->
     <title>403 - Not Authorized</title>
     <link href="boot/bootstrap.min.css" rel="stylesheet" type="text/css"/>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />
</head>

<body class="bg-dark text-white py-5">
     <div class="container py-5">
          <div class="row">
               <div class="col-md-2 text-center">
                    <p><i class="fa fa-exclamation-triangle fa-5x"></i><br/>Status Code: 403</p>
               </div>
               <div class="col-md-10">
                    <h3>OPPSSS!!!! Sorry...</h3>
                    <p>Sorry, your access is refused due: ${message}</p>
                    <a class="btn btn-danger" href="javascript:history.back()">Go Back</a>
               </div>
          </div>
     </div>

</body>

</html>
