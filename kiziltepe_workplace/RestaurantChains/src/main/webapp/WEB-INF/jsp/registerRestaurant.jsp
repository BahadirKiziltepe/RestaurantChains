<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<div class="container">

		<form:form method="POST" modelAttribute="restaurantForm"
			class="form-signin">
			<h2 class="form-signin-heading">Create your account</h2>
			<spring:bind path="name">
				<div class="form-group">
					<form:input type="text" path="name" class="form-control"
						placeholder="Name" autofocus="true"></form:input>
				</div>
			</spring:bind>

			<spring:bind path="address">
				<div class="form-group">
					<form:input type="text" path="address" class="form-control"
						placeholder="Address"></form:input>
				</div>
			</spring:bind>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>