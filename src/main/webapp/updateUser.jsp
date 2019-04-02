<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2><center>------- UPDATE USER -------</center></h2>
<form action="LoginServlet?action=Indietro" method="post">
	<input type="submit" value="HOME" name="richiesta">
</form>

     <h3>Inserisci i dati dell'utente</h3>
     <form action="UsersServlet?action=update" method="post">
     
     <h4>IdUser: <input type = "text" id = "user" name ="idUser" placeholder = "inserisci nuovo ID"></h4>
     	
     	<h4>Username: <input type = "text" id = "user" name ="username" placeholder = "inserisci username"></h4>
     	
     	<h4>Password: <input type = "password" id = "user" name ="password" placeholder = "inserisci la password"></h4>
     	
    
     	
     	<h4>Type: <input type = "text" id = "user" name ="type" placeholder = "inserisci ruolo"></h4>

     	
     	<input type="submit" value="Aggiorna Utente" name="action">
     	
     </form>

     

</body>
</html>