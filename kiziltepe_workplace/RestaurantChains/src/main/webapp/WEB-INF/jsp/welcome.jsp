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

<title>WELCOME</title>

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
				| <a href="/profile?name=${pageContext.request.userPrincipal.name}">My
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

	<div>
		<table>
			<tr>
				<td>
					<h2>Restaurants</h2>
				</td>
			</tr>
			<tr>
				<td><c:forEach var="restaurant" items="${restaurantList}">
						<table>
							<tr>
								<td><form action="/view_restaurant">
										<table>
											<tr>
												<td><input type="hidden" name="name"
													value="${restaurant.name}"></td>
												<td><input type="submit" value="${restaurant.name}"></td>
											</tr>
										</table>
									</form></td>
							</tr>
						</table>
					</c:forEach></td>
			</tr>
		</table>
	</div>
	<c:if test="${pageContext.request.isUserInRole('OWNER')}">

		<div>
			<c:choose>
				<c:when test="${pageContext.request.isUserInRole('ADMIN')}">
					<table>
						<tr>
							<td>
								<h2>All Restaurants</h2>
							</td>
						</tr>
						<tr>
							<td>
								<table>
									<tr>
										<td>
											<form action="/admin/view_restaurants">
												<input type="submit" value="View Restaurants">
											</form>
										</td>
									</tr>
								</table>
					</table>
				</c:when>
				<c:otherwise>
					<table>
						<tr>
							<td>
								<h2>My Restaurant</h2>
							</td>
						</tr>
						<tr>
							<td>
								<table>
									<tr>
										<td>
											<form action="/owner/view_restaurant">
												<input type="hidden" name="name"
													value="${pageContext.request.userPrincipal.name}">
												<input type="submit" value="My Restaurant">
											</form>
										</td>
									</tr>
								</table>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/divshow.js"></script>
</body>
</html>
