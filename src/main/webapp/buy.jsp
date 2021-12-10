<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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


		<!--Carousel por armar -->
		<div class="container_carrousel">
			<div class="body_carrousel">
				<div id="carouselExampleControls" class="carousel slide"
					data-bs-ride="carousel">
					<div class="carousel-inner">

						<div class="carousel-item active">
							<img src="https://i.ibb.co/PxWB015/full-mapa.jpg"
								class="d-block w-100 h-50" alt="..">
						</div>

						<c:forEach items="${ofertas}" var="oferta">
							<c:if
								test="${user.puedeComprar(oferta) && oferta.canHost(1) && !oferta.estaDeshabilitado() && oferta.imagen!=null}">
								<div class="carousel-item">
									<img src="${oferta.getImagen()}" class="d-block w-100 h-50"
										alt="..">
								</div>
							</c:if>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleControls" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleControls" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
		</div>
		<h2>Promociones y atracciones disponibles para vos.</h2>
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Atraccion</th>
					<th>Costo</th>
					<th>Duracion</th>
					<th>Cupo</th>
					<th>Acciones</th>
					<!-- 					<th>deshabilitado?</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ofertas}" var="oferta">
					<tr>

						<c:if
							test="${!oferta.estaDeshabilitado() && user.puedeComprar(oferta)}">


							<td><strong><c:out value="${oferta.nombre}"></c:out></strong>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
									Cras pretium eros urna. Sed quis erat congue, bibendum tortor
									malesuada, iaculis diam. Ut ut imperdiet sapien.</p></td>
							<td><fmt:formatNumber type="number" maxFractionDigits="3"
									value="${oferta.costo}" /></td>
							<td><c:out value="${oferta.tiempo}"></c:out></td>
							<td><c:out value="${oferta.cupo}"></c:out></td>
							<td><c:choose>
									<c:when
										test="${user.puedeComprar(oferta) && oferta.canHost(1)}">
										<a href="buy.do?id=${oferta.idAtraccion}"
											class="btn btn-success rounded" role="button">Comprar</a>
									</c:when>
									<c:otherwise>
										<a href="#" class="btn btn-secondary rounded disabled"
											role="button">No se puede comprar</a>
									</c:otherwise>
								</c:choose></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	<jsp:include page="partials/footer.jsp"></jsp:include>






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