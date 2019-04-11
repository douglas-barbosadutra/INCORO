<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!-- Custom styles for this template -->
<link href="/prova.css" rel="stylesheet" type="text/css">
</head>

<body>

<h2><center>------- INSERT USER -------</center></h2>
<form action="/User/userManagement" method="get">
	<input type="submit" value="HOME" name="richiesta">
</form>

     <h3>Inserisci i dati dell'utente</h3>
     <form action="/User/creaUser" method="post">
     	
     	<h4>Username: <input type = "text" id = "user" name ="username" placeholder = "inserisci username"></h4>
     	
     	<h4>Password: <input type = "password" id = "user" name ="password" placeholder = "inserisci la password"></h4>

     	
    
     	
     	<h4>Type: 
  <select name="type">
    <option value="0" >0</option>
    <option value="1" >1</option>
  </select>
  <br><br></h4>


     	<input type="submit" value="Inserisci Utente" name="action">
     	
     </form>
</body>
</html>