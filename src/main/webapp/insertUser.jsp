<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head> 

<body>

<h2><center>------- INSERT USER -------</center></h2>
<form action="UsersServlet?action=Indietro" method="post">
	<input type="submit" value="HOME" name="richiesta">
</form>

     <h3>Inserisci i dati dell'utente</h3>
     <form action="UsersServlet?action=insert" method="post">
     	
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