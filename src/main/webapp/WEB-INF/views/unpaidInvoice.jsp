<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="status" class="com.internetadvert.edu.Pojo.ProductList" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" 	href="resources/css/bootstrap.css">
<title>Unpaid Invoice</title>
</head>
<body>
	<div class="container">
<div class="navbar navbar-inverse">
              <div class="navbar-inner">
                <div class="container">
                  <ul class="nav nav-pills">
                                   
                    <li><a href="viewInvoice.htm" >View Invoice</a></li>                    
                    
                    <li><a href="logout.htm" >Logout</a></li>
                  </ul>
                </div>
              </div>
            </div>            
</div>
	
	<table border="1">
		<tr>
			<td>Inovice ID</td>
			<td>Amount</td>
			<td>Status</td></tr>
			<c:forEach var="i" items="${status.invoiceList}">
				<tr><td>${i.invoiceId}</td>
				<td>${i.amount }</td>
				<td>${i.status}</td>	</tr>			
			</c:forEach>
		
	</table>
</body>
</html>