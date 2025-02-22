<%@page import="com.jspiders.springmvc.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.jspiders.springmvc.dto.Contact"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration Form</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/addContact.css">
</head>
<body>
<nav class="navbar">
        <div class="left-section">
            <h1>Contact Manager</h1>
        </div>
        <div class="right-section">
        <% User user = (User)request.getAttribute("user"); %>
            <a href="./add-contact-page?id=<%=user.getId()%>" class="navbar-button">Add Contact</a>
            <a href="./userupdate?id=<%=user.getId()%>" class="navbar-button">Edit User</a>
            <a href="./contacts?id=<%=user.getId()%>" class="navbar-button">View Contacts</a>
            <a href="./home" class="navbar-button">Log Out</a>
        </div>
    </nav>
  <div align="center" style="display: flex; justify-content: center; align-items: center; height: calc(100vh - 100px);">
    <% Contact contact = (Contact) request.getAttribute("contact");
   
  String firstName = contact.getFirstName(); 
  %>
  
    <form action="./updateContact?id=<%=contact.getId() %>" method="post">
    
      <table>
        <tr>
          <td><label for="first_name">First Name</label></td>
          <td><input type="text" id="first_name" name="first_name"  value=<%=firstName %> required></td>
        </tr>
        <tr>
          <td><label for="last_name">Last Name</label></td>
          <td><input type="text" id="last_name" name="last_name" value="<%=contact.getLastName() %> "required></td>
        </tr>
        <tr>
          <td><label for="email">Email</label></td>
          <td><input type="email" id="email" name="email" value="<%=contact.getEmail() %>" required></td>
        </tr>
        <tr>
          <td><label for="mobile" ">Mobile</label></td>
          <td><input type="number" id="mobile" placeholder="9876543210" name="mobile"  value="<%=contact.getMobile() %>"   required></td>
        </tr>
        <tr>
          <td><label for="work" >Work</label></td>
          <td><input type="number" id="work" placeholder="9876543210" name="work" value="<%=contact.getWork() %>" ></td>
        </tr>
        <tr>
          <td>Gender</td>
          <td>
          <% String gender = contact.getGender();
          if(gender.equals("MALE")){%>
            <input type="radio" id="male"  name="gender" value="MALE" checked>
            <label for="male">Male</label>
            <input type="radio" id="female" name="gender" value="FEMALE" >
            <label for="female">Female</label>
            <input type="radio" id="others" name="gender" value="OTHERS" >
            <label for="others">Others</label>
            <%}else if(gender.equals("FEMALE")){ %>
             <input type="radio" id="male"  name="gender" value="MALE" >
            <label for="male">Male</label>
            <input type="radio" id="female" name="gender" value="FEMALE" checked>
            <label for="female">Female</label>
            <input type="radio" id="others" name="gender" value="OTHERS" >
            <label for="others">Others</label>
            <%}else{ %>
            <input type="radio" id="male"  name="gender" value="MALE" >
            <label for="male">Male</label>
            <input type="radio" id="female" name="gender" value="FEMALE" >
            <label for="female">Female</label>
            <input type="radio" id="others" name="gender" value="OTHERS" checked>
            <label for="others">Others</label>
            <%} %>
          </td>
        </tr>
        <tr>
          <td><label for="dob">DOB</label></td>
          <td><input type="date" id="dob" name="dob" value="<%=contact.getDob() %>" required></td>
        </tr>
        <tr>
          <td><label for="address">Address</label></td>
          <td><input type="text" id="address" value="<%=contact.getAddress() %>" name="address"></td>
        </tr>
        <tr>
          <td><label for="website">Website</label></td>
          <td><input type="url" id="website" value="<%=contact.getWebsite() %>" name="website"></td>
        </tr>
      </table>
      <input type="submit" value="update">
    </form>
</div>
  
  <footer>
        &copy; 2024 Contact Manager. All rights reserved.
    </footer>
</body>
</html>
