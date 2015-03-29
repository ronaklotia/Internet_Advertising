<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="web" class="com.internetadvert.edu.Pojo.Website" scope="request" />
<html>
<head>
<meta charset=”utf-8”>
<title>Insert title here</title>
<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>

<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css">
</head>
<body>
	<div class="container login">
		<div class="row ">
			<div class="center span9 well">


				<form:form commandName="user" method="post" action="signin.htm" accept-charset="UTF-8" class="form-signin">
					<h2 class="form-signin-heading">Sign in</h2>
					 <input  type="text"	id="username" class="span4" name="username" placeholder="Username" required="" />
					<br> Password: <input path="password" type="password" id="password"	class="span4" name="password" placeholder="Password" required=""/>
					 <label	class="checkbox"> 
					 <input type="checkbox" name="remember"	value="1" /> Remember Me
					 </label>
					<button type="submit" name="submit"	class="btn btn-primary btn-block">Sign in</button>
					<input type="hidden" name="web" value="${web.name}">
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>