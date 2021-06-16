
<%@ page language="java" contentType="text/html;
    charset=utf-8" 
    pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="cpr" uri="/WEB-INF/tld/taglib.tld" %>
<%@ include file = "header.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<fmt:setLocale value="${sessionScope.locale}"/>
	<fmt:setBundle basename="local.local" var="loc"/>
	<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
	<fmt:message bundle="${loc}" key="local.welcome" var="welcome"/>
	<fmt:message bundle="${loc}" key="local.btn.add_appointment" var="add"/>
	<fmt:message bundle="${loc}" key="local.btn.my_patients" var="my"/>
	<fmt:message bundle="${loc}" key="local.btn.get_patient" var="get"/>
	<fmt:message bundle="${loc}" key="local.card.my_patients" var="card_p"/>
	<fmt:message bundle="${loc}" key="local.card.my_app" var="my_app_card"/>
	<fmt:message bundle="${loc}" key="local.card.get_from_free" var="card_free"/>
	<fmt:message bundle="${loc}" key="local.card.add_app" var="card_add"/>
	<fmt:message bundle="${loc}" key="local.btn.my_appointments" var="appoint"/>
	<fmt:message bundle="${loc}" key="local.page.staff" var="title"/>
	<title>${title}</title>
</head>
<body class="bg-info">
		<div class="d-flex flex-row flex-wrap justify-content-center">
				<c:if test="${sessionScope.role == 'doctor'}">
					<div class="card m-2" style="width: 18rem;">
						<div class="card-body m-2">
							<h5 class="card-title">${add}</h5>
							<p class="card-text">${card_add}</p>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="go_to_add_appointment_page"/>
								<button class="btn btn-info card-link">${add}</button>
							</form>
						</div>
					</div>
					<div class="card m-2" style="width: 18rem;">
						<div class="card-body m-2">
							<h5 class="card-title">${my}</h5>
							<p class="card-text">${card_p}</p>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="go_to_doctors_patients_page"/>
								<button class="btn btn-info card-link">${my}</button>
							</form>
						</div>
					</div>
					<div class="card m-2" style="width: 18rem;">
						<div class="card-body m-2">
							<h5 class="card-title">${get}</h5>
							<p class="card-text">${card_free}</p>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="go_to_free_patients_page"/>
								<button class="btn btn-info card-link">${get}</button>
							</form>
						</div>
					</div>
				</c:if>
					<div class="card m-2" style="width: 18rem;">
						<div class="card-body m-2">
							<h5 class="card-title">${appoint}</h5>
							<p class="card-text">${my_app_card}</p>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="go_to_staff_appointment_list_page"/>
								<button class="btn btn-info card-link">${appoint}</button>
							</form>
						</div>
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