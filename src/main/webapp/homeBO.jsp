<%@ page import="java.io.*" %>
<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="java.io.*, java.net.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="ISO-8859-1">
<link href = "Stile.css" rel = "stylesheet" media = "screen">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- Custom styles for this template -->
<link href="/prova.css" rel="stylesheet" type="text/css">
<title>Business Owner view</title>
</head>
<body class="text-center">
	<% UserDTO userDTO = (UserDTO)session.getAttribute("utenteCollegato"); %>
	<div class="clearfix" id="corpo">
	  <div class="header" id="intestazione">
	    <h1>INCORO</h1>
	  </div>
		<div class="column menu intero" id="menu-destra">
	      <ul style="list-style: none;">
	        <li style="text-align:center">
	          <label>MENU</label>
	        </li>
	        <li class="btMenu">
						<a href="/Thing/crea?idUser=<%=userDTO.getIdUser() %>" class="btn btn-primary t-action" >Inserisci Thing</a>
	        </li>
					<li class="btMenu">
						<a href="/Thing/thingManagement?idUser=<%=userDTO.getIdUser() %>" class="btn btn-primary t-action" >Lista Things </a>
					</li>
					<li class="btMenu">
						<a href="/Label/labelManagement?idUser=<%=userDTO.getIdUser() %>" class="btn btn-primary t-action" >Lista Label</a>
					</li>
					<li class="btMenu">
						<a href="/Label/crea?idUser=<%=userDTO.getIdUser() %>" class="btn btn-primary t-action" >Inserisci Label</a>
					</li>
					
					

					<li class="btMenu">
						<a href="/Home/logout" class="btn btn-primary t-action" >Logout</a>
					</li>
	      </ul>
	  </div>
		<div class="column content" id="visualizzazione">
			<h1>Benvenuto <%	out.println(userDTO.getUsername());%></h1>
		
			<p>Probably we'll show some User statistics in this page, but actually we dont have such data</p>
		</div>
	</div>
</body>
</html>
