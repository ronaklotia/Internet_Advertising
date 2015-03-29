<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <jsp:useBean id="list" class="com.internetadvert.edu.Pojo.ProductList" scope="request" />
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" 	href="resources/css/bootstrap.css">
<title>Insert title here</title>
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
<form:form action="paymentSucess.htm" method="post" class="form-horizontal">
<fieldset>
<!-- Form Name -->
<legend>Make Payment</legend>


<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Amount</label>
  <div class="controls">
  <label>${list.totalAmount}</label>
  </div>
</div>

<div class="control-group">
  <label class="control-label" for="textinput">Type of Card</label>
  <div class="controls">
 <input type="radio" name="typeofcard" value="Credit" required="">Credit
 <input type="radio" name="typeofcard" value="Debit" reuired="">Debit
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Card Holder Name</label>
  <div class="controls">
    <input id="textinput" name="name" type="text" placeholder="Card Holder Name" class="input-xlarge" required=""/>
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Credit Card No</label>
  <div class="controls">
    <input  id="textinput" name="ccno" type="number"  maxlength="16" class="input-xlarge" required=""/>
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">CVV No</label>
  <div class="controls">
    <input id="textinput" name="cvvno" type="number" maxlength="3" class="input-xlarge" required=""/>
    
  </div>
</div>


<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Expire Date</label>
  <div class="controls">
    <input id="textinput" name="mm" type="number" maxlength=2 placeholder="" class="input-small" required=""/>
    
     <input id="textinput" name="yyyy" type="number" maxlength=4 placeholder="" class="input-small" required=""/>
    <p class="help-block">MM &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;YYYY</p>
  </div>
</div>

<!-- Button -->
<div class="control-group">
  <label class="control-label" for=""></label>
  <div class="controls">
    <button id="" name="" class="btn btn-primary">Payment</button>
  </div>
</div>

</fieldset>
</form:form>
</body>
</html>