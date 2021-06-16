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
    <fmt:message bundle="${loc}" key="local.firstname" var="name"/>
    <fmt:message bundle="${loc}" key="local.fulfilled" var="fulfilled"/>
    <fmt:message bundle="${loc}" key="local.appointed" var="appointed"/>
    <fmt:message bundle="${loc}" key="local.date_of_appointment" var="date_app"/>
    <fmt:message bundle="${loc}" key="local.date_of_appointment" var="date_comp"/>
    <fmt:message bundle="${loc}" key="local.appointment" var="appointment"/>
    <fmt:message bundle="${loc}" key="local.type" var="type"/>
    <fmt:message bundle="${loc}" key="local.status" var="status"/>
    <fmt:message bundle="${loc}" key="local.preparations" var="prep"/>
    <fmt:message bundle="${loc}" key="local.procedure" var="procedure"/>
    <fmt:message bundle="${loc}" key="local.surgery" var="surgery"/>
    <fmt:message bundle="${loc}" key="local.status.appointed" var="status_app"/>
    <fmt:message bundle="${loc}" key="local.status.done" var="done"/>
    <fmt:message bundle="${loc}" key="local.status.canceled" var="canceled"/>
    <fmt:message bundle="${loc}" key="local.btn.execute" var="execute"/>
    <fmt:message bundle="${loc}" key="local.btn.undo" var="undo"/>
    <fmt:message bundle="${loc}" key="local.page.appointment_list" var="title"/>
    <title>${title}</title>

    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<div class="container m-5">
    <div class="row d-flex">
        <div class="col-lg-3 flex">
            <table class="table table-bordered border-primary">
                <tr class="table-primary">
                    <td>${fulfilled}</td>
                    <td>${appointed}</td>
                    <td>${date_app}</td>
                    <td>${date_comp}</td>
                    <td>${appointment}</td>
                    <td>${type}</td>
                    <td>${status}</td>
                </tr>
                <c:forEach var="item" items="${requestScope.appointment_list}">
                    <tr>
                        <td>${item.executeStaffFirstname} ${item.executeStaffLastname}</td>
                        <td>${item.appointingDoctorFirstname} ${item.appointingDoctorLastname}</td>
                        <td>${item.dateOfAppointment}</td>
                        <td>${item.dateOfCompletion}</td>
                        <td>${item.info}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.type.toString().equals('PREPARATION')}">
                                    ${prep}
                                </c:when>
                                <c:when test="${item.type.toString().equals('PROCEDURE')}">
                                    ${procedure}
                                </c:when>
                                <c:when test="${item.type.toString().equals('SURGERY')}">
                                    ${surgery}
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${item.status.toString().equals('APPOINTED')}">
                                    ${status_app}
                                </c:when>
                                <c:when test="${item.status.toString().equals('DONE')}">
                                    ${done}
                                </c:when>
                                <c:when test="${item.status.toString().equals('CANCELED')}">
                                    ${canceled}
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<cpr:copyright/>
</body>
</html>
