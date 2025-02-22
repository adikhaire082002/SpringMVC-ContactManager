<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Manager</title>

    <!-- External CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">
</head>
<body>

    <div class="sign-up-page">
        <div class="center-block">
            <!-- Top Section -->
            <div class="top-section">
                <form action="./usersignup" method="POST">
                    <div class="inline-fields">
                        <div>
                            <label for="name">First Name:</label> 
                            <input type="text" id="name" name="name" required>
                        </div>
                        <div>
                            <label for="surname">Last Name:</label> 
                            <input type="text" id="surname" name="surname" required>
                        </div>
                    </div>

                    <!-- New Username and Password in One Line -->
                    <div class="inline-fields">
                        <div>
                            <label for="username">Username:</label>
                            <input type="text" id="username" name="username" required>
                        </div>
                        <div>
                            <label for="password">Password:</label>
                            <input type="password" id="password" name="password" required>
                        </div>
                    </div>

                    <!-- Email and Mobile Number in One Line -->
                    <div class="inline-fields">
                        <div>
                            <label for="email">Email:</label> 
                            <input type="email" id="email" name="email" required>
                        </div>
                        <div>
                            <label for="mobile">Mobile Number:</label> 
                            <input type="tel" id="mobile" name="mobile" required>
                        </div>
                    </div>

                    <!-- Security Question and Answer in One Line -->
                    <div class="inline-fields">
                        <div>
                            <label for="security_question">Security Question:</label>
                            <select id="security_question" name="security_question" required>
                            <option selected="selected">Choose the question</option>
                                <option value="mother_maiden_name">What is your mother's maiden name?</option>
                                <option value="first_pet">What was the name of your first pet?</option>
                                <option value="school_name">What is the name of your first school?</option>
                            </select>
                        </div>
                        <div>
                            <label for="answer">Answer:</label>
                            <input type="text" id="answer" name="answer" required>
                        </div>
                    </div>

                    <button type="submit">Sign Up</button>
                    <button type="reset" class="clear-button">Clear</button>
                </form>
            </div>

            <!-- Bottom Section -->
            <div class="bottom-section">
                <h2>Join Us Now!</h2>
                <p>Sign up to enjoy our services and join a community of like-minded individuals.</p>
                <a href="./home" class="signup-button">Already have an account? Login</a>
            </div>
        </div>
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

        <footer>
            <p>&copy; 2025 Your Company. All rights reserved.</p>
        </footer>
    </div>

</body>
</html>
