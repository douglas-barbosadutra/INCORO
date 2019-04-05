<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href = "Stile.css" rel = "stylesheet" media = "screen">

<title></title>
</head>
<body>

<h1>Benvenuto su INCORO!</h1>



	
	<!--
			<li>
		  		<div class="dropdown">
					<a href="UsersServlet?action=openInsert" class="dropbtn">Inserisci utente</a>				
				</div>
	  		</li>
			
			<li>
		  		<div class="dropdown">
					<a href="UsersServlet?action=delete" class="dropbtn">Elimina utente</a>				
				</div>
	  		</li>

	 		<li>
		  		<div class="dropdown">
					<a href="UsersServlet?action=UsersManager" class="dropbtn">Lista utenti</a>				
				</div>
	  		</li>

			
			<li>
		  		<div class="dropdown">
					<a href="UsersServlet?action=openUpdate" class="dropbtn">Aggiorna utenti</a>				
				</div>
	  		</li>
	  		

	  		<li>
		  		<div class="dropdown">
					<a href="UserServlet?action=homeLogs" class="dropbtn">Logout</a>				
				</div>
	  		</li>
		</ul>
	
	 
	<h2>------- MENU PRINCIPALE -------</h2>

	<h3>1. Esempio</h3>
	<form action="MenuServlet" method="post">
		<button type="submit" value="esempioManager" name="richiesta">
			bottone esempio</button>
	</form>
	 
     <h3>2. Badges</h3>
     <form action="BadgeServlet" method="post">
     <button type="submit" value="badgesManagement" name="richiesta"> Management badge</button>
     </form>
     
     <h3>3. Assegnazione Badges</h3>
     <form action="AssegnazioneServlet" method="post">
     <button type="submit" value="assegnazioneManagement" name="richiesta"> Management Assegnazione</button>
     </form>
     
     <h3>4. Indietro</h3>
     <form action="CustomersServlet" method="post">
     <input type="submit" value="indietro" name="richiesta">
     </form>
     
       <h3>5.logout<h3>
     <form action="LogoutServlet" method="post">
     <input type="submit" value="Logout" name="Logout">
     </form>


		<div class="main">
			<h1 class="title">Lista utenti</h1>
			<div style="padding-left:10%; padding-right:10%">

			</div>
		</div>
		
		-->
		<ul>
			 		<li>
		  		<div class="dropdown">
					<a href="UsersServlet?action=UsersManager" class="dropbtn">Gestione Utenti</a>				
				</div>
	  		</li>

	  		

	  		<li>
		  		<div class="dropdown">
					<a href="UsersServlet?action=LogsMenu" class="dropbtn">Logout</a>				
				</div>
	  		</li>
		</ul>
		
</body>
</html>