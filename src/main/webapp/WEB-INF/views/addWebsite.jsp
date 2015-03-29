<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <jsp:useBean id="exc" class="com.internetadvert.edu.Pojo.ProductList" scope="session" />
   <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Website</title>
<link rel="stylesheet" type="text/css" 	href="resources/css/bootstrap.css">
<style>
            .errows{
                color: red;
                font-style: italic;
                
            }
        </style>


<script>
	
	var ajaxRequest; // The variable that makes Ajax possible!
	function ajaxFunction() {
		try {
			// Opera 8.0+, Firefox, Safari
			ajaxRequest = new XMLHttpRequest();
			// alert("Your browser broke 1!");
		} catch (e) {
			// Internet Explorer Browsers
			try {
				ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
				//  alert("Your browser broke! 2");
			} catch (e) {
				try {
					ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
					//  alert("Your browser broke! 3");
				} catch (e) {
					// Something went wrong
					alert("Your browser broke!4");
					return false;
				}
			}
		}
	}

	function isWebsiteValid() {
		ajaxFunction();
		// Here processRequest() is the callback function.

		//alert("after onreadystatechane");

		var web = document.getElementById("web2");
		var url = "isWeb.htm?web=" + web.value;
		ajaxRequest.open("GET", url, true);
		ajaxRequest.send(null);
		ajaxRequest.onreadystatechange = webRequest;

	}

	function webRequest() {
		//alert("in proreq ");
		if (ajaxRequest.readyState == 4) {
			//   alert("in proreq with 4");
			if (ajaxRequest.status == 200) {
				//  alert("in proreq with 2");
	
				switch (ajaxRequest.responseText) {
				
				case "true":
					
					document.getElementById("webID").value = "Available";
					break;
				case "false":
					document.getElementById("webID").value = " Not Available";
					break;				
				}

			}
		}
	}
</script>



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
<form:form commandName="website" action="addWebsite.htm" method="post" class="form-horizontal">

<fieldset>

<!-- Form Name -->
<legend>Add Website</legend>

<div class="control-group">
  <label class="control-label" for="website">Exchange</label>
  <div class="controls">
  <select name="exch">
 	<c:forEach var="i" items="${exc.exchangeList}"> 
 	<option value="${i.exchangeName}">${i.exchangeName}</option>
 	</c:forEach>
	</select><br>
    
  </div>
</div>


<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="website">Website</label>
  <div class="controls">
    <form:input path="name" id="web2" name="website" type="text" placeholder="" onkeyup="isWebsiteValid();" class="input-xlarge" required=""/>
   	
   	<br>
   	<span class="errows">
   	<form:errors path="name"/>
   	</span>
   	<input type="text" id="webID" readonly="readonly">
  </div>
</div>

<!-- Button -->
<div class="control-group">
  <label class="control-label" for=""></label>
  <div class="controls">
    <button id="" name="" class="btn btn-primary">Add Website</button>
  </div>
</div>

</fieldset>

</form:form>
</body>
</html>