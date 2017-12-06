<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/header.jsp" />

<body>
	<div class="row" style="margin-top:20px">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
        	<spring:url value="/efetuaLogin" var="efetuaLoginUrl" />
        
            <form:form class="form-horizontal" method="post" modelAttribute="usuarioForm" action="${efetuaLoginUrl}">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                            Usuário ou senha inválida!
                        </div>
                    </c:if>
                    <c:if test="${not empty logout}">
                        <div class="alert alert-info">
                            Saiu com sucesso!
                        </div>
                    </c:if>
                    
                    <spring:bind path="username">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Usuário</label>
							<div class="col-sm-10">
								<form:input path="username" type="text" class="form-control " id="username" placeholder="username" />
								<form:errors path="username" class="control-label" />
							</div>
						</div>
					</spring:bind>
			
					<spring:bind path="password">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Senha</label>
							<div class="col-sm-10">
								<form:password path="password" class="form-control" id="password" placeholder="password" />
								<form:errors path="password" class="control-label" />
							</div>
						</div>
					</spring:bind>
					
					<div class="form-group">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<button type="submit" class="btn-lg btn-primary pull-right">Entrar</button>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6">
                        </div>
					</div>
            </form:form>
        </div>
    </div>
	
	<jsp:include page="fragments/footer.jsp"/>
</body>

</html>