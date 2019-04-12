<%@ page import="java.util.List" %>
<%@ page import="it.contrader.dto.LabelDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!-- Custom styles for this template -->
<link href="/prova.css" rel="stylesheet" type="text/css">
</head>
<body class="text-center">
	<div class="clearfix" id="corpo">
		<div class="header" id="intestazione">
			<h1>INCORO</h1>
		</div>
	<% out.println("LISTA LABEL");%>
	
	<table>
	      <tr>
			<th> ID label </th>
			<th> Name </th>
			<th> ID User </th>
      	 </tr>
		<tbody>
				    	<%
							List<LabelDTO> labels = (List<LabelDTO>) session.getAttribute("allLabel");
							if ( labels != null ){
								for(int i = 0; i < labels.size(); i++)
								{
						%>
								<tr>

									<th><%out.println(labels.get(i).getIdLabel()); %></th>
									
									<th><%out.println(labels.get(i).getName()); %></th>
									
									<th><%out.println(labels.get(i).getIdUser()); %></th>
									
									<th> <a href="/Label/openUpdate?idLabel=<%=labels.get(i).getIdLabel()%>"> Modifica </a> </th>
									
									<th> <a href="/Label/delete?idLabel=<%=labels.get(i).getIdLabel()%>"> Elimina  </a> </th>
									
															
								</tr> <%

								}
							}
						%>	 
		</tbody>
	</table>
	<form action="/Label/indietro" method="get">
	    <input type="submit" value="HOME BO" name="/Label/indietro">
	</form>
</body>
</html>