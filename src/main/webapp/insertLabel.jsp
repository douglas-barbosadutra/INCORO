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
	<h1>INSERT LABELS HERE</h1>
	<h2><center>------- INSERT LABEL -------</center></h2>
	<form action="LabelsServlet?action=Indietro" method="post">
	<input type="submit" value="HOME BO" name="richiesta">
	</form>
	
     	<h3>Inserisci i dati della labels</h3>
     	<form action="LabelsServlet?action=insert" method="post">
     	
     		<h4> Nome: <input type = "text" id = "nomelabel" name ="name" placeholder = "inserisci nome label"></h4>     		
     		
     	<input type="submit" value="Inserisci Label" name="action">  	 	
     </form>
</body>
</html>