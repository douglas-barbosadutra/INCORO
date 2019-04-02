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
	<h2>Things menù</h2>
	<ul>
			<li>
		  		<div class="dropdown">
					<a href="ThingsServlet?action=openInsert" class="dropbtn">Inserisci Things</a>				
				</div>
	  		</li>
			
			<li>
		  		<div class="dropdown">
					<a href="ThingsServlet?action=openDelete" class="dropbtn">elimina things</a>				
				</div>
	  		</li>
	 		<li>
		  		<div class="dropdown">
					<a href="ThingsServlet?action=openList" class="dropbtn">Lista things</a>				
				</div>
	  		</li>			
			<li>
		  		<div class="dropdown">
					<a href="ThingsServlet?action=openUpdate" class="dropbtn">Aggiorna things</a>				
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