<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<!--  int id = (int) session.getAttribute("id"); %>-->

<h2><center>------- UPDATE LABEL -------</center></h2>
<form action="LoginServlet?action=Indietro" method="post">
	<input type="submit" value="HOME" name="richiesta">
</form>
     <h3>Inserisci i dati della Label</h3>
     <form action="LabelsServlet?action=update" method="post">
     
     	<!-- <h4>IdLabel: <input type = "text" id = "label" name ="idLabel" value=" out.println(id);%>" disabled></h4>-->
     	
     	<!-- <h4>IdLabel: <input type = "text" id = "label" name ="idLabel" value="" disabled></h4>-->
     	
     	<h4>Name: <input type = "text" id = "label" name ="name" placeholder = "inserisci nome"></h4>
     	
     	<h4>ID Proprietario: <input type = "text" id = "label" name ="fktouser" placeholder = "inserisci la fk to user"></h4>
     	 	
    	<!-- <input type="submit" value="Aggirna Label" name="action">-->
     	<button type="submit" value="update" name="action">Aggiorna Label</button>
     </form>
</body>
</html>