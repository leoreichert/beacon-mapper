<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Beacon Mapper</title>

<link rel="shortcut icon" type="image/png" href="resources/beacon.png"/>

<link href="../resources/css/bootstrap.min.css" rel="stylesheet" />

<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/hello.js"></script>
<script src="../resources/js/simpleheat.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/mapaCalor" var="urlMapaCalor" />
<spring:url value="/logout" var="urlLogout" />

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
				<li class="active"><a href="${urlLogout}">Sair</a></li>
			</ul>
		</div>
	</div>
</nav>