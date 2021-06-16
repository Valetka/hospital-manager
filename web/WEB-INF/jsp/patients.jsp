<%@ page language="java" contentType="text/html;
    charset=utf-8"
         pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file = "header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.firstname" var="name"/>
    <fmt:message bundle="${loc}" key="local.btn.discharge" var="discharge"/>
    <fmt:message bundle="${loc}" key="local.page.my_patients" var="title"/>
    <title>${title}</title>
</head>
<body>
<table class="d-flex m-5 table table-striped  table-borderless">
    <tr>
        <td> ${name}</td>
    </tr>
    <c:forEach var="patient" items="${requestScope.patientList}">
        <tr>
            <td>
                <a href="Controller?command=go_to_medical_history_page&patientId=${patient.id}">${patient.firstname} ${patient.lastname}</a>
            <td>
            <td>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="go_to_diagnosis_page"/>
                    <input type="hidden" name="patient_id" value="${patient.id}"/>
                    <button type="submit" class="btn btn-info btn-sm">${discharge}</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
