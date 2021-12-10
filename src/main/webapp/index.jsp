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
				Bienvenido,
				<c:out value="${user.nombre}"></c:out>
				!
			</h1>
			<h4>

				Tiempo:
				<c:out value="${user.getTiempo()}"></c:out>
				Dinero:
				<c:out value="${user.getDinero()}"></c:out>
				<br>
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
					<!-- 					<th>deshabilitado?</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ofertas}" var="oferta">
					<tr>

						<c:if test="${!oferta.estaDeshabilitado()}">


							<td><strong><c:out value="${oferta.nombre}"></c:out></strong>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
									Cras pretium eros urna. Sed quis erat congue, bibendum tortor
									malesuada, iaculis diam. Ut ut imperdiet sapien.</p></td>


							<%-- 							<td><c:out value="${oferta.costo}"></c:out></td> --%>

							<td><fmt:formatNumber type="number" maxFractionDigits="3"
									value="${oferta.costo}" /></td>
							<td><c:out value="${oferta.tiempo}"></c:out></td>
							<td><c:out value="${oferta.cupo}"></c:out></td>
							<td><c:if test="${user.isAdmin()}">

									<c:choose>
										<c:when test="${oferta.esPromocion()}">

											<a href="editPromo.do?id=${oferta.idPromo}"
												class="btn btn-warning" role="button"><i
												class="bi bi-pencil-fill"></i></a>
											<a href="deletePromo.do?id=${oferta.idPromo}"
												class="btn btn-danger rounded" role="button"><i
												class="bi bi-x-circle-fill"></i></a>
										</c:when>
										<c:otherwise>
											<a href="edit.do?id=${oferta.idAtraccion}"
												class="btn btn-warning" role="button"><i
												class="bi bi-pencil-fill"></i></a>
											<a href="delete.do?id=${oferta.idAtraccion}"
												class="btn btn-danger rounded" role="button"><i
												class="bi bi-x-circle-fill"></i></a>
										</c:otherwise>
									</c:choose>

								</c:if> <c:choose>

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
							<%-- 							<td><c:out value="${oferta.estaDeshabilitado()}"></c:out></td> --%>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<c:if test="${user.isAdmin()}">
			<h1>Estas son las atracciones Deshabilitadas de Parque de la
				Costa</h1>
			<table class="table table-stripped table-hover">
				<thead>
					<tr>
						<th>Atraccion</th>
						<th>Costo</th>
						<th>Duracion</th>
						<th>Cupo</th>
						<th>Acciones</th>
						<th>deshabilitado?</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ofertas}" var="oferta">
						<tr>
							<c:if test="${oferta.estaDeshabilitado()}">
								<td><strong><c:out value="${oferta.nombre}"></c:out></strong>
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
										Cras pretium eros urna. Sed quis erat congue, bibendum tortor
										malesuada, iaculis diam. Ut ut imperdiet sapien.</p></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="3"
										value="${oferta.costo}" /></td>
								<td><c:out value="${oferta.tiempo}"></c:out></td>
								<td><c:out value="${oferta.cupo}"></c:out></td>
								<td><c:if test="${user.isAdmin()}">

										<c:choose>
											<c:when test="${oferta.esPromocion()}">

												<a href="editPromo.do?id=${oferta.idPromo}"
													class="btn btn-warning" role="button"><i
													class="bi bi-pencil-square"></i></a>
												<a href="habilitePromo.do?id=${oferta.idPromo}"
													class="btn btn-success rounded" role="button"><i
													class="bi bi-check-circle-fill"></i></a>
											</c:when>
											<c:otherwise>
												<a href="edit.do?id=${oferta.idAtraccion}"
													class="btn btn-warning" role="button"><i
													class="bi bi-pencil-fill"></i></a>
												<a href="habilite.do?id=${oferta.idAtraccion}"
													class="btn btn-success rounded" role="button"><i
													class="bi bi-check-circle-fill"></i></a>
											</c:otherwise>
										</c:choose>

									</c:if>
								<td><c:out value="${oferta.estaDeshabilitado()}"></c:out></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</c:if>
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