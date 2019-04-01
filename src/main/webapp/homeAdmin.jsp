<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<% String mode = session.getAttribute("allUsers").toString(); %>

		<ul>
			<li>
		  		<div class="dropdown">
					<a href="UsersServlet?action=insert" class="dropbtn">Inserisci utente</a>				
				</div>
	  		</li>
	  		<li>
		  		<div class="dropdown">
					<a href="UsersServlet?action=deletet" class="dropbtn">Elimina utente</a>				
				</div>
	  		</li>
	 		<li>
		  		<div class="dropdown">
					<a href="UsersServlet?action=UsersManager" class="dropbtn">Lista utenti</a>				
				</div>
	  		</li>
	  		<li>
		  		<div class="dropdown">
					<a href="UserServlet?action=homeLogs" class="dropbtn">Logout</a>				
				</div>
	  		</li>
		</ul>

		<div class="main">
			<h1 class="title">Lista utenti</h1>
			<div style="padding-left:10%; padding-right:10%">
				<table class="table table-striped">
				    <thead>
				      <tr>
				        <th>ID</th>
				        <th>Nome</th>
				        <th>Cognome</th>
				        <th>Email</th>
				        <th>Telefono</th>
				        <% if(mode.equals("delete")){
				        	%><th>Opzioni</th><%
				        }%>
				      </tr>
				    </thead>
				    <tbody>
				    	<%
							List<UsersDTO> users = (List<UsersDTO>)session.getAttribute("users_list");
							for(int i = 0; i < users.size(); i++)
							{
						%>

								<tr>

									<th><%out.println(users.get(i).getId()); %></th>

									<th><%out.println(users.get(i).getUsername()); %></th>
									
									<th><%out.println(users.get(i).getPassword()); %></th>
									
									<%if(mode.equals("delete")){

										%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="UserServlet?action=deleteUser&id=<%=users.get(i).getId()%>">elimina</a></th><%

									} %>
								</tr><%

							}

						%>
				    </tbody>
			  </table>
			</div>
		</div>
</body>
</html>