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
					Account</a> |
				<c:if test="${pageContext.request.isUserInRole('ADMIN')}">
					<a href="${contextPath}/h2-console">CONSOLE</a> |
				</c:if>
				<a onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h3>
		</c:if>
	</div>

	<h2>
		Account Details
		<c:if test="${pageContext.request.isUserInRole('OWNER')}">
			<a
				href="${contextPath}/owner/view_restaurant?name=${pageContext.request.userPrincipal.name}">
				| Restaurant</a>
		</c:if>
	</h2>
	<h3>Owner of the restaurant,
		${pageContext.request.userPrincipal.name}</h3>
	<table>
		<tr>
			<td><h3>Name = ${restaurant.name}, ID = ${restaurant.id},
					Address = ${restaurant.address} |</h3></td>
		</tr>
	</table>
	<table>
		<tr>
			<td><form action="/owner/edit_restaurant_name">
					<table>
						<tr>
							<td><input type="hidden" name="name"
								value="${restaurant.name}"></td>
							<td><input type="text" name="newName"
								placeholder="New Restaurant Name"></td>
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
							<td><input type="text" name="newName"
								placeholder="New Restaurant Adress"></td>
							<td><input type="submit" value="set new name"></td>
						</tr>
					</table>
				</form></td>
		</tr>
		<tr>
			<td>
				<h2>Menu</h2>
			</td>
		</tr>
		<tr>
			<td><c:forEach var="item" items="${itemList}">
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td>
											<h4>$${item.price} - ${item.name}, ${item.description}</h4>
										<td>
											<form action="/owner/edit_restaurant_edit_item_name">
												<input type="hidden" name="name" value="${restaurant.name}">
												<input type="hidden" name="id" value="${item.id}"> <input
													type="text" name="newName" placeholder="New Item Name">
												<input type="submit" value="Change Name">
											</form>
										</td>
										<td>
											<form action="/owner/edit_restaurant_edit_item_description">
												<input type="hidden" name="name" value="${restaurant.name}">
												<input type="hidden" name="id" value="${item.id}"> <input
													type="text" name="newName"
													placeholder="New Item Description"> <input
													type="submit" value="Change Name">
											</form>
										</td>
										<td>
											<form action="/owner/edit_restaurant_edit_item_price">
												<input type="hidden" name="name" value="${restaurant.name}">
												<input type="hidden" name="id" value="${item.id}"> <input
													type="number" step="0.01" name="newPrice"
													placeholder="New Item Price"> <input type="submit"
													value="Change Name">
											</form>
										</td>
										<td>
											<form action="/owner/edit_restaurant_remove_item">
												<input type="hidden" name="restaurantName"
													value="${restaurant.name}"> <input type="hidden"
													name="id" value="${item.id}"> <input type="submit"
													value="Delete Item">
											</form>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</c:forEach>
				<form action="/owner/edit_restaurant_add_item">
					<input type="hidden" name="restaurantName"
						value="${restaurant.name}"> <input type="text" name="name"
						placeholder="Name"> <input type="text" name="description"
						placeholder="Description"> <input type="number"
						step="0.01" name="price" placeholder="Price"> <input
						type="submit" value="Add Item">
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
