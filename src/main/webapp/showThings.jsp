
<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*" %><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>THING LIST HERE</h1>
	<div class="main">
			<div style="padding-left:10%; padding-right:10%">
				<table class="table table-striped">
				    <thead>
				      <tr>
				        <th>ID</th>
				        <th>ThingsName</th>
				        <th>id Proprietario</th>
				        <th>id label</th>
      			      </tr>
				    </thead>
				    <tbody>
				    	<%
							List<ThingsDTO> things = (List<ThingsDTO>)session.getAttribute("allThings");
							if ( things != null ){
								for(int i = 0; i < things.size(); i++)
								
								{
						%>

								<tr>

									<th><%out.println(things.get(i).getId()); %></th>

									<th><%out.println(things.get(i).getName()); %></th>
									
									<th><%out.println(things.get(i).getFktouser()); %></th>
									
									<th><%out.println(things.get(i).getFktolabel()); %></th>
										
									<th><a href="ThingsServlet?action=openUpdate&id=<%out.println(things.get(i).getId());%>"> Modifica</a></th>
									<th><a href=ThingsServlet?action=delete&id=<%out.println(things.get(i).getId());%>"> Elimina</a></th>			
									<th><a href="ThingsServlet?action=openInsertCode&id=<%out.println(things.get(i).getId());%>"> Inserisci Codice </a></th>
											
								</tr><%

								}
							}
						%>
					 
				    </tbody>
				    
			  </table>
			</div>
		</div>
		<form action="LabelsServlet?action=Indietro" method="post">
		<input type="submit" value="HOME BO" name="richiesta">
</body>
</html>