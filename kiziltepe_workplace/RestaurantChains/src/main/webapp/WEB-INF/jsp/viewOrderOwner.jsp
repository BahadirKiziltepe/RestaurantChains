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

<title>SHOPPING CART</title>

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
					href="/profile?name=${pageContext.request.userPrincipal.name}">My
					Account</a> | <a
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
		<h2>Order ID = ${id}</h2>
		<c:forEach var="item" items="${orderRestaurant}">
			<table>
				<tr>
					<td>
						<table>
							<tr>
								<td><h3>$${item.price} - ${item.name},
										${item.description}</h3></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</c:forEach>
		<table class="myRestaurant">
			<tr>
				<td>
					<h2>total = $${total}</h2>
				</td>
			</tr>
		</table>
	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/divshow.js"></script>
</body>
</html>
