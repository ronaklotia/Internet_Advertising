<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="list4" class="com.internetadvert.edu.Pojo.ProductList"
	scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Bid</title>
<link rel="stylesheet" type="text/css" 	href="resources/css/bootstrap.css">
</head>
<body>
<div class="container">
<div class="navbar navbar-inverse">
              <div class="navbar-inner">
                <div class="container">
                  <ul class="nav nav-pills">
                    <li class="active">
                    <a href="categories.htm">Add Categories</a></li>
                    <li><a href="product.htm" >Add Product</a></li>                    
                    <li><a href="bid.htm" >Add Bid</a></li>                    
                    <li><a href="payment.htm" >Make Payment</a></li>
                    <li><a href="logout.htm" >Logout</a></li>
                  </ul>
                </div>
              </div>
            </div>            
</div>
	<form:form  action="addBid.htm" method="post" enctype="multipart/form-data" commandName="image"  modelAttribute="image" class="form-horizontal">
	<fieldset>

<!-- Form Name -->
<legend>Add Bid</legend>

<div class="control-group">
  <label class="control-label" for="occupation">Product</label>
  <div class="controls">
   <select name="product">
			<c:forEach var="i" items="${list4.productList}">
				<option value="${i.name}">${i.name}</option>
			</c:forEach>
		</select><br>
    
  </div>
</div>


<div class="control-group">
  <label class="control-label" for="occupation">Exchange</label>
  <div class="controls">
     <select name="Exchange">
			<c:forEach var="i" items="${list4.exchangeList}">
				<option value="${i.exchangeName}">${i.exchangeName}</option>
			</c:forEach>
		</select><br>
  </div>
</div>

<div class="control-group">
  <label class="control-label" for="occupation"> Gender</label>
  <div class="controls">
  <select
			name="gender">
			<option value="Male">Male</option>
			<option value="Female">Female</option>
		</select><br> 
    
  </div>
</div>



<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="occupation">Occupation</label>
  <div class="controls">
    <input   id="occupation" name="occupation" type="text" placeholder="Occupation" class="input-xlarge" required=""/>
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="price">Age</label>
  <div class="controls">
    <input id="price" name="age" type="number" placeholder="Age" class="input-xlarge" required=""/>
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="type">Computer Type</label>
  <div class="controls">
    <input  id="type" name="compType" type="text" placeholder="Computer Type" class="input-xlarge" required=""/>
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Add Type</label>
  <div class="controls">
    <input id="textinput" name="adtype" type="text" placeholder="Add Type" class="input-xlarge"/>
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="price">Price</label>
  <div class="controls">
    <input id="price" name="price" type="number" placeholder="Price" class="input-xlarge" required=""/>
    
  </div>
</div>

<div class="control-group">
  <label class="control-label" for="occupation"> Image</label>
  <div class="controls"> 
    <input type="file" name="image" />
  </div>
</div>


<!-- Button -->
<div class="control-group">
  <label class="control-label" for=""></label>
  <div class="controls">
    <button id="" name="" class="btn btn-primary">Add Bid</button>
  </div>
</div>

</fieldset>
		
		
		
		
		
		
		
		
	</form:form>
</body>
</html>