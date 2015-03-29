<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   <jsp:useBean id="cat" class="com.internetadvert.edu.Pojo.CategoryList" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
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
<form:form action="addProduct.htm" method="post" class="form-horizontal">


<fieldset>

<!-- Form Name -->
<legend>Add Product</legend>

<div class="control-group">
  <label class="control-label" for="name">Category</label>
  <div class="controls">
   <select name="product">
 	<c:forEach var="i" items="${cat.catogriesList}"> 
 	<option value="${i.categoryName}">${i.categoryName}</option>
 	</c:forEach>
</select><br>
    
  </div>
</div>


<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="name">Product Name</label>
  <div class="controls">
    <input  id="name" name="name" type="text" placeholder="Product Name" class="input-xlarge" required=""/>
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="price">Price</label>
  <div class="controls">
    <input path="price" id="price" name="price" type="number" placeholder="Price" class="input-xlarge" required=""/>
    
  </div>
</div>

<!-- Button -->
<div class="control-group">
  <label class="control-label" for=""></label>
  <div class="controls">
    <button id="" name="" class="btn btn-primary">Add Product</button>
  </div>
</div>

</fieldset>



</form:form>
</body>
</html>