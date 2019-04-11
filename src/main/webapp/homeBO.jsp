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
<title>Business Owner view</title>
</head>
<body>
	<h1>Benvenuto BO</h1>
	<h2>
	
	</h2>
	<h2>MENU</h2>
	<ul>	
			<li>
		  		<div class="dropdown">
					<a href="/Thing/crea" class="dropbtn"> Inserisci Thing </a>				
				</div>
	  		</li> 
			<li>
		  		<div class="dropdown">
					<a href="/Thing/thingManagement" class="dropbtn"> Lista Thing </a>				
				</div>
	  		</li>
	  		 <li>
		  		<div class="dropdown">
					<a href="/Label/labelManagement" class="dropbtn"> Lista Label </a>				
				</div>
	  		</li>
	  		<li>
		  		<div class="dropdown">
					<a href="/Label/crea" class="dropbtn"> Inserisci Label </a>				
				</div>
	  		</li>
		</ul>
</body>
</html>	