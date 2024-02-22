<%-- 
    Document   : nav.jsp
    Created on : Feb 17, 2024, 5:37:38 PM
    Author     : DAGIM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light px-3 bg-light">
    <a class="navbar-brand" href="/WebApplication1" style="font-weight: 500; font-size: 1.5rem;">
        Pick <span class="text-success font-weight-bold" style="font-weight: 700;">UP</span>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="dashboard">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="profile">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="orders">orders</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">About</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout" style="color: #d9534f;">Logout</a>
            </li>
        </ul>
    </div>
</nav>
