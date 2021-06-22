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
    <link rel="stylesheet" href="css/card.css">
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.welcome" var="welcome"/>
    <fmt:message bundle="${loc}" key="local.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="local.my_appointment" var="my_appoint"/>
    <fmt:message bundle="${loc}" key="local.btn.medical_history" var="hist"/>
    <fmt:message bundle="${loc}" key="local.btn.request" var="submit"/>
    <fmt:message bundle="${loc}" key="local.btn.app_sub" var="application_submitted"/>
    <fmt:message bundle="${loc}" key="local.card.patient_appointment" var="app"/>
    <fmt:message bundle="${loc}" key="local.card.patient_medical_history" var="hist_card"/>
    <fmt:message bundle="${loc}" key="local.card.request" var="request"/>
    <fmt:message bundle="${loc}" key="local.page.patient" var="title"/>
    <title>${title}</title>
</head>
<body  class="bg-info d-flex flex-column h-100">
    <div class="d-flex flex-row flex-wrap justify-content-center flex-grow-1">
        <c:if test="${sessionScope.role == 'patient'}">
            <div class="card m-2" style="width: 18rem; height: 35%">
                <div class="card-body m-2 d-flex flex-column align-items-center justify-content-between">
                    <h5 class="card-title">${my_appoint}</h5>
                    <p class="card-text text-center">${app}</p>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="go_to_patient_appointment_list_page"/>
                        <button class="btn btn-info card-link">${my_appoint}</button>
                    </form>
                </div>
            </div>
            <div class="card m-2" style="width: 18rem; height: 35%">
                <div class="card-body m-2 d-flex flex-column align-items-center justify-content-between">
                    <h5 class="card-title">${hist}</h5>
                    <p class="card-text text-center">${hist_card}</p>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="go_to_medical_history_page"/>
                        <button class="btn btn-info card-link">${hist}</button>
                    </form>
                </div>
            </div>
            <c:if test="${sessionScope.patient.statusID==2}">
                <div class="card m-2" style="width: 18rem; height: 35%">
                    <div class="card-body m-2 d-flex flex-column align-items-center justify-content-between">
                        <h5 class="card-title">${submit}</h5>
                        <p class="card-text text-center">${request}</p>
                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="submit_application"/>
                            <button class="btn btn-info card-link">${submit}</button>
                        </form>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.patient.statusID==1 || sessionScope.patient.statusID ==3}">
                <div class="card m-2" style="width: 18rem;height: 35%">
                    <div class="card-body m-2 d-flex flex-column align-items-center justify-content-between">
                        <h5 class="card-title">${submit}</h5>
                        <p class="card-text text-center">${request}</p>
                        <button class="btn btn-success btn-lg">${application_submitted}</button>
                    </div>
                </div>
            </c:if>
        </c:if>
    </div>
    <cpr:copyright/>
</body>
</html>