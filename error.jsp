<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Error Page</title>
	<link href="javajam.css" rel="stylesheet">
</head>
<body>
	<h1>Error</h1>
	<p><%= request.getAttribute("errorMsg") %></p>
</body>
</html>