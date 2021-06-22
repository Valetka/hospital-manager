<%@ page contentType="text/html;
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
    <fmt:message bundle="${loc}" key="local.firstname" var="first"/>
    <fmt:message bundle="${loc}" key="local.lastname" var="last"/>
    <fmt:message bundle="${loc}" key="local.btn.enter" var="submit"/>
    <fmt:message bundle="${loc}" key="local.page.additional_info" var="title"/>
    <title>${title}</title>
</head>
<body>

<div class="container">
    <div class="row d-flex w-100 justify-content-center">
        <div class="col-lg-5 flex">
            <form action="Controller" method="post" >
                <input type="hidden" name="command" value="add_additional_info" />
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="firstname" id="firstname" required>
                    <label for="firstname">${first}</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="lastname" id="lastname" required>
                    <label for="lastname">${last}</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="number" class="form-control" name="age"  min="1" id="age" required />
                    <label for="age">${age}</label>
                </div>
                <button type="submit" class="btn btn-success m-2">OK</button>
            </form>
        </div>
    </div>
</div>

<cpr:copyright/>
</body>
</html>