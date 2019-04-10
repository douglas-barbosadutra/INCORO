<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>LOGIN PAGE</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/signin.css" rel="stylesheet">

</head>

<body class="text-center">
	<form action="/User/login" target="_blank" method="POST">
			First name:<br> <input type="text" name="username"
			placeholder="inserisci username"> <br> 
			Last name:<br> <input
			type="text" name="password" placeholder="inserisci password"> <br>
		<br> <input type="submit" value="Login">
	</form>
</body>
</html>