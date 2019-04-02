<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
     	
     	<h4>name: <input type = "text" id = "user" name ="name" placeholder = "nome thing"></h4>
     	
     	<h4>foreign key idUtente: <input type = "password" id = "user" name ="fkUser" placeholder = "inserisci id Proprietario"></h4>
     	
     	<h4>foreign key idLabel: <input type = "text" id = "user" name ="fkLabel" placeholder = "inserisci id Label"></h4>

     	<input type="submit" value="Inserisci thing" name="action">
     	
     </form>

     
 
</body>
</html>