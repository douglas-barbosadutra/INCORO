<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>LABELS LIST HERE</h1>
	<div class="main">
			<div style="padding-left:10%; padding-right:10%">
				<table class="table table-striped">
				    <thead>
				      <tr>
				        <th>ID</th>
				        <th>LabelName</th>
				        <th>Fk to user</th>
      			      </tr>
				    </thead>
				    <tbody>
				    	<%
							List<LabelsDTO> labels = (List<LabelsDTO>) session.getAttribute("allLabels");
							if ( labels != null ){
								for(int i = 0; i < labels.size(); i++)
								{
						%>

								<tr>

									<th><%out.println(labels.get(i).getId()); %></th>

									<th><%out.println(labels.get(i).getName()); %></th>
									
									<th><%out.println(labels.get(i).getFktouser()); %></th>
														
								</tr><%

								}
							}
						%>
				    </tbody>
			  </table>
			</div>
		</div>
</body>
</html>