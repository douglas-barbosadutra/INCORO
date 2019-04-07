
<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href = "Stile.css" rel = "stylesheet" media = "screen">
<title>Insert title here</title>
</head>
<body>
<% ThingsDTO thing = (ThingsDTO)session.getAttribute("tTest");
out.println(thing.getId());
out.println(thing.getName());
out.println(thing.getFktouser());
out.println(thing.getFktolabel());
%>
</body>
</html>