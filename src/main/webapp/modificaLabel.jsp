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
<body class="text-center">
	<div class="clearfix" id="corpo">
		<div class="header" id="intestazione">
			<h1>INCORO</h1>
		</div>



<h2><center>------- UPDATE LABEL -------</center></h2>
<form action="/Label/indietro" method="get">
	<input type="submit" value="HOME" name="richiesta" class="btn btn-primary my-2 t-action">
</form>

     <h3>Inserisci i dati dell'utente</h3>
     <form action="/Label/modifica" method="get">
     

     
        <!--    <% //Integer id = (Integer) session.getAttribute("id"); %> -->
     	
     	<h4>Name: <input type = "text" id = "label" name ="name" placeholder = "inserisci name"></h4>
     	
     
     	


     	
     	<button type="submit" value="/Label/modifica?idLabel=<% //out.println(id); %>" >Aggiorna</button>
     	
     </form>

</body>
</html>