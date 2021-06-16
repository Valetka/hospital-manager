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
    <fmt:message bundle="${loc}" key="local.date_of_completion" var="date_comp"/>
    <fmt:message bundle="${loc}" key="local.select" var="select"/>
    <fmt:message bundle="${loc}" key="local.btn.add" var="add"/>
    <fmt:message bundle="${loc}" key="local.appointment" var="appoint"/>
    <fmt:message bundle="${loc}" key="local.date_of_appointment" var="date_app"/>
    <fmt:message bundle="${loc}" key="local.staff" var="st"/>
    <fmt:message bundle="${loc}" key="local.type" var="type"/>
    <fmt:message bundle="${loc}" key="local.surgery" var="surgery"/>
    <fmt:message bundle="${loc}" key="local.preparations" var="prep"/>
    <fmt:message bundle="${loc}" key="local.procedure" var="procedure"/>
    <fmt:message bundle="${loc}" key="local.doctor" var="doctor"/>
    <fmt:message bundle="${loc}" key="local.nurse" var="nurse"/>
    <fmt:message bundle="${loc}" key="local.patient" var="patient"/>
    <fmt:message bundle="${loc}" key="local.page.appointment" var="title"/>
    <title>${title}</title>

</head>
<body>
<div class="container ">
    <div class="row d-flex w-100 justify-content-center">
        <div class="col-6">
            <ul class="list-group m-2">
                <li class="list-group-item">${type} :
                    <c:if test="${requestScope.select_type == 1}">
                        ${prep}</li>
                </c:if>
                <c:if test="${requestScope.select_type == 2}">
                    ${procedure}</li>
                </c:if>
                <c:if test="${requestScope.select_type == 3}">
                    ${surgery}</li>
                </c:if>

                <li class="list-group-item">${date_app} : ${requestScope.dateOfAppointment}</li>
                <li class="list-group-item">${patient} : ${requestScope.select_patient_id.firstname} ${requestScope.select_patient_id.id}</li>
            </ul>
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="add_appointment" />
                <input type="hidden" name="select_type" value="${requestScope.select_type}" />
                <input type="hidden" name="dateOfAppointment" value="${requestScope.dateOfAppointment}" />
                <input type="hidden" name="select_patient_id" value="${requestScope.select_patient_id.id}" />

                <c:if test="${requestScope.select_type!=1}">
                    <label for="inputDate" class="form-label m-2">${date_comp}</label>
                    <input type="date"  id="inputDate"   class="form-control m-2" name="dateOfCompletion" required/>
                </c:if>

                <label for="inputStaff" class="form-label m-2">${st}</label>
                <select name = "select_staff_id"   class="form-select m-2" id="inputStaff">
                    <optgroup label="${doctor}">
                        <c:forEach var="staff" items="${requestScope.allStaff}">
                            <c:if test="${staff.staffTypeID==1}">
                                <option value="${staff.id}">${staff.firstname} ${staff.lastname} </option>
                            </c:if>
                        </c:forEach>
                    </optgroup>
                    <optgroup label="${nurse}">
                        <c:forEach var="staff" items="${requestScope.allStaff}">
                            <c:if test="${staff.staffTypeID==2}">
                                <option value="${staff.id}">${staff.firstname} ${staff.lastname} </option>
                            </c:if>
                        </c:forEach>
                    </optgroup>
                </select>

                <div class="form-floating">
                    <textarea class="form-control m-2" name="info" placeholder="Leave a comment here" id="floatingTextarea" required></textarea>
                    <label for="floatingTextarea">${appoint}</label>
                </div>
                <button type="submit" class="btn btn-success m-2 col-md-3">${add}</button>
            </form>
        </div>
    </div>
</div>


<cpr:copyright/>
</body>
</html>
