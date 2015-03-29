<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css">
<title>Enter Website</title>
</head>
<body>

<div class="container">
<div class="navbar navbar-inverse">
              <div class="navbar-inner">
                <div class="container">
                  <ul class="nav nav-pills">                    
					<li><a href="custhome.htm" >Home</a></li>
					<li><a href="logout.htm">Logout</a></li>
                  </ul>
                </div>
              </div>
            </div>    
              
</div>


	<form:form method="post" action="log.htm">
		<fieldset>
			<div class="container">
				<div class="hero-unit">
					<div class="container text-center">

						<h2>Enter The Name of the Website</h2>
						<input type="text" name="website" placeholder="Enter url" required="" /><br>
						<button id="" name="" class="btn btn-primary">Go>></button>
					</div>
					<!-- Button -->
					
				</div>
			</div>


		</fieldset>

	</form:form>
</body>
</html>