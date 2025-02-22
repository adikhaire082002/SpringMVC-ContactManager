<%@page import="com.jspiders.springmvc.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.jspiders.springmvc.dto.Contact"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration Form</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">
	<style type="text/css">/* Navbar Styling */
.navbar {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    background: linear-gradient(135deg, #2575fc, #6a11cb);
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    z-index: 1000;
    box-sizing: border-box;
}

.left-section h1 {
    font-size: 1.5rem;
    color: #fff;
    margin: 0;
    font-weight: 600;
    white-space: nowrap;
}

.right-section {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-end;
    align-items: center;
    gap: 10px;
    max-width: 100%;
    overflow: hidden;
}

.navbar-button {
    padding: 8px 16px;
    background-color: transparent;
    color: #fff;
    font-weight: 500;
    border: none;
    text-decoration: none;
    transition: color 0.3s ease, transform 0.2s ease;
    font-size: 0.9rem;
    white-space: nowrap;
}

.navbar-button:hover {
    color: #f0f0f0;
    transform: translateY(-2px);
}

.navbar-button:active {
    transform: translateY(0);
}

/* Responsive Navbar */
@media (max-width: 768px) {
    .navbar {
        padding: 10px 15px;
    }

    .left-section h1 {
        font-size: 1.3rem;
    }

    .right-section {
        justify-content: center;
        gap: 8px;
    }

    .navbar-button {
        padding: 6px 12px;
        font-size: 0.8rem;
    }
}

@media (max-width: 480px) {
    .right-section {
        flex-direction: row;
        justify-content: center;
    }

    .navbar-button {
        padding: 5px 10px;
        font-size: 0.75rem;
    }
}</style>
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
  
  
  <div class="sign-up-page">
        <div class="center-block">
            <!-- Top Section -->
            <div class="top-section">
                <form action="./updateuser?id=<%=user.getId()%>" method="POST">
                    <div class="inline-fields">
                        <div>
                            <label for="name">First Name:</label> 
                            <input type="text" id="name" name="name" value=<%=user.getName() %> required>
                        </div>
                        <div>
                            <label for="surname">Last Name:</label> 
                            <input type="text" id="surname" name="surname" value=<%=user.getSurname() %> required>
                        </div>
                    </div>

                    <!-- New Username and Password in One Line -->
                    <div class="inline-fields">
                        <div>
                            <label for="username">Username:</label>
                            <input type="text" id="username" name="username" value=<%=user.getUsername() %> required>
                        </div>
                        <div>
                            <label for="password">Password:</label>
                            <input type="password" id="password" name="password" value=<%=user.getPassword() %> required>
                        </div>
                    </div>

                    <!-- Email and Mobile Number in One Line -->
                    <div class="inline-fields">
                        <div>
                            <label for="email">Email:</label> 
                            <input type="email" id="email" name="email" value=<%=user.getEmail() %> required>
                        </div>
                        <div>
                            <label for="mobile">Mobile Number:</label> 
                            <input type="tel" id="mobile" name="mobile" value=<%=user.getMobile() %> required>
                        </div>
                    </div>

                    <!-- Security Question and Answer in One Line -->
                    <div class="inline-fields">
                        <div>
                            <label for="security_question">Security Question:</label>
                            <select id="security_question" name="security_question" required>
                            <% String que = user.getSecurtiyQue();  
                            if(que.equals("mother_maiden_name")){
                            	%>
                                <option value="mother_maiden_name" selected="selected">What is your mother's maiden name?</option>
                                <option value="first_pet">What was the name of your first pet?</option>
                                <option value="school_name">What is the name of your first school?</option>
                                <%} else if(que.equals("first_pet")) {%>
                                <option value="mother_maiden_name">What is your mother's maiden name?</option>
                                <option value="first_pet" selected="selected">What was the name of your first pet?</option>
                                <option value="school_name">What is the name of your first school?</option>
                                <%}else { %>
                                <option value="mother_maiden_name">What is your mother's maiden name?</option>
                                <option value="first_pet">What was the name of your first pet?</option>
                                <option value="school_name" selected="selected">What is the name of your first school?</option>
                                <%} %>
                            </select>
                        </div>
                        <div>
                            <label for="answer">Answer:</label>
                            <input type="text" id="answer" name="answer" value=<%=user.getAnswer() %> required>
                        </div>
                    </div>

                    <button type="submit" name="Update">Update</button>
                    <% 
         String message = (String) request.getAttribute("message");
    if (message != null) {
    %>
    <div class="message">
        <%= message %>
    </div>
    <%
    }
    %>
    <% 
         String message1 = (String) request.getAttribute("message1");
    if (message1 != null) {
    %>
    <div class="message">
        <%= message1 %>
    </div>
    <%
    }
    %>
    <% 
         String message2 = (String) request.getAttribute("message2");
    if (message2 != null) {
    %>
    <div class="message">
        <%= message2 %>
    </div>
    <%
    }
    %>
                </form>
            </div>

            
        </div>
  
    
  
  <footer>
        &copy; 2024 Contact Manager. All rights reserved.
    </footer>
</body>
</html>
