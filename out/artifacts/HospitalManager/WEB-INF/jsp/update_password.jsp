
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<meta charset="utf-8"><link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file = "header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.old_password" var="old"/>
    <fmt:message bundle="${loc}" key="local.new_password" var="newp"/>
    <fmt:message bundle="${loc}" key="local.btn.update" var="update"/>
    <fmt:message bundle="${loc}" key="local.info_for_password" var="describ"/>
    <fmt:message bundle="${loc}" key="local.page.update_password" var="title"/>
    <title>${title}</title>
</head>
<body>


<div class="container">
    <div class="row d-flex w-100 justify-content-center">
        <div class="col-lg-5 flex">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="update_password" />
                <div class="form-floating m-2">
                    <input type="password" name="old_password" class="form-control" id="old_p" minlength="6" required />
                    <label for="old_p" class="form-label">${old}</label>
                </div >
                <div class="form-floating m-2">
                    <input type="password" name="new_password" class="form-control" id="new_p" minlength="6"  aria-describedby="passwordHelpBlock"  required />
                    <label for="new_p" class="form-label">${newp}</label>
                    <div id="passwordHelpBlock" class="form-text">
                        ${describ}
                    </div>
                </div>
                <button type="submit" class="btn btn-primary m-2">${update}</button>
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
</body>
</html>