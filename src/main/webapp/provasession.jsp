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
<h1>PROVA SESSION</h1>

<%
	String sCase = (String)(session.getAttribute("step"));
	switch(sCase){
	case "1":
		out.println(session.getAttribute("nome")); %>
		<a href="sessionServlet?action=s2"> step2 </a>
		<%
		break;
	case "2":
		out.println("session2");
		break;
	}
		%>
</body>
</html>