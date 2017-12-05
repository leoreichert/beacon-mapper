<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>Beacons mapeados</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>UID</th>
					<th>URLID</th>
					<th>Ações</th>
				</tr>
			</thead>

			<c:forEach var="beacon" items="${beacons}">
				<tr>
					<td>
						${beacon.id}
					</td>
					<td>${beacon.uid}</td>
					<td>${beacon.urlid}</td>
					<td>
						<spring:url value="/beacons/${beacon.id}" var="beaconUrl" />
						<spring:url value="/beacons/${beacon.id}/delete" var="deleteUrl" /> 
						<spring:url value="/beacons/${beacon.id}/update" var="updateUrl" />
						<spring:url value="${beacon.urlid}" var="editUrl" />
						<spring:url value="${beacon.urlid}/vis/" var="viewUrl" />

						<button class="btn btn-default" onclick="location.href='${beaconUrl}'">Query</button>
						<button class="btn btn-info" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-primary" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
						<button class="btn btn-warning" onclick="location.href='${editUrl}'">Editar Página</button>
						<button class="btn btn-danger" onclick="location.href='${viewUrl}'">Visualizar Página</button></td>
				</tr>
			</c:forEach>
		</table>
		<spring:url value="/beacons/add" var="urlAddBeacon" />
		<button class="btn btn-success btn-lg" onclick="location.href='${urlAddBeacon}'">Novo Beacon</button>		
	</div>
	
	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>