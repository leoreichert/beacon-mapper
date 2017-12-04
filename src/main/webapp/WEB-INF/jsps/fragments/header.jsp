<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Beacon MVC Form</title>

<spring:url value="resources/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />

<script src="resources/js/jquery.min.js"></script>

<spring:url value="resources/js/bootstrap.min.js"	var="bootstrapJs" />
<script src="${bootstrapJs}"></script>

<link rel="shortcut icon" type="image/png" href="resources/beacon.png"/>

</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/beacons/add" var="urlAddBeacon" />
<spring:url value="/mapaCalor" var="urlMapaCalor" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a class="navbar-brand" href="${urlHome}">Página Inicial</a></li>
				<li class="active"><a class="navbar-brand" href="${urlMapaCalor}">Mapa de calor</a></li>
			</ul>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlAddBeacon}">Novo Beacon</a></li>
			</ul>
		</div>
	</div>
</nav>