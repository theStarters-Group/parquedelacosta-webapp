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
		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="create.do" class="btn btn-primary btn-admin" role="button">
					<i class="bi bi-plus-lg"></i> Crear Atracciones
				</a>
				<!-- 				<a href="" class="btn btn-primary btn-admin" role="button"> <i -->
				<!-- 					class="bi bi-plus-lg"></i> Modificar Promociones -->
				<!-- 				</a> -->
			</div>
		</c:if>
		<div class="container">
			<h1>
				¡Bienvenido,
				<c:out value="${user.nombre}"></c:out>
				!
			</h1>
			<h4>
				Tu contraseña es:
				<c:out value="${user.password}"></c:out>
				Tiempo:
				<c:out value="${user.getTiempo()}"></c:out>
				Dinero:
				<c:out value="${user.getDinero()}"></c:out>
				<br>
				<c:out value="${user}"></c:out>
			</h4>
		</div>



		<h1>Estas son las atracciones de Parque de la Costa</h1>



		<p>


			<c:if test="${flash != null}">
				<div class="alert alert-danger">
					<p>
						<c:out value="${flash}" />
						<c:if test="${errors != null}">
							<ul>
								<c:forEach items="${errors}" var="entry">
									<li><c:out value="${entry.getValue()}"></c:out></li>
								</c:forEach>
							</ul>
						</c:if>
					</p>
				</div>
			</c:if>
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Atraccion</th>
					<th>Costo</th>
					<th>Duracion</th>
					<th>Cupo</th>
					<th>Acciones</th>
<!-- 					<th>id</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ofertas}" var="oferta">
					<tr>
						<td><strong><c:out value="${oferta.nombre}"></c:out></strong>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Cras pretium eros urna. Sed quis erat congue, bibendum tortor
								malesuada, iaculis diam. Ut ut imperdiet sapien.</p></td>
						<td><c:out value="${oferta.costo}"></c:out></td>
						<td><c:out value="${oferta.tiempo}"></c:out></td>
						<td><c:out value="${oferta.cupo}"></c:out></td>
						<td><c:if test="${user.isAdmin()}">

								<c:choose>
									<c:when test="${oferta.esPromocion()}">

										<a href="editPromo.do?id=${oferta.idPromo}"
											class="btn btn-light rounded-0" role="button"><i
											class="bi bi-pencil-fill"></i></a>
										<a href="deletePromo.do?id=${oferta.idPromo}"
											class="btn btn-danger rounded" role="button"><i
											class="bi bi-x-circle-fill"></i></a>
									</c:when>
									<c:otherwise>
										<a href="edit.do?id=${oferta.idAtraccion}"
											class="btn btn-light rounded-0" role="button"><i
											class="bi bi-pencil-fill"></i></a>
										<a href="delete.do?id=${oferta.idAtraccion}"
											class="btn btn-danger rounded" role="button"><i
											class="bi bi-x-circle-fill"></i></a>
									</c:otherwise>
								</c:choose>

							</c:if> <c:choose>

								<c:when test="${user.puedeComprar(oferta) && oferta.canHost(1)}">
									<a href="buy.do?id=${oferta.idAtraccion}"
										class="btn btn-success rounded" role="button">Comprar</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-secondary rounded disabled"
										role="button">No se puede comprar</a>
								</c:otherwise>
							</c:choose></td>
						<td><c:out value="${attraction.idAtraccion}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>


		<!-- Tabla de atracciones funcionando -->




		<!-- 		<table class="table table-stripped table-hover"> -->
		<!-- 			<thead> -->
		<!-- 				<tr> -->
		<!-- 					<th>Atraccion</th> -->
		<!-- 					<th>Costo</th> -->
		<!-- 					<th>Duracion</th> -->
		<!-- 					<th>Cupo</th> -->

		<!-- 					<th>Acciones</th> -->
		<!-- 					<th>id</th> -->
		<!-- 					<th>tipo</th> -->
		<!-- 				</tr> -->
		<!-- 			</thead> -->
		<!-- 			<tbody> -->
		<%-- 				<c:forEach items="${attractions}" var="attraction"> --%>
		<!-- 					<tr> -->
		<%-- 						<td><strong><c:out value="${attraction.nombre}"></c:out></strong> --%>
		<!-- 							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. -->
		<!-- 								Cras pretium eros urna. Sed quis erat congue, bibendum tortor -->
		<!-- 								malesuada, iaculis diam. Ut ut imperdiet sapien.</p></td> -->
		<%-- 						<td><c:out value="${attraction.costo}"></c:out></td> --%>
		<%-- 						<td><c:out value="${attraction.tiempo}"></c:out></td> --%>
		<%-- 						<td><c:out value="${attraction.cupo}"></c:out></td> --%>



		<%-- 						<td><c:if test="${user.isAdmin()}"> --%>
		<%-- 								<a href="attractions/edit.do?id=${attraction.idAtraccion}" --%>
		<!-- 									class="btn btn-light rounded-0" role="button"><i -->
		<!-- 									class="bi bi-pencil-fill"></i></a> -->
		<%-- 								<a href="attractions/delete.do?id=${attraction.idAtraccion}" --%>
		<!-- 									class="btn btn-danger rounded" role="button"><i -->
		<!-- 									class="bi bi-x-circle-fill"></i></a> -->
		<%-- 							</c:if> <c:choose> --%>

		<%-- 								<c:when --%>
		<%-- 									test="${user.puedeComprar(attraction) && attraction.canHost(1)}"> --%>
		<%-- 									<a href="attractions/buy.do?id=${attraction.idAtraccion}" --%>
		<!-- 										class="btn btn-success rounded" role="button">Comprar</a> -->
		<%-- 								</c:when> --%>
		<%-- 								<c:otherwise> --%>
		<!-- 									<a href="#" class="btn btn-secondary rounded disabled" -->
		<!-- 										role="button">No se puede comprar</a> -->
		<%-- 								</c:otherwise> --%>
		<%-- 							</c:choose></td> --%>
		<%-- 						<td><c:out value="${attraction.idAtraccion}"></c:out></td> --%>
		<%-- 						<td><c:out value="${attraction.getTipoAtraccion()}"></c:out></td> --%>
		<%-- 				</c:forEach> --%>
		<!-- 			</tbody> -->
		<!-- 		</table> -->







		<!--Carousel por armar -->
		<!-- 				<div id="carouselExample" class="carousel-slide" data-ride="carousel"> -->
		<!-- 					<ol class="carousel-indicators"> -->
		<!-- 						<li data-target="#carouselExample" data-slide-to="0" class="active"></li> -->
		<!-- 						<li data-target="#carouselExample" data-slide-to="1"></li> -->
		<!-- 					</ol> -->
		<!-- 					<div class="carousel-inner"> -->
		<!-- 						<div class="carousel-item active"> -->
		<!-- 							<img src="img/boomerang.jpg" alt="..."> -->
		<!-- 						</div> -->
		<!-- 						<div class="carousel-item"> -->
		<!-- 							<img src="img/vertigo_extremo.jpg" alt="..."> -->
		<!-- 						</div> -->
		<!-- 					</div> -->
		<!-- 					<a href="#carouselExample" class="carousel-control-prev" -->
		<!-- 						role="button" data-slide="prev"> <span -->
		<!-- 						class="carousel-control-prev-icon" aria-hidden="true"></span> <span -->
		<!-- 						class="visually-hidden">Anterior</span> -->
		<!-- 					</a> <a href="#carouselExample" class="carousel-control-next" -->
		<!-- 						role="button" data-slide="next"> <span -->
		<!-- 						class="carousel-control-next-icon" aria-hidden="true"> </span> <span -->
		<!-- 						class="visually-hidden">Siguiente</span></a> -->
		<!-- 				</div> -->



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