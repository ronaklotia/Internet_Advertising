<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 	<jsp:useBean id="bid" class="com.internetadvert.edu.Pojo.ProductList" scope="request" />
    <jsp:useBean id="sessio" class="com.internetadvert.edu.Pojo.User" scope="session" />
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${sessio.username } Account</title>
<link rel="stylesheet" type="text/css" 	href="resources/css/bootstrap.css">

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
<form class="form-horizontal">

<div align="center" id='container1' class='row-fluid'>
Adds You might be intrested in<br>
      <div class='span12 main'>
       <c:forEach var="i" items="${bid.bidList}">
		<img alt="${i.ad1 }" src="resources/${i.name}">
		</c:forEach>
      </div>
      <h1>Welcome ${sessio.username}</h1>
</div>

<div  id="content">
	<div id="left">
		<p>Free Cloudy Water Sports  is yours to use.</p>
		<p>Ulysses, Ulysses - Soaring through all the galaxies. In search of Earth, flying in to the night. Ulysses, Ulysses - Fighting evil and tyranny, with all his power, and with all of his might. Ulysses - no-one else can do the things you do. Ulysses - like a bolt of thunder from the blue. Ulysses - always fighting all the evil forces bringing peace and justice to all.</p>
				<ul>
			<li><a href="#">One for all and all for one.</a></li>
			<li><a href="#">I never spend much time in school.</a></li>
			<li><a href="#">Soaring through all the galaxies.</a></li>
		</ul>
		<p>One for all and all for one, Muskehounds are always ready. One for all and all for one, helping everybody. One for all and all for one, it's a pretty story. Sharing everything with fun, that's the way to be. One for all and all for one, Muskehounds are always ready. One for all and all for one, helping everybody. One for all and all for one, can sound pretty corny. If you've got a problem chum, think how it could be.</p>
	</div>

</div>


</form>
</body>
</html>