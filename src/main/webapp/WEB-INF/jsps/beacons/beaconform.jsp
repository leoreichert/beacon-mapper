<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">
	<script>
		function post(path, params, method) {
			method = method || "post"; 
		
			var form = document.createElement("form");
			form.setAttribute("method", method);
			form.setAttribute("action", path);
		
			for ( var key in params) {
				if (params.hasOwnProperty(key)) {
					var hiddenField = document.createElement("input");
					hiddenField.setAttribute("type", "hidden");
					hiddenField.setAttribute("name", key);
					hiddenField.setAttribute("value", params[key]);
		
					form.appendChild(hiddenField);
				}
			}
		
			document.body.appendChild(form);
			form.submit();
		}
		
		function salvarPosicao(event) {
			document.getElementById('posicaoX').value = event.offsetX;
			document.getElementById('posicaoY').value = event.offsetY;
		}
	</script>

	<c:choose>
		<c:when test="${beaconForm['new']}">
			<h1>Add Beacon</h1>
		</c:when>
		<c:otherwise>
			<h1>Update Beacon</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/beacons" var="beaconActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="beaconForm" action="${beaconActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="uid">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">ID</label>
				<div class="col-sm-10">
					<form:input path="uid" type="text" class="form-control " id="uid" placeholder="uid" />
					<form:errors path="uid" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="urlid">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">URL</label>
				<div class="col-sm-10">
					<form:input path="urlid" class="form-control" id="urlid" placeholder="urlid" />
					<form:errors path="urlid" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="estado">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Estado</label>
				<div class="col-sm-5">
					<form:select path="estado" class="form-control">
						<form:option value="NONE" label="--- Select ---" />
						<form:options items="${estadoList}" />
					</form:select>
					<form:errors path="estado" class="control-label" />
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>
		
		<spring:bind path="posicaoX">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">X</label>
				<div class="col-sm-4">
					<form:input enable='false' path="posicaoX" class="form-control" id="posicaoX" placeholder="posicaoX" />
					<form:errors path="posicaoX" class="control-label" />
				</div>
				
				<label class="col-sm-2 control-label">Y</label>
				<div class="col-sm-4">
					<form:input enable='false' path="posicaoY" class="form-control" id="posicaoY" placeholder="posicaoY" />
					<form:errors path="posicaoY" class="control-label" />
				</div>
				
				<div class="col-sm-5 mapa">
	    			<canvas id="canvas" width="400" height="400" onclick="salvarPosicao(event)"></canvas>
				</div>
			</div>
		</spring:bind>
		
			

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${beaconForm['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>