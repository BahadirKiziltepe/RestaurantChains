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

<title>Create an account</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">


</head>
<body>
	<h2>Owner ${pageContext.request.userPrincipal.name}</h2>
	<table>
		<tr>

			<td>Name = ${restaurant.name}, ID = ${restaurant.id}, Address =
				${restaurant.address}</td>

		</tr>
	</table>
	<table>
		<tr>
			<td><form action="/owner/edit_restaurant_name">
					<table>
						<tr>
							<td><input type="hidden" name="name"
								value="${restaurant.name}"></td>
							<td><input type="text" name="newName" placeholder="new name"></td>
							<td><input type="submit" value="set new name"></td>
						</tr>
					</table>
				</form></td>
		</tr>
		<tr>
			<td><form action="/owner/edit_restaurant_address">
					<table>
						<tr>
							<td><input type="hidden" name="name"
								value="${restaurant.name}"></td>
							<td><input type="text" name="newName" placeholder="new name"></td>
							<td><input type="submit" value="set new name"></td>
						</tr>
					</table>
				</form></td>
		</tr>
		<tr>
			<td><c:forEach var="item" items="${itemList}">
					<table>
						<tr>
							<td><form action="/view_restaurant">
									<table>
										<tr>
											<td><input type="hidden" name="name"
												value="${item.name}"></td>
											<td><input type="submit" value="${item.name}"></td>
										</tr>
									</table>
								</form></td>
						</tr>
					</table>
				</c:forEach>
				<form action="/owner/edit_restaurant_add_item">
				<input type="hidden" name="restaurantName" value="${restaurant.name}">
				<input type="text" name="name" placeholder="Name">
				<input type="text" name="description" placeholder="Description">
				<input type="number" name="price" placeholder="Price">
				<input type="submit" value="Add Item">
				</form></td>
		</tr>
	</table>

	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/divshow.js"></script>
</body>
</html>
