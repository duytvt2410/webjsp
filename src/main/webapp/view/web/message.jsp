<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
		<% List<String> listString = (List<String>)  request.getAttribute("listString"); %>
        <h3><%=request.getAttribute("Message")%></h3>
        <% for(String s : listString) { %>
        <img src="data:image/jpg;base64,<%= s %>" width="240" height="300"/>
        <% } %>
    </center>
</body>
</html>