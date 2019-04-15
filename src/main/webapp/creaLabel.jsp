<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="it.contrader.dto.LabelDTO"%>
<%@ page import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>


<link href="/prova.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- Custom styles for this template -->

<title>JSP per la creazione delle thing</title>
</head>
<body>
	<!--
	 <form class="" action="/Thing/creaThing" method="post">
      <ul style="list-style: none;">
        <li>
          <label> Nome </label>
        </li>
        <li>
          <input type="text" name="name" id="insertThing" class="form-control" placeholder="nome" required autofocus>
        </li>
          <button class="btn btn-lg btn-primary btn-block" type="submit"> Inserisci Thing </button>
        </li>
      </ul>-->

      <div class="clearfix" id="corpo">
        <div class="header" id="intestazione">
          <h1>INCORO</h1>
        </div>
        <div class="column menu intero" id="menu-destra">
            <ul style="list-style: none;">
              <li>
                <label>Benvenuto</label>
              </li>
              <li>
                
              </li>
              <li>
              </li>
              <li>
              </li>
              <li>
                <label>MENU</label>
              </li>
              <li>
                <form action="/Label/indietro" method="get">
          	    <input type="submit" value="HOME BO" name="/Thing/indietro" href="/HomeBO" class="btn btn-primary t-action">
          			</form>
              </li>
            </ul>
        </div>

        <div class="column content" id="visualizzazione">
          <div class="col-md-8 themed-grid-col data-header" >
	           <form action="/Label/creaLabel" method="post" enctype="multipart/form-data">
  		         
      					<div class="form-group">
                  <p class="woocommerce-form-row woocommerce-form-row--wide form-row form-row-wide">
        						<label class="col-form-label"> Nome </label>
        						<input type="text" class="form-control" name="name" placeholder="Nome" required autofocus>
                  </p>
      					</div>
                  
                  <p class="woocomerce-FormRow form-row">
  					         <button type="submit" class="btn btn-lg btn-primary btn-block" name="action" value="creaLabel" >Inserisci</button>
                 </p>
      </form>
</body>
</html>