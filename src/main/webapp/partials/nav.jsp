<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar">
	<div class="navbar_elements">
		<img src="img\logo_inicio_azul.png">
	</div>
	<div class="navbar_centro">
		<div>
			<a href="index.jsp">Inicio</a>
		</div>
		<div class="dropdown-container">
			<!-- 				<a href="atracciones.HTML">Atracciones</a> -->
			<span>Atracciones</span>
			<ul>
				<li><a href="extremo.jsp">Paseo Extremo</a></li>
				<li><a href="familiar.jsp">Paseo Familiar</a></li>
				<li><a href="plano.jsp">Paseo Plano</a></li>
				<li><a href="oscuro.jsp">Paseo Oscuro</a></li>

			</ul>
		</div>
		<div class="dropdown-container">
			<span>Promociones</span>
			<ul>
				<li><a href="packextremo.jsp">Packs Extremos</a></li>
				<li><a href="packfamiliar.jsp">Packs Familiares</a></li>
				<li><a href="packplano.jsp">Packs Planos</a></li>
				<li><a href="packoscuro.jsp">Packs Oscuros</a></li>

			</ul>
		</div>
		<div class="dropdown-container">

			<span><c:out value="${user.nombre}"></c:out></span>
			<ul>
				<li><a href="comprar.jsp">Comprar</a></li>
				<li><a href="itinerario.jsp">Mi Itinerario</a></li>
				<li><a href="perfil.jsp">Mi Perfil</a></li>
				<li><a href="logout">Salir</a></li>
			</ul>
		</div>
	</div>
	<!-- 	<div class="navbar_elements"> -->
	<!-- 		<a href="login.jsp"><button type="button" -->
	<!-- 				class="btn btn-outline-primary">LOGIN</button></a> -->
	<!-- 	</div> -->
</nav>