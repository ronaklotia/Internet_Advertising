<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <jsp:useBean id="list1" class="com.internetadvert.edu.Pojo.NetworkList" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add exchange</title>
<link rel="stylesheet" type="text/css"	href="resources/css/bootstrap.css">

<style>
            .errows{
                color: red;
                font-style: italic;
                
            }
        </style>


</head>
<body>
<div class="container">
<div class="navbar navbar-inverse">
              <div class="navbar-inner">
                <div class="container">
                  <ul class="nav nav-pills">
                    <li class="active">
                    <a href=""></a></li>
                    <li><a href="network.htm" >Add	Network</a></li>
					<li><a href="employee.htm" >Add Employee</a></li>

					<li><a href="exchange.htm">Add Exchange</a></li>

					<li><a href="company.htm">Add
							Advertising Company</a></li>

					<li><a href="website.htm">Add Website</a></li>

					<li><a href="logout.htm">Logout</a></li>
                  </ul>
                </div>
              </div>
            </div>    
              
</div>
<form:form commandName="exchange" action="addExchange.htm" method="post" class="form-horizontal">

<fieldset>

<!-- Form Name -->
<legend>Add Exchange</legend>


<div class="control-group">
  <label class="control-label" for="textinput">Employee</label>
  <div class="controls">
  	<select name="emp">
 	<c:forEach var="i" items="${list1.emList}"> 
 	<option value="${i.name}">${i.name}</option>
 	</c:forEach>
	</select><br>
    
  </div>
</div>

<div class="control-group">
  <label class="control-label" for="textinput">Network</label>
  <div class="controls">
  	<select name="net">
 	<c:forEach var="i" items="${list1.network}"> 
 	<option value="${i.name}">${i.name}</option>
 	</c:forEach>
	</select><br>
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Exchange</label>
  <div class="controls">
    <form:input path="exchangeName" id="textinput" name="name" type="text" placeholder="" class="input-xlarge" required=""/>
   	<span class="errows">
   	<form:errors path="exchangeName"></form:errors>
   	</span>
   </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Username</label>
  <div class="controls">
    <input id="textinput" name="username" type="text" placeholder="" class="input-xlarge" required=""/>
    
  </div>
</div>

<!-- Password input-->
<div class="control-group">
  <label class="control-label" for="passwordinput">Password</label>
  <div class="controls">
    <input id="passwordinput" name="password" type="password" placeholder="" class="input-xlarge" required=""/>
    
  </div>
</div>

<!-- Button -->
<div class="control-group">
  <label class="control-label" for=""></label>
  <div class="controls">
    <button id="" name="" class="btn btn-primary">Add Exchange</button>
  </div>
</div>

</fieldset>

</form:form>
</body>
</html>