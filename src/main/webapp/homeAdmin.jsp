<html>
<head>
<title>Menu Principale</title>
</head>
<body>
	<h1>Benvenuto: ${utente.getUsername()}</h1>
	
		<ul>

			<li>

		  		<div class="dropdown">

					<a href="UsersServlet?action=insert" class="dropbtn">Inserisci utente</a>				

				</div>

	  		</li>

	  		<li>

		  		<div class="dropdown">

					<a href="UsersServlet?action=delete" class="dropbtn">Elimina utente</a>				

				</div>

	  		</li>

	  		<li>

		  		<div class="dropdown">

					<a href="UsersServlet?action=UsersManager" class="dropbtn">Lista utenti prova</a>				

				</div>

	  		</li>
			
			<li>
		  		<div class="dropdown">
					<a href="UsersServlet?action=update" class="dropbtn">Aggiorna utenti</a>				
				</div>
	  		</li>
	  		
	  		<li>

		  		<div class="dropdown">

					<a href="UsersServlet?action=logout" class="dropbtn">Logout</a>				

				</div>

	  		</li>

		</ul>
	
	<!-- 
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
-->

</body>
</html>