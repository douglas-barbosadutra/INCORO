<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.*"%>
<html lang="en">
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
	<%
		UserDTO userDTO = (UserDTO) session.getAttribute("utenteCollegato");
	%>
	<div class="header" id="intestazione">
		<h1>INCORO</h1>
	</div>
	<div class="column menu intero" id="menu-destra">
		<ul style="list-style: none;">
			<li style="text-align: center"><label>MENU</label></li>
			<li class="btMenu"><a
				href="/Thing/crea?idUser=<%=userDTO.getIdUser()%>"
				class="btn btn-primary t-action">Inserisci Thing</a></li>
			<li class="btMenu"><a
				href="/Thing/thingManagement?idUser=<%=userDTO.getIdUser()%>"
				class="btn btn-primary t-action">Lista Things </a></li>
			<li class="btMenu"><a
				href="/Label/labelManagement?idUser=<%=userDTO.getIdUser()%>"
				class="btn btn-primary t-action">Lista Label</a></li>
			<li class="btMenu"><a
				href="/Label/crea?idUser=<%=userDTO.getIdUser()%>"
				class="btn btn-primary t-action">Inserisci Label</a></li>
			<li class="btMenu"><a href="/Home/logout"
				class="btn btn-primary t-action">Logout</a></li>
		</ul>
	</div>
	<div class="column content" id="visualizzazione">

		<table class="table table-hover tdCentered">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Code</th>
					<th scope="col">Image</th>
					<th scope="col">Name</th>
					<th scope="col">Xml path</th>
					<th scope="col">Label</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<ThingDTO> things = (List<ThingDTO>) session.getAttribute("allThing");
					if (things != null) {
						for (int i = 0; i < things.size(); i++) {
				%>
				<tr >
					<th scope="row">
						<%
							out.println(things.get(i).getId());
						%>
					</th>
					<td><a
						href="/Thing/showCode?id=<%out.println(things.get(i).getId());%>"
						class="btn btn-primary my-2 t-action"> Visualizza codice</a></td>
					<td>
						<%
							out.println(things.get(i).getImage());
						%>
					</td>
					<td>
						<%
							out.println(things.get(i).getName());
						%>
					</td>
					<td>
						<%
							out.println(things.get(i).getXml());
						%>
					</td>
					<td>
						<%
							out.println(things.get(i).getLabel().getIdLabel());
						%>
					</td>
				</tr>
				<tr>
					<th scope="row" class="optionTD">Option</th>
					<td colspan="2" class="optionTD">
					<p class = "link-row">
					<a class="btn btn-primary my-2 t-action"
						href="/Thing/openUpdateThing?id=<%=things.get(i).getId()%>">
							Modifica </a> </p>
					<td colspan="2" class="optionTD">
					<a class="btn btn-secondary my-2 t-action"
						href="/Thing/delete?id=<%=things.get(i).getId()%>"> Elimina </a>
					</td>
				</tr>
				<%
					}
					} else {
				%>
				<tr>
					<th scope="row">!!!</th>
					<td>nessun oggetto rilevato per l utente attivo
					<td>
				</tr>

				<%
					}
				%>
			</tbody>
		</table>

	</div>


</body>
</html>