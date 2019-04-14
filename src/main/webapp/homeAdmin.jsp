<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*"%>
<html lang="en">
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
<% UserDTO userDTO = (UserDTO)session.getAttribute("utenteCollegato"); %>
	<div class="clearfix" id="corpo">
		<div class="header" id="intestazione">
			<h1>INCORO</h1>
		</div>

		<div class="column menu intero" id="menu-destra">
			<ul style="list-style: none;">
				<li><label>Benvenuto <%	out.println(userDTO.getUsername());%></label></li>
				
				<li></li>
				<li></li>
				<li><label>MENU</label></li>
				<li>
				<form action="/User/reindirizzaCrea"> 
				<button class="btn btn-lg btn-primary btn-block" type="submit" >Insert
						User</button>
						</form>
						
						<form action ="/XML/export">
			<button class="btn btn-lg btn-primary btn-block" type="submit"
			 onclick="return confirm('File esportato con successo!')"> Esporta File in XML
		
			</button>
		</form>

					<form action="/Home/logout"> 
				<button class="btn btn-lg btn-primary btn-block" type="submit" >Logout
						</button>
						</form>

				</li>
			</ul>
		</div>
		
		<div class="column content" id="visualizzazione">

<%
			List<UserDTO> users = (List<UserDTO>) session.getAttribute("allUserDTO");
		if (!(users == null)){
			for (int i = 0; i < users.size(); i++) {
		%>
			<div class="col-md-8 themed-grid-col data-header">
				<div class="pb-3">

					<span><b class="tipo-dato">ID</b> <span
						class="contenuto-dato"> <%
 	out.println(users.get(i).getIdUser());
 %></span></span>
				</div>
				<div class="row">
					<div class="col-md-6 themed-grid-col data-cels">
						<span><b>USERNAME:</b> <span class="contenuto-dato">
								<%
									out.println(users.get(i).getUsername());
								%>
						</span></span>
					</div>
					<div class="col-md-6 themed-grid-col data-cels">
						<span><b>PASSWORD:</b> <span class="contenuto-dato">
								<%
									out.println(users.get(i).getPassword());
								%>
						</span></span>
					</div>
					<div class="col-md-6 themed-grid-col data-cels">
						<span><b>TIPO:</b> <span class="contenuto-dato"> <%
 	out.println(users.get(i).getType());
 %></span></span>
					</div>
				</div>
				<p class="link-row">
					<a href="/User/delete?id=<% out.println(users.get(i).getIdUser()); %>" class="btn btn-primary my-2 t-action">
					Elimina</a> 
					<a href="/User/reindirizzaModifica?id=<% out.println(users.get(i).getIdUser()); %>" class="btn btn-secondary my-2 t-action">
					Modifica</a> 
					<a href="#" class="btn btn-primary my-2 t-action">Terza
						action</a>
				</p>
			</div>

			<%
				}
		}
			%>

		</div>
	</div>
</body>
</html>
