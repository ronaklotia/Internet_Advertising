<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta charset=utf-8>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"	href="resources/css/bootstrap.css">
<style>
            .errows{
                color: red;
                font-style: italic;
                
            }
        </style>
</head>
<body>
	<div class="container">

		<div class="row">


			<div class="span8">

				<form:form commandName="details" class="form-horizontal" id="registerHere" method='post'
					action='login.htm'>
					<fieldset>
						<legend>Registration</legend>
						<div class="control-group">
							<label class="control-label" for="input01">Name</label>
							<div class="controls">
								<input  type="text" class="input-xlarge" id="user_name"
									name="user_name" rel="popover" required=""/>									>

							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="input01">Email</label>
							<div class="controls">
								<form:input path="email" type="email" class="input-xlarge" id="user_email"
									name="user_email" rel="popover" required=""/>		
									 <span class="errows">
									<form:errors path="email"/>		
									</span>					
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="input01">Username</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="username"
									name="username" rel="popover" required="">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="input01">Phone No</label>
							<div class="controls">
								<form:input path="phoneNo" type="number" class="input-xlarge" id="phoneNo"
									name="phoneNo" rel="popover" required=""/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="input01">Occupation</label>
							<div class="controls">
								<form:input path="occupation" type="text" class="input-xlarge" id="occupation"
									name="occupation" rel="popover" required=""/>
									
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="input01">Computer Type</label>
							<div class="controls">
								<form:input path="computerType" type="text" class="input-xlarge" id="type"
									name="type123" rel="popover" required=""/>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="input01">Password</label>
							<div class="controls">
								<input type="password" class="input-xlarge" id="pwd" name="pwd"
									required="">
							</div>
						</div>
						
					


						<div class="control-group">
							<label class="control-label" for="input01">Gender</label>
							<div class="controls">
								<form:select path="gender" name="gender" id="gender" required="">
									<option value="">Gender</option>
									<option value="male">Male</option>
									<option value="female">Female</option>									
								</form:select>
							</div>
						</div>


						<div class="control-group">
							<label class="control-label" for="input01"></label>
							<div class="controls">
								<button type="submit" class="btn btn-success"
									title="first tooltip">Create My Account</button>
							</div>
						</div>
						
						<input type="hidden" name="Role" value="Customer" />


					</fieldset>
				</form:form>
			</div>

		</div>


	</div>
	<!--/row-->
	</div>
	<!--/span-->
	</div>
	<!--/row-->

	<hr>

	<footer> </footer>

	</div>
<!--  
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script src="http://twitter.github.com/bootstrap/assets/js/jquery.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-transition.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-alert.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-modal.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-dropdown.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-scrollspy.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-tab.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-tooltip.js"></script>
	<script
		src="http://twitter.github.com/bootstrap/assets/js/bootstrap-popover.js"></script>
	<script type="text/javascript"
		src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#registerHere input').hover(function() {
				$(this).popover('show')
			});
			$("#registerHere").validate({
				rules : {
					user_name : "required",
					user_email : {
						required : true,
						email : true
					},
					pwd : {
						required : true,
						minlength : 6
					},
					cpwd : {
						required : true,
						equalTo : "#pwd"
					},
					gender : "required"
				},
				messages : {
					user_name : "Enter your first and last name",
					user_email : {
						required : "Enter your email address",
						email : "Enter valid email address"
					},
					pwd : {
						required : "Enter your password",
						minlength : "Password must be minimum 6 characters"
					},
					cpwd : {
						required : "Enter confirm password",
						equalTo : "Password and Confirm Password must match"
					},
					gender : "Select Gender"
				},
				errorClass : "help-inline",
				errorElement : "span",
				highlight : function(element, errorClass, validClass) {
					$(element).parents('.control-group').addClass('error');
				},
				unhighlight : function(element, errorClass, validClass) {
					$(element).parents('.control-group').removeClass('error');
					$(element).parents('.control-group').addClass('success');
				}
			});
		});
	</script>
-->

</body>
</html>