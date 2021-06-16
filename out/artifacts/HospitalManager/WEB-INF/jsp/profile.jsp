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
    <fmt:message bundle="${loc}" key="local.firstname" var="first"/>
    <fmt:message bundle="${loc}" key="local.lastname" var="last"/>
    <fmt:message bundle="${loc}" key="local.age" var="age"/>
    <fmt:message bundle="${loc}" key="profile.status.main" var="status"/>
    <fmt:message bundle="${loc}" key="profile.status.discharged" var="dis"/>
    <fmt:message bundle="${loc}" key="profile.status.on_treatment" var="treatment"/>
    <fmt:message bundle="${loc}" key="profile.attending_doctor" var="attending_doctor"/>
    <fmt:message bundle="${loc}" key="profile.status.doctor" var="doctor"/>
    <fmt:message bundle="${loc}" key="profile.status.nurse" var="nurse"/>
    <fmt:message bundle="${loc}" key="local.btn.update_password" var="update"/>
    <fmt:message bundle="${loc}" key="local.btn.update_image" var="image"/>
    <fmt:message bundle="${loc}" key="local.btn.close" var="close"/>
    <fmt:message bundle="${loc}" key="local.page.profile" var="title"/>
    <title>${title}</title>
</head>
<body>
<div class="d-flex m-2">
    <c:if test="${sessionScope.role.equalsIgnoreCase('patient')}">
        <table class="table-borderless">
            <tr>

              <td>
                  <img src="<c:url value="${requestScope.patient.patientPic}"></c:url>" class="rounded-circle" width="100" height="80"  alt=""/>
              </td>
            </tr>
            <tr>
              <td>
                  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                      ${image}
                  </button>
                  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog">
                          <div class="modal-content">
                              <div class="modal-header">
                                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body">
                                  <form action="UploadFileController" method="post" enctype="multipart/form-data">
                                      <input type="file" name="file" required/>
                                      <button type="submit" class="btn btn-primary">OK</button>
                                  </form>
                              </div>
                          </div>
                      </div>
                  </div>
              </td>
            </tr>
            <tr>
            <td>${first}<td>
                <td>${requestScope.patient.firstname}</td>
            </tr>
            <tr>
                <td>${last}<td>
                <td>${requestScope.patient.lastname}</td>
            </tr>
            <tr>
                <td>${age}<td>
                <td>${requestScope.patient.age}</td>
            </tr>
            <tr>
                <td>${status}<td>
                <c:if test="${requestScope.patient.statusID == 1}">
                    <td>${treatment}</td>
                </c:if>
                <c:if test="${requestScope.patient.statusID == 2}">
                    <td>${dis}</td>
                </c:if>
            </tr>
            <tr>
                <td>${attending_doctor}<td>
                <td>${requestScope.attendingDoctor.firstname} ${requestScope.attendingDoctor.lastname}</td>
            </tr>
            <tr>
                <td>
                    <div>
                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="go_to_password_update_page"/>
                            <button type="submit" class="btn btn-primary m-2">${update}</button>
                        </form>
                    </div>
                </td>
            </tr>
        </table>
    </c:if>
    <c:if test="${sessionScope.role.equalsIgnoreCase('doctor')||sessionScope.role.equalsIgnoreCase('nurse')}">
        <table class="table-borderless">
            <tr>
                <td>
                    <img src="<c:url value="${requestScope.staff.picture}"></c:url>" class="rounded-circle" width="100" height="80"  alt=""/>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal2">
                            ${image}
                    </button>
                    <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="UploadFileController" method="post" enctype="multipart/form-data">
                                        <input type="file" name="file" required/>
                                        <button type="submit" class="btn btn-primary">OK</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>

            <tr>
                <td>${first}<td>
                <td>${requestScope.staff.firstname}</td>
            </tr>
            <tr>
                <td>${last}<td>
                <td>${requestScope.staff.lastname}</td>
            </tr>
            <tr>
                <td>${status}<td>
                <c:if test="${requestScope.staff.staffTypeID==1}">
                    <td>${doctor}</td>
                </c:if>
                <c:if test="${requestScope.staff.staffTypeID==2}">
                    <td>${nurse}</td>
                </c:if>
            </tr>
            <tr>
                <td>
                    <div>
                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="go_to_password_update_page"/>
                            <button type="submit" class="btn btn-primary m-2">${update}</button>
                        </form>
                    </div>
                </td>
            </tr>

        </table>
    </c:if>
</div>
<cpr:copyright/>
</body>
</html>
