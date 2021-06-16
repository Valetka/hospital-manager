<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

<meta charset="utf-8">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.lang.eng" var="en_button"/>
<fmt:message bundle="${loc}" key="local.lang.rus" var="ru_button"/>
<fmt:message bundle="${loc}" key="header.home" var="home"/>
<fmt:message bundle="${loc}" key="local.logout" var="logout"/>
<fmt:message bundle="${loc}" key="header.lang" var="lang"/>
<fmt:message bundle="${loc}" key="header.profile" var="profile"/>


<header >
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <c:choose>
        <c:when test="${sessionScope.role== null}">
            <a class="navbar-brand m-3" href="Controller?command=go_to_index_page ">
                </c:when>
                <c:otherwise>
                        <a class="navbar-brand m-3" href="Controller?command=go_to_main_page">
                </c:otherwise>
            </c:choose>
                    ${home}</a>

                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <c:if test="${sessionScope.auth == true && sessionScope.role != 'admin'}">
                                <li class="nav-item">
                                    <a  class="nav-link active" href="Controller?command=go_to_profile_page">${profile}</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.auth == false || sessionScope.auth == null}">
                                <li  class="nav-item">
                                    <a  class="nav-link active" href="Controller?command=go_to_index_page"><fmt:message bundle="${loc}" key="header.login"/></a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.auth == true}">
                                    <li class="nav-item justify-content-end">
                                        <a  class="nav-link active" href="Controller?command=logout">${logout}</a>
                                    </li>

                            </c:if>
                    </ul>
                    <div class="container-fluid d-flex flex-row-reverse">
                            <div class="right m-2">
                                <a href="Controller?command=change_locale&lang=eng" >
                                    <img src="<c:url value= "/images/eng.png"></c:url>"  class="rounded float-end m-2" width="40"  alt="...">
                                </a>
                            </div>
                            <div class="right m-2">
                                <a href="Controller?command=change_locale&lang=ru" >
                                    <img src="<c:url value="/images/ru.png"></c:url>"  class="rounded float-end m-2" width="40" alt="...">
                                </a>
                            </div>
                    </div>
        </div>
    </nav>

</header>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

