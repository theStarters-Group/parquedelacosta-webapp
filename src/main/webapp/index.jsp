<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
</head>
<body>
	<header>
		<jsp:include page="partials/nav.jsp"></jsp:include>
	</header>
	<!-- - -->
	<section>
		<div class="container">
			<h1>
				¡Bienvenido,
				<c:out value="${user.nombre}"></c:out>
				!
				<c:out value="${user.password}"></c:out>
			</h1>
		</div>

	</section>






	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<!-- Popper JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>