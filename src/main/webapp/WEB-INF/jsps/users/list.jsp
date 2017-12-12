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

		<h1>Usuários</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Login</th>
					<th>Estado</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.username}</td>
					<td>${user.estado}</td>
					<td>
						<spring:url value="/users/${user.id}" var="userUrl" />
						<spring:url value="/users/${user.id}/delete" var="deleteUrl" /> 

						<button class="btn btn-deafault" onclick="location.href='${userUrl}'">Visualizar</button>
						<button class="btn btn-info" onclick="this.disabled=true;post('${deleteUrl}')">Deletar</button></td>
				</tr>
			</c:forEach>
		</table>
		<spring:url value="/users/add" var="urlAddUser" />
		<button class="btn btn-success btn-lg" onclick="location.href='${urlAddUser}'">Novo Usuário</button>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>