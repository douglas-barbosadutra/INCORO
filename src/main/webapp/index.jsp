<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<div calss="acc">
	<script src="https://apis.google.com/js/platform.js" async defer></script>
</div>
<meta name="google-signin-client_id" content="649449410443-05dl4f94lnkq6mastqm2hb80n18cokrq.apps.googleusercontent.com">

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
    <p>	BENVENUTI	</p>
    <img class="doge" src="/immagineINCORO.jpg" width="450" height="300">
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
          <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        </li>
        <li>
          <button class="btn btn-lg btn-primary btn-block" type="submit">Signin</button>
        </li>
      </ul>
    </form>
  </div>
</div>

	<div class="g-signin2" data-onsuccess="onSignIn" id="myP"></div>
    <img id="myImg"><br>
    <p id="name"></p>
    <div id="status"></div>
   	<div>
   	<script type="text/javascript">
      	function onSignIn(googleUser) {
      		//window.location.href='success.jsp';
      		var profile = googleUser.getBasicProfile();
     		var imagurl=profile.getImageUrl();
      		var name=profile.getName();
      		var email=profile.getEmail();
      		document.getElementById("myImg").src = imagurl;
      		document.getElementById("name").innerHTML = name;
      		document.getElementById("myP").style.visibility = "hidden";
      		document.getElementById("status").innerHTML = 'Welcome '+name+'!<a href=success.jsp?email='+email+'&name='+name+'/>Continue with Google login</a></p>'
   		}
   	</script>
   	<button onclick="myFunction()">Sign Out</button>
   	<script>
   	   function myFunction() {
   	   		gapi.auth2.getAuthInstance().disconnect();
   	  		location.reload();
   		}
   	</script>
   </div>
</body>
</html>
