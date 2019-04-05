<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h1>DELETE THINGS HERE</h1>
 <h2><center>------- DELETE THING -------</center></h2>
<form action="ThingsServlet?action=openList" method="post">
	<input type="submit" value="lista thing" name="action">
</form>

     <h3>Inserisci l'id della thing da eliminare</h3>
     <form action="ThingsServlet?action=delete" method="post">
     	
     	<h4>name: <input type = "text" id = "user" name ="id" placeholder = "idThing"></h4>
     	<input type="submit" value="Elimina thing" name="action">
     	
     </form>
</body>
</html>