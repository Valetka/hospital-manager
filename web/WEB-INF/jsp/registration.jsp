
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta charset="utf-8"><link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="cpr" uri="/WEB-INF/tld/taglib.tld" %>
<%@ include file = "header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="<c:url value="/css/login.css"/>">
	<link rel="stylesheet" href="../../css/footer.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
	<meta charset="utf-8">
	<fmt:setLocale value="${sessionScope.locale}"/>
	<fmt:setBundle basename="local.local" var="loc"/>
	<fmt:message bundle="${loc}" key="local.login" var="login"/>
	<fmt:message bundle="${loc}" key="local.password" var="password"/>
	<fmt:message bundle="${loc}" key="local.firstname" var="first"/>
	<fmt:message bundle="${loc}" key="local.lastname" var="last"/>
	<fmt:message bundle="${loc}" key="registration.btn" var="registration"/>
	<fmt:message bundle="${loc}" key="local.back" var="back"/>
	<fmt:message bundle="${loc}" key="local.info_for_password" var="describ"/>
	<fmt:message bundle="${loc}" key="local.warning.please_enter" var="please"/>
	<fmt:message bundle="${loc}" key="local.page.registration" var="title"/>
	<title>${title}</title>
</head>
<body>



<div class="container">
	<div class="row d-flex w-100 justify-content-center">
		<div class="col-5">
			<form  action="Controller" method="post">
					<input type="hidden" name="command" value="add_account" />

					<div class="form-floating mb-3">
						<input type="text" class="form-control" name="login" id="login" required>
						<label for="login">${login}</label>
					</div>
					<div class="form-floating mb-3">
						<input type="password" class="form-control" name="password" id="passIn"  minlength="6"  aria-describedby="passwordHelpBlock" required>
						<label for="passIn">${password}</label>
						<div id="passwordHelpBlock" class="form-text">
							${describ}
						</div>

					</div>
					<button type="submit" class="btn btn-primary">${registration}</button>
				</form>
		</div>
	</div>


	<c:if test="${errorMessage != null}">
		<c:forEach var="errorMessageKey" items="${errorMessage}">
			<fmt:message bundle="${loc}" key="${errorMessageKey}" var="messageEr"/>
			<div class="alert alert-danger" role="alert">
					${messageEr}
			</div>
		</c:forEach>
		<c:remove var="errorMessage"/>
	</c:if>

	<c:if test="${informationMessage != null}">
		<c:forEach var="errorMessageKey" items="${informationMessage}">
			<fmt:message bundle="${loc}" key="${errorMessageKey}" var="messageInf"/>
			<div class="alert alert-info" role="alert">
					${messageInf}
			</div>
		</c:forEach>
		<c:remove var="informationMessage"/>
	</c:if>

	<cpr:copyright/>
</body>

</html>
