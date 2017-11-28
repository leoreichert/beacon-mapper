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

		<h1>All Beacons</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>uid</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="beacon" items="${beacons}">
				<tr>
					<td>
						${beacon.id}
					</td>
					<td>${beacon.uid}</td>
					<td>
						<spring:url value="/beacons/${beacon.id}" var="beaconUrl" />
						<spring:url value="/beacons/${beacon.id}/delete" var="deleteUrl" /> 
						<spring:url value="/beacons/${beacon.id}/update" var="updateUrl" />
						<spring:url value="${beacon.urlid}" var="editUrl" />
						<spring:url value="${beacon.urlid}/vis/" var="viewUrl" />

						<button class="btn btn-info" onclick="location.href='${beaconUrl}'">Query</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${editUrl}')">Editar Página</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${viewUrl}')">Visualizar Página</button></td>
				</tr>
			</c:forEach>
		</table>
		
	</div>
	
	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>