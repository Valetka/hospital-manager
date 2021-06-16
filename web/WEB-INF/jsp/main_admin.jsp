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
    <fmt:message bundle="${loc}" key="local.welcome" var="welcome"/>
    <fmt:message bundle="${loc}" key="local.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="local.add_staff" var="addst"/>
    <fmt:message bundle="${loc}" key="local.add_patient" var="addpat"/>
    <fmt:message bundle="${loc}" key="local.card.add_staff" var="cardst"/>
    <fmt:message bundle="${loc}" key="local.card.add_patient" var="cardpat"/>

    <fmt:message bundle="${loc}" key="local.page.administrator" var="title"/>
    <title>${title}</title>
</head>
<body  class="bg-info">

<h1 align="center">

</h1>

        <c:if test="${sessionScope.role == 'admin'}">
            <div class="d-flex flex-row flex-wrap">
                <div class="card m-2" style="width: 18rem;">
                    <div class="card-body m-2">
                        <h5 class="card-title">${addst}</h5>
                        <p class="card-text">${cardst}</p>
                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="go_to_add_staff_page"/>
                            <button class="btn btn-info card-link">${addst}</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="d-flex flex-row flex-wrap">
                <div class="card m-2" style="width: 18rem;">
                    <div class="card-body m-2">
                        <h5 class="card-title">${addpat}</h5>
                        <p class="card-text">${cardpat}</p>
                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="go_to_add_patient_page"/>
                            <button class="btn btn-info card-link">${addpat}</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>

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