<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="chains.restaurant.application.model.Restaurant"%>
<%@ page import="chains.restaurant.application.model.Item"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

<title>RESTAURANT</title>

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
	<div class="myRestaurant">
		<h3>Restaurant Details</h3>
		<h3>Name = ${restaurant.name}, Address = ${restaurant.address}</h3>
	</div>
	<div class="tableDiv">
		<h2>Menu</h2>
		<table>
			<tr>
				<td><c:forEach var="item" items="${itemListRestaurant}">
						<table>
							<tr>
								<td><form action="/view_restaurant/add_item">
										<table>
											<tr>
												<td><input type="hidden" name="name"
													value="${restaurant.name}"></td>
												<td><input type="hidden" name="user"
													value="${pageContext.request.userPrincipal.name}"></td>
												<td><input type="hidden" name="id" value="${item.id}"></td>
												<td><h3>$${item.price} - ${item.name},
														${item.description}</h3></td>
												<td><h4><input type="submit" value="Add Item"></h4></td>
											</tr>
										</table>
									</form></td>
							</tr>
						</table>
					</c:forEach></td>
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
