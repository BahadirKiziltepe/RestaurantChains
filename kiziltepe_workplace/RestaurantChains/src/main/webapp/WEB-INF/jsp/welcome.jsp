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

<title>Create an account</title>

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

			<h4 class="welcomelogout">
				Welcome ${pageContext.request.userPrincipal.name} | <a
					onclick="document.forms['logoutForm'].submit()">Logout</a>
					<a href="/edit_profile"></a>
			</h4>

		</c:if>

	</div>

	<div class="role_admin">
		<a href="${contextPath}/h2-console">CONSOLE</a>
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
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/divshow.js"></script>
</body>
</html>
