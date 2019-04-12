<%@ page import="java.util.List" %>
<%@ page import="it.contrader.dto.ThingDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<% out.println("LISTA THING");%>
	
	<table>
	      <tr>
			<th> ID thing </th>
			<th> Code </th>
			<th> Image </th>
			<th> Name </th>
			<th> Xml </th>
			<th> ID Label </th>
			<th> ID User </th>
      	 </tr>
		<tbody>
		
		<form action="/Thing/indietro" method="get">
	    <input type="submit" value="HOME BO" name="/Thing/indietro">
	</form>
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
									
									<th> <a href="/Thing/openUpdateThing?id=<%=things.get(i).getId()%>"> Modifica </a> </th>
									
									<th> <a href="/Thing/delete?id=<%=things.get(i).getId()%>"> Elimina  </a> </th>
									
															
								</tr> <%

								}
							}
						%>	 
		</tbody>
	</table>
</body>
</html>