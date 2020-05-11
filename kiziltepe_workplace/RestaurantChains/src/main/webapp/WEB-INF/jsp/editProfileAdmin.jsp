<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="chains.restaurant.application.model.Restaurant"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>EDIT PROFILE</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">


</head>
<body class="homebg">
	<div class="container">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<h3 class="welcomelogout">${pageContext.request.userPrincipal.name}
				| <a href="/welcome">Home</a> | <a
					href="/shoppingcart?name=${pageContext.request.userPrincipal.name}">Shopping
					Cart</a> |
				<c:if test="${pageContext.request.isUserInRole('ADMIN')}">
					<a href="${contextPath}/h2-console">CONSOLE</a> |
				</c:if>
				<a onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h3>
		</c:if>
	</div>

	<div class="tableDiv">
		<h2>
			Account Details | <a
				href="/admin/view_user?id=${user.id}">Profile</a>
		</h2>
	</div>

	<div class="tableDiv">
		<h3>Name: ${user.name}</h3>
		<form action="/admin/edit_profile_name">
			<input type="hidden" name="id"
				value="${user.id}"> <input
				type="text" name="newName" placeholder="New Name"> <input
				type="submit" value="Save Name">
		</form>
		<h3>Address: ${user.address}</h3>
		<form action="/admin/edit_profile_address">
			<input type="hidden" name="name"
				value="${pageContext.request.userPrincipal.name}"> <input
				type="text" name="newName" placeholder="New Address"> <input
				type="submit" value="Save Address">
		</form>
		<h3>Credit Card: ${user.creditCard}</h3>
		<form action="/admin/edit_profile_creditcard">
			<input type="hidden" name="name"
				value="${pageContext.request.userPrincipal.name}"> <input
				type="text" name="newName" placeholder="0000 0000 0000 0000"> <input
				type="submit" value="Save Card">
		</form>
	</div>

	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/divshow.js"></script>
</body>
</html>
