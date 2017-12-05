<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>
	<div class="container">
		<div class="mapa2">
	    	<canvas id="canvas" width="800" height="800"></canvas>
		</div>
	</div>

	<script>
		function httpGet(theUrl) {
		    var xmlHttp = new XMLHttpRequest();
		    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
		    xmlHttp.send( null );
		    return xmlHttp.responseText;
		}
		
		window.requestAnimationFrame = window.requestAnimationFrame || window.mozRequestAnimationFrame ||
		                               window.webkitRequestAnimationFrame || window.msRequestAnimationFrame;
		
		var response = JSON.parse(httpGet('https://beacon-mapper.herokuapp.com/GetAllAccessBeacon'));
		
		var data = [];
		for (i = 0; i < response.access.length; i++) { 
			data.push([response.access[i].posX, response.access[i].posY, response.access[i].value]);
		}
		
		var heat = simpleheat('canvas').data(data).max(response.max * 1.2),
		    frame;
		
		function draw() {
		    heat.draw();
		    frame = null;
		}
		
		draw();
	</script>

	
	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>