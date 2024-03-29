<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="description" content="">
<meta name="author" content="">

<title>REGISTER OWNER</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>
	<div class="backgroundlogin">
		<div class="container">
			<form:form method="POST" modelAttribute="userForm"
				class="form-signin">
				<h2 class="form-signin-heading">Create your account</h2>
				<spring:bind path="username">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="username" class="form-control"
							placeholder="Username" autofocus="true"></form:input>
						<form:errors path="username"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="password">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="password" path="password" class="form-control"
							placeholder="Password"></form:input>
						<form:errors path="password"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="passwordConfirm">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="password" path="passwordConfirm"
							class="form-control" placeholder="Confirm your password"></form:input>
						<form:errors path="passwordConfirm"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="name">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="name" class="form-control"
							placeholder="Name"></form:input>
					</div>
				</spring:bind>

				<spring:bind path="address">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="address" class="form-control"
							placeholder="Address"></form:input>
					</div>
				</spring:bind>

				<spring:bind path="creditCard">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="creditCard" class="form-control"
							placeholder="Credit Card"></form:input>
					</div>
				</spring:bind>

				<spring:bind path="isOwner">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:checkbox id="isOwner" path="isOwner"></form:checkbox>
						Are you an owner?
					</div>
				</spring:bind>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
			</form:form>

		</div>
	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/divshow.js"></script>
</body>
</html>
