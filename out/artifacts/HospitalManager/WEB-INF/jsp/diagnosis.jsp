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
    <fmt:message bundle="${loc}" key="local.age" var="age"/>
    <fmt:message bundle="${loc}" key="local.pic" var="pic"/>
    <fmt:message bundle="${loc}" key="local.receiptDate" var="receipt"/>
    <fmt:message bundle="${loc}" key="local.preliminaryDiagnosis" var="preliminary"/>
    <fmt:message bundle="${loc}" key="local.btn.enter" var="submit"/>
    <fmt:message bundle="${loc}" key="local.dischargeDate" var="discharge"/>
    <fmt:message bundle="${loc}" key="local.definitiveDiagnosis" var="diagnosis"/>
    <fmt:message bundle="${loc}" key="local.page.diagnosis" var="title"/>
    <title>${title}</title>
</head>
<body>
    <div class="container">
        <div class="row d-flex w-100 justify-content-center">
            <div class="col-lg-5 flex">
                <ul class="list-group m-2">
                    <li class="list-group-item">
                      ${receipt} : ${requestScope.diagnosis.receiptDate}
                    </li>
                    <li class="list-group-item">
                       ${preliminary} : ${requestScope.diagnosis.preliminaryDiagnosis}
                    </li>
                </ul>
                <form action="Controller" method="post" >
                    <input type="hidden" name="command" value="add_diagnosis" />
                    <input type="hidden" name="patient_id" value="${requestScope.patient_id}">
                    <div class="form-floating mb-3 m-2">
                        <input type="date" class="form-control" name="dischargeDate" id="dis" required>
                        <label for="dis" class="form-label">${discharge}</label>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control m-2" name="definitiveDiagnosis" placeholder="Leave a comment here" id="floatingTextarea"></textarea>
                        <label for="floatingTextarea">${diagnosis}</label>
                    </div>
                    <button type="submit" class="btn btn-success m-2">OK</button>
                </form>
            </div>
        </div>
    </div>
    <cpr:copyright/>
</body>
</html>