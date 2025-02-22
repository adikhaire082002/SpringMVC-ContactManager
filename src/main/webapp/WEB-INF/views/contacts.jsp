<%@page import="com.jspiders.springmvc.dto.User"%>
<%@page import="com.jspiders.springmvc.dto.Contact"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contacts Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/contacts.css">
</head>
<body>
 <nav class="navbar">
  <%   User user = (User)request.getAttribute("user"); %>
        <div class="left-section">
            <h1>Contact Manager</h1>
            <!-- Search Bar -->
            <div class="search-container">
            <form action="./search-contact" method="post">
                <input type="text" id="search" name="search" placeholder="Search Contacts...">
                 <button type="submit" class="search-icon">🔍</button>
                 </form>
            </div>
        </div>
       
        <div class="right-section">
        <form action="./sort?sortOption">
    <select id="sortOptions" name="sortOption" onchange="this.form.submit()">
       <option  value="Sort" selected="selected" disabled="disabled">Sort By</option>
         <option value="firstname">First Name</option>
        <option value="lastname">Last Name</option>
        <option value="dob">DOB</option>
        <option value="id">Date Created</option>
    </select>
    </form>
        
            <a href="./add-contact-page" class="navbar-button">Add Contact</a>
            <a href="./userupdate" class="navbar-button">Edit User</a>
             <a href="./delete-user" class="navbar-button" onclick="return confirm('Are you sure you want to delete this user?');">Delete User</a>
            <a href="./contacts" class="navbar-button">View Contacts</a>
            <a href="./home" class="navbar-button">Log Out</a>
        </div>
    </nav>
     
     
<script>
    function confirmDelete(contactId) {
        if (confirm("Are you sure you want to delete this contact?")) {
            window.location.href = './deleteContactById?id=' + contactId;
        }
    }
</script>
    <%
    @SuppressWarnings("unchecked")
    List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");
    if (contacts != null) {
    %>
    <div class="container">
        <%
        for (Contact contact : contacts) {
        %>
        <div class="card">
            
            <div class="details">
                <h3><%= contact.getFirstName() %> <%= contact.getLastName() %></h3>
                <p><strong>Email:</strong> <%= contact.getEmail() %></p>
                <p><strong>Mobile:</strong> <%= contact.getMobile() %></p>
                <p><strong>Work:</strong> <%= contact.getWork() %></p>
                <p><strong>Gender:</strong> <%= contact.getGender() %></p>
                <p><strong>DOB:</strong> <%= contact.getDob() %></p>
                <p><strong>Address:</strong> <%= contact.getAddress() %></p>
                <p><strong>Website:</strong> <a href="<%= contact.getWebsite() %>" target="_blank" style="color: #f9f9f9" ><%= contact.getWebsite() %></a></p>
                <div class="actions">
                    <a href="javascript:void(0);" class="delete" onclick="confirmDelete('<%= contact.getId() %>')">Delete 🗑</a>
                    <a href="./update-contact-page?id=<%= contact.getId() %>" class="update">Update 🖊</a>
                </div>
            </div>
        </div>
        <%
        }
        %>
    </div>
    <%
    }
   String message = (String) request.getAttribute("message");
    if (message != null) {
    %>
    <div class="message">
        <%= message %>
    </div>
    <%
    }
    %>
    
    <footer>
        &copy; 2024 Contact Manager. All rights reserved.
    </footer>
</body>
</html>
