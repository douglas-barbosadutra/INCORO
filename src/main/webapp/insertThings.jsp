<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href = "Stile.css" rel = "stylesheet" media = "screen">
<title>Insert title here</title>
</head>
<body>
 <h1>INSERT THINGS HERE</h1>
 
<h2><center>------- INSERT THING -------</center></h2>
<form action="ThingsServlet?action=openList" method="post">
	<input type="submit" value="lista thing" name="action">
</form>

     <h3>Inserisci i dati della thing</h3>
     <form action="ThingsServlet?action=insert" method="post">
     	
     	<h4>name: <input type = "text" id = "user" name ="nameThing" placeholder = "nome thing"></h4>
     	
     	<h4>Label: <select  id = "user" name ="fkLabel" placeholder = "inserisci Label">
     		<%
     			List<String> names = (List<String>)session.getAttribute("names");
     			
     			for(int i=0; i<names.size(); i++){
     				%><option><%out.println(names.get(i));%></option><%
     			}
     		%>
     			</select>
		</h4>

     	<input type="submit" value="Inserisci thing" name="action">
     	
     </form>

     
 
</body>
</html>