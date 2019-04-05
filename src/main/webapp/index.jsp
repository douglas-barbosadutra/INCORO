<html>
<head>
<title>Login Trader</title>
</head>
<body>
	<div>
		<form action="LoginServlet" method="post">
			<h3>
				username: <input type="text" id="#user" name="username"
					placeholder="inserisci username">
			</h3>
			<h3>
				password: <input type="password" id="pass" name="password"
					placeholder="inserisci password">
			</h3>
			<button type="submit" value="Login" name="pulsante">Login</button>
			<!--  <br> <a href="register.jsp"> Registrati </a> -->
		</form>
	</div>
	<div class="dropdown">
					<a href="ImageServlet?action=openInsert" class="dropbtn">Inserisci Immagine</a>				
				</div>
	<div>
		<a href="sessionServlet?action=s1"> prova sessione </a>
	</div>

</body>
</html>