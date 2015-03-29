<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta charset=”utf-8”> 
<title>Enter URL</title>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
</head>
<body>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<div class="container">
<div class="hero-unit">  
	<div class="container text-center">       
		<form:form method="post" action = "welcome.htm">
         <h2>Enter The Name of the Website</h2> 
         <input path="website" type="text" name="website" placeholder="Enter url" required=""/>
         <input type="submit" value="Go>>" class="btn btn-primary btn-block">         
         </form:form>
        </div>
</div>
</div>
</body>
</html>