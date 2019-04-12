<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.LabelDTO"%>
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
					<h4>Label: <select  id = "user" name ="idLabel" placeholder = "inserisci Label">
     		<%
     		out.println(1);
     			List<LabelDTO> list = (List<LabelDTO>)session.getAttribute("list");
     			out.println(2);
     			for(int i=0; i<list.size(); i++){
     				out.println(i);
     				%><option value ="<%out.println(list.get(i).getIdLabel());%>"> <%out.println(list.get(i).getName());%></option><%
     			}
     		%>
     			</select>
		</h4>
					<button style="margin-top:2%; margin-left:40%;" type="submit" class="btn btn-primary" name="action" value="updateThing" >Inserisci</button>

				
</form>
</body>
</html>

