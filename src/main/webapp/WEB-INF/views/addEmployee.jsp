<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" 	href="resources/css/bootstrap.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
<style>
            .errows{
                color: red;
                font-style: italic;
                
            }
        </style>


</head>
<body>
<script src="http://code.jquery.com/jquery.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
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
<form:form commandName="employee" action="addEmployee.htm" method="post"  class="form-horizontal">

<fieldset>

<!-- Form Name -->
<legend>Add Employee</legend>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Employee</label>
  <div class="controls">
    <form:input path="name" id="text" name="employee" type="text" placeholder="Employee name" class="input-xlarge" required=""/>
     <span class="errows">
    <form:errors path="name"/>
    </span>
  </div>
</div>
<!-- Button -->
<div class="control-group">
  <label class="control-label" for="submit"></label>
  <div class="controls">
    <button id="submit" name="submit" class="btn btn-primary">Add Employee</button>
  </div>
</div>
</fieldset>

</form:form>
</body>
</html>