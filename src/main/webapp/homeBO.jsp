<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Business Owner view</title>
</head>
<body>
	<h1>Benvenuto BO</h1>
	<h2>
	<% UsersDTO utente = (UsersDTO) session.getAttribute("utente");
	out.println(utente.getUsername());
	%>
	</h2>
	<h2>MENU</h2>
	<ul>
			<li>
		  		<div class="dropdown">
					<a href="ThingsServlet?action=openInsert" class="dropbtn">Inserisci Things</a>				
				</div>
	  		</li>
			<li>
		  		<div class="dropdown">
					<a href="LabelsServlet?action=openInsert" class="dropbtn">Inserisci Labels</a>				
				</div>
	  		</li>
			<li>
		  		<div class="dropdown">
					<a href="ThingsServlet?action=openDelete" class="dropbtn"> Elimina things</a>				
				</div>
	  		</li>
	  		<li>
		  		<div class="dropdown">
					<a href="LabelsServlet?action=openDelete" class="dropbtn"> Elimina labels</a>				
				</div>
	  		</li>
	 		<li>
		  		<div class="dropdown">
					<a href="ThingsServlet?action=openList" class="dropbtn">Lista things</a>				
				</div>
	  		</li>
	  		<li>
		  		<div class="dropdown">
					<a href="LabelsServlet?action=openList" class="dropbtn">Lista labels</a>				
				</div>
	  		</li>			
			<li>
		  		<div class="dropdown">
					<a href="ThingsServlet?action=openUpdate" class="dropbtn">Aggiorna things</a>				
				</div>
	  		</li>
	  		<li>
		  		<div class="dropdown">
					<a href="LabelsServlet?action=openUpdate" class="dropbtn">Aggiorna lables</a>				
				</div>
	  		</li>

	  		<li>
		  		<div class="dropdown">
					<a href="UserServlet?action=homeLogs" class="dropbtn">Logout</a>				
				</div>
	  		</li>
		</ul>
	
</body>
</html>