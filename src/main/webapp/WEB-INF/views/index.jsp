<%@page import="com.jspiders.springmvc.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Contact Manager</title>

<!-- External CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
	<div class="center-block">
		<!-- Left Section: Login Form -->
		<div class="left-section">
			<%
			User user = (User) request.getAttribute("user");
			%>
			<form action="./login">
				<label for="username">Username</label> <input type="text"
					id="username" name="username"
					placeholder="Enter your username "> <label
					for="password">Password</label> <input type="password"
					id="password" name="password" placeholder="Enter your password">

				<button type="submit">Login</button>
			</form>
		</div>

		<!-- Right Section: Welcome Message and Signup Button -->
		<div class="right-section">
			<h2>Welcome to Contact Manager</h2>
			<a href="./signup" class="signup-button">Sign Up</a>
		</div>
	</div>
	<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
	%>
	<div class="message">
		<%=message%>
	</div>
	<%
	}
	%>

	<footer> &copy; 2024 Contact Manager. All rights reserved. </footer>
</body>

</html>
