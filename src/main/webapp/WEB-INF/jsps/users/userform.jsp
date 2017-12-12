<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:choose>
		<c:when test="${userForm['new']}">
			<h1>Adiconar</h1>
		</c:when>
		<c:otherwise>
			<h1>Atualizar</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/users" var="userActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="userForm" action="${userActionUrl}">

		<form:hidden path="id" />
		
		<spring:bind path="username">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Login</label>
				<div class="col-sm-10">
					<form:input path="username" type="text" class="form-control " id="username" placeholder="username" />
					<form:errors path="username" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<c:choose>
				<c:when test="${userForm['new']}">
					<spring:bind path="password">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Senha</label>
							<div class="col-sm-10">
								<form:password path="password" class="form-control" id="password" placeholder="password" />
								<form:errors path="password" class="control-label" />
							</div>
						</div>
					</spring:bind>
			
					<spring:bind path="confirmPassword">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Confirmar Senha</label>
							<div class="col-sm-10">
								<form:password path="confirmPassword" class="form-control" id="password" placeholder="password" />
								<form:errors path="confirmPassword" class="control-label" />
							</div>
						</div>
					</spring:bind>
				</c:when>
				<c:otherwise>
				</c:otherwise>
		</c:choose>

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
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${userForm['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right">Adicionar</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Atualizar</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>