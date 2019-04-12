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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- Custom styles for this template -->
<link href="/prova.css" rel="stylesheet" type="text/css">
</head>

<body class="text-center">

<div class="clearfix" id="corpo">
  <div class="header" id="intestazione">
    <h1>INCORO</h1>
  </div>
  <div class="column content" id="visualizzazione">
    <h1>PROJECT NAME</h1>
    <p>we actually don't know what to insert in this space xD</p>
    <img class="doge" src="doge.jpg" width="300" height="300">
  </div>
  <div class="column menu" id="menu-destra">
    <form class="form-signin" action="/User/login" method="post">
      <ul style="list-style: none;">
        <li>
          <label>Username</label>
        </li>
        <li>
          <input type="text" name="username" id="inputUser" class="form-control" placeholder="Username" required autofocus>
        </li>
        <li>
          <label>Password</label>
        </li>
        <li>
          <input	type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        </li>
        <li>
          <button class="btn btn-lg btn-primary btn-block" type="submit">Signin</button>
        </li>
        <li>
          <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
        </li>
      </ul>
    </form>
  </div>
</div>
</body>
</html>






<!-- 
	<form action="/User/login" target="_blank" method="POST">
			First name:<br> <input type="text" name="username"
			placeholder="inserisci username"> <br> 
			Last name:<br> <input
			type="text" name="password" placeholder="inserisci password"> <br>
		<br> <input type="submit" value="Login">
	</form>
-->
