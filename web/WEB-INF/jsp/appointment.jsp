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
    <fmt:message bundle="${loc}" key="local.date_of_appointment" var="date_app"/>
    <fmt:message bundle="${loc}" key="local.date_of_completion" var="date_comp"/>
    <fmt:message bundle="${loc}" key="local.firstname" var="first"/>
    <fmt:message bundle="${loc}" key="local.lastname" var="last"/>
    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.select" var="select"/>
    <fmt:message bundle="${loc}" key="local.btn.next" var="submit"/>
    <fmt:message bundle="${loc}" key="local.preparations" var="prep"/>
    <fmt:message bundle="${loc}" key="local.procedure" var="procedure"/>
    <fmt:message bundle="${loc}" key="local.appointment" var="appoint"/>
    <fmt:message bundle="${loc}" key="local.surgery" var="surgery"/>
    <fmt:message bundle="${loc}" key="local.type" var="type"/>
    <fmt:message bundle="${loc}" key="local.patient" var="patient"/>
    <fmt:message bundle="${loc}" key="local.page.appointment" var="title"/>
    <title>${title}</title>
</head>
<body>

    <div class="container">
        <div class="row d-flex w-100 justify-content-center">
            <div class="col-6">
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="go_to_add_appointment_next_page" />

                    <div class="col-md-6">
                        <label for="inputDate" class="form-label">${date_app}</label>
                        <input type="date" class="form-control" name="dateOfAppointment" id="inputDate" required/>
                    </div>
                    <div class="col-md-6">
                        <label for="inputPatient" class="form-label">${patient}</label>
                        <select class="form-select" name = "select_patient_id" id="inputPatient" >
                            <c:forEach var="pati" items="${requestScope.allPatients}">
                                <option value="${pati.id}">${pati.firstname} ${pati.lastname} ${pati.age}</option>
                            </c:forEach>
                        </select>
                    </div>
                <div class="col-md-8">
                    <label for="inputApp" class="form-label">${type}</label>
                    <select class="form-select" name = "select_type" id="inputApp" >
                        <c:forEach var="AppType" items="${requestScope.types}">
                            <c:if test="${AppType.toString().equals('PREPARATION')}">
                                <option value="${AppType.id}">${prep}</option>
                            </c:if>
                            <c:if test="${AppType.toString().equals('PROCEDURE')}">
                                <option value="${AppType.id}">${procedure}</option>
                            </c:if>
                            <c:if test="${AppType.toString().equals('SURGERY')}">
                                <option value="${AppType.id}">${surgery}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-outline-primary m-2 col-md-3">${submit}</button>
            </form>
            </div>
        </div>
    </div>
    <cpr:copyright/>
</body>
</html>
