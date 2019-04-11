<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP  pagina per l'aggiornamento delle thing</title>
</head>
<body>
	
	<form action="/Thing/updateThing" method="post">
			
					
					<div class="form-group">
						<label class="col-form-label"> Code </label>
						<input type="text" class="form-control" name="code">				
						
					</div>
					
					<div class="form-group">
						<label class="col-form-label"> Image </label>
						<input type="text" class="form-control" name="image">				
						
					</div>
					
					<div class="form-group">
						<label class="col-form-label"> Nome </label>
						<input type="text" class="form-control" name="name">				
					</div>
			
					<div class="form-group">
						<label class="col-form-label"> Xml </label>
						<input type="text" class="form-control" name="xml">				
					</div>
					<div class="form-group">
						<label class="col-form-label"> ID Label </label>
						<input type="text" class="form-control" name="idLabel">				
					</div>
					<div class="form-group">
						<label class="col-form-label"> ID User </label>
						<input type="text" class="form-control" name="idUser">				
					</div>
					<button style="margin-top:2%; margin-left:40%;" type="submit" class="btn btn-primary" name="action" value="updateThing" >Inserisci</button>	
				
</form>
</body>
</html>

