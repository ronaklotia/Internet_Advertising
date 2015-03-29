<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Category</title>
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
<form:form method="post" action="addCategories.htm" class="form-horizontal">

<fieldset>

<!-- Form Name -->
<legend>Add Category</legend>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="category">Category</label>
  <div class="controls">
    <input id="category" name="name" type="text" placeholder="" class="input-xlarge" required=""/>
    
  </div>
</div>

<!-- Button -->
<div class="control-group">
  <label class="control-label" for=""></label>
  <div class="controls">
    <button id="" name="" class="btn btn-primary">Add Category</button>
  </div>
</div>

</fieldset>

</form:form>
</body>
</html>