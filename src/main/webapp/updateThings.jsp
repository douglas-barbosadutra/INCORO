<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>UPDATE THINGS HERE</h1>
	
	<h3>Inserisci i dati della Things</h3>
    
    <form action="ThingsServlet?action=update" method="post">
      
     	<!-- <h4>IdThings: <input type = "text" id = "thing" name ="idThings" value=" out.println(id); " disabled></h4>-->
     	
     	<h4>Name: <input type = "text" id = "things" name ="name" placeholder = "inserisci nome"></h4>
     	
     	<h4>ID Proprietario: <input type = "text" id = "things" name ="fktouser" placeholder = "inserisci l fk to user"></h4>
     	
     	<h4>ID Label: <input type = "text" id = "things" name ="fktolabel" placeholder = "inserisci l fk to label"></h4>
     	 	
    	<input type="submit" value="Aggiorna Things" name="action">
     	
     </form>
</body>
</html>