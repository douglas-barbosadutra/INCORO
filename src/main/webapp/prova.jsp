
<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*" %><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/prova.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<% UserDTO userDTO = (UserDTO)session.getAttribute("utenteCollegato"); %>

<div class="clearfix" id="corpo">
		<div class="header" id="intestazione">
			<h1>INCORO</h1>
		</div>
		</div>

	<div class="main">
			<div style="padding-left:10%; padding-right:10%">
				<table class="table table-striped">
				    <thead>
				      <tr>
				        <th> ID thing </th>
			<th> Code </th>
			<th> Image </th>
			<th> Name </th>
			<th> Xml </th>
			<th> ID Label </th>
			<th> ID User </th>
      			      </tr>
				    </thead>
				    <tbody>
				    	<%
							List<ThingDTO> things = (List<ThingDTO>) session.getAttribute("allThing");
							if ( things != null ){
								for(int i = 0; i < things.size(); i++)
								{
						%>
								<tr>

									<th><%out.println(things.get(i).getId()); %></th>

									<th><%out.println(things.get(i).getCode()); %></th>
									
									<th><%out.println(things.get(i).getImage()); %></th>
									
									<th><%out.println(things.get(i).getName()); %></th>
									
									<th><%out.println(things.get(i).getXml()); %></th>
									
									<th><%out.println(things.get(i).getIdLabel()); %></th>
									
									<th><%out.println(things.get(i).getIdUser()); %></th>
									
									<th> <a href="/Thing/openUpdateThing?id=<%=things.get(i).getId()%>"
									class="btn btn-primary my-2 t-action"> Modifica </a> </th>
									
									<th> <a href="/Thing/delete?id=<%=things.get(i).getId()%>"
									class="btn btn-secondary my-2 t-action"> Elimina  </a> </th>
									
															
								</tr> <%

								}
							}
						%>	 
		</tbody>
	</table>
			</div>
		</div>
		<form action="/Thing/indietro" method="get">
	    <input type="submit" value="HOME BO" name="/Thing/indietro">
	</form>
</body>
</html>