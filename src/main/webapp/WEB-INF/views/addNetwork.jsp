<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   <jsp:useBean id="network" class="com.internetadvert.edu.Pojo.NetworkList" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script>
function invald(){
	var x = ${valid}
	if (x == true){
		alert("Network Already Exists");
	}
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Network</title>
<link rel="stylesheet" type="text/css" 	href="resources/css/bootstrap.css">
</head>
<body onload="invald()">
 
<c:if test="${valid==true}">
  invald();
</c:if>

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
<form:form action="addnetwork.htm" method="post" class="form-horizontal">
<fieldset>
<legend>Add Network</legend>
<div class="control-group">
  <label class="control-label" for="textinput">Network</label>
  <div class="controls">
   <select name="location">
 <c:forEach var="i" items="${network.networkList} }"> 
 <option value="${i}">${i}</option>
 </c:forEach>
</select>
  </div>
</div>

<div class="control-group">
  <label class="control-label" for="submit"></label>
  <div class="controls">
    <button id="submit" name="submit" class="btn btn-primary">Add Network</button>
  </div>
</div>

</fieldset>
</form:form>
</body>
</html>