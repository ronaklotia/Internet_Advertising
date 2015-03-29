<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <jsp:useBean id="list3" class="com.internetadvert.edu.Pojo.ProductList" scope="session" />
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" 	href="resources/css/bootstrap.css">
<title>Payment</title>
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
<form action="paymentRedirect.htm" method="post" class="form-horizontal">
<fieldset>
<!-- Form Name -->
<legend>Select Invoice to Make Payment</legend>

<div class="control-group">
  <label class="control-label" for="occupation"></label>
  <div class="controls">
    <table border="1">
    	<tr>
    	<td>Inovice</td>
    	<td>Amount</td>
    	<td>Select to make Payment</td>
			<c:forEach var="i" items="${list3.invoiceList}">
				<tr>
				
				<td>${i.invoiceId}</td>				
				
				
				<td>${i.amount}</td>			
				
				
				<td><input type="checkbox" name="pay" value="${i.invoiceId}" >Pay</td>
				
			</c:forEach>
			</tr>
		</table>
  </div>
</div>

<!-- Button -->
<div class="control-group">
  <label class="control-label" for=""></label>
  <div class="controls">
    <button id="" name="" class="btn btn-primary">Make Payment(s)</button>
  </div>
</div>


</fieldset>
</form>
</body>
</html>