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
    <fmt:message bundle="${loc}" key="local.btn.next" var="select"/>
    <fmt:message bundle="${loc}" key="local.page.free_patients" var="title"/>
    <title>${title}</title>
</head>
<body>
<div class="container">
    <div class="row d-flex w-100 justify-content-center">
        <div class="col-lg-3 flex">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="go_to_receipt_date_page"/>
                <label for="name" class="form-label m-2">${name}</label>
                    <select name = "free_patient_id"  class="form-select m-2" id="name" >
                        <c:forEach var="patient" items="${requestScope.patientList}">
                            <option value="${patient.id}">${patient.firstname} ${patient.lastname} </option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-info m-2">${select}</button>
                </form>
            </div>
        </div>
    </div>
<cpr:copyright/>
</body>
</html>
