<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<H1> Immetti il codice </H1>
        <FORM ACTION="ThingsServlet?action=insertFile" METHOD="POST">
        INSERISCI IL CODICE:
        <BR>
        <TEXTAREA NAME="textarea1" ROWS="25" COLS ="100"></TEXTAREA>
        <BR>
        <INPUT TYPE="SUBMIT" VALUE="Inserisci testo">
       	</FORM>
       	
       	<form action="LabelsServlet?action=Indietro" method="post">
		<!-- <input type="submit" value="" name="richiesta">-->
</body>
</html>