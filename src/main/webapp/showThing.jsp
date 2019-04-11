<%@ page import="java.util.List" %>
<%@ page import="it.contrader.dto.ThingDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<% out.println(" prova di funzionamento");%>
	<br>
	<br>
	
	<table>
		

	      <tr>
				        <th> ID thing</th>
				        <th> code </th>
				        <th> image </th>
				        <th> name</th>
				        <th> xml </th>
				        <th> id label</th>
				        <th> id user</th>
      	</tr>
	
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
									
									
								</tr> <%

								}
							}
						%>	 
	</tbody>
	</table>
</body>
</html>