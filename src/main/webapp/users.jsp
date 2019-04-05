<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link href = "Stile.css" rel = "stylesheet" media = "screen">
<title>Insert title here</title>
</head>
<body>

	<ul>
		<li>
			<div class="dropdown">
				<a href="UsersServlet?action=openInsert" class="dropbtn">
					Inserisci utente</a>
			</div>
		</li>
		<!--
		<li>
			<div class="dropdown">
				<a href="UsersServlet?action=UsersManager" class="dropbtn">
					Lista utenti</a>
			</div>
		</li>
		-->
		<li>
			<div class="dropdown">
				<a href="UsersServlet?action=LogsMenu" class="dropbtn">Logout</a>
			</div>
		</li>
	</ul>

	<div class="main">
		<h1 class="title">Lista utenti</h1>
		<div style="padding-left: 10%; padding-right: 10%">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Username</th>
						<th>Password</th>
						<th>Type</th>
						<th>Elimina</th>
						<th>Modifica</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<UsersDTO> users = (List<UsersDTO>) session.getAttribute("users_list");
						for (int i = 0; i < users.size(); i++) {
					%>

					<tr>

						<th>
							<%
								out.println(users.get(i).getId());
							%>
						</th>

						<th>
							<%
								out.println(users.get(i).getUsername());
							%>
						</th>

						<th>
							<%
								out.println(users.get(i).getPassword());
							%>
						</th>

						<th>
							<%
								out.println(users.get(i).getType());
							%>
						</th>

						<th><a href="UsersServlet?action=delete&id=<%out.println(users.get(i).getId());%>">Elimina</a></th>
							


						<th><a href="UsersServlet?action=openUpdate&id=<%out.println(users.get(i).getId());%>">Modifica</a></th>
                                   
                                   



					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			
			<a href="XMLServlet" class="btn btn-info" role="button">Esporta XML</a>
		</div>
	</div>
</body>
</html>