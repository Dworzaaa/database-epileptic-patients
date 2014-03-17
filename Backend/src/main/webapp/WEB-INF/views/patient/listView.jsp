<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<t:menuLVL1>

<jsp:attribute name="title">
      <spring:message code="label.cardIndex"/>
    </jsp:attribute>

	<jsp:attribute name="head">
    <link href="<c:url value="/resources/custom/css/clickable-row.css" />" rel="stylesheet">
    </jsp:attribute>

	<jsp:attribute name="script">
    <script src="<c:url value="/resources/custom/js/patient-list.js"/>"></script>
    </jsp:attribute>

<jsp:body>
<div class="row">
    <div class="col-xs-6">
        <h2>
            <spring:message code="label.cardIndex"/>
        </h2>
    </div>
    <div class="col-xs-6">
        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_DOCTOR,ROLE_SUPER_DOCTOR">
            <h3 class="pull-right">
                <a href="<c:url value="/patient/create" />">
                    <spring:message code="label.addPatient"/>
                </a>
            </h3>
        </sec:authorize>
    </div>
</div>
<div class="row">
    <div class="col-xs-12">
        <div class="form-group">
            <label for="search" class="col-xs-2 control-label"><spring:message code="label.filter"/>:</label>

            <div class="col-xs-4 input-group">
                <span class="input-group-addon glyphicon glyphicon-search"></span>
                <input type="text" class="form-control" id="search"
                       placeholder="<spring:message code="label.lastname"/>/<spring:message code="label.firstname"/>/<spring:message code="label.nin"/>"
                       data-max-results="${maxResult}">
            </div>
        </div>
    </div>
</div>

<div class="text-center">
    <ul class="pagination">
        <li><a class="start" href="#">&laquo;</a></li>
        <li><a class="prev" href="#">&lsaquo;</a></li>
            <%-- <li><a href="#">1</a></li>
             <li><a href="#">2</a></li>
             <li><a href="#">3</a></li>
             <li><a href="#">4</a></li>
             <li><a href="#">5</a></li> --%>
        <li class="next-li"><a class="next" href="#">&rsaquo;</a></li>
        <li><a class="end" href="#">&raquo;</a></li>
    </ul>
</div>

<div class="table-responsive">
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <td>
                <b><spring:message code="label.firstname"/></b>
            </td>
            <td>
                <b><spring:message code="label.lastname"/></b>
            </td>
            <td>
                <b><spring:message code="label.birthIdentificationNumber"/></b>
            </td>
            <td>
                <b><spring:message code="label.address"/></b>
            </td>
            <td>
                <b><spring:message code="label.addressCity"/></b>
            </td>
        </tr>
        </thead>
        <tbody id="patientList">
            <%--  <c:forEach items="${patientList}" var="patient">
                  <tr class="clickable-row" href="<c:url value="/patient/${patient.id}/overview" />">
                      <td>
                              ${patient.contact.firstName}
                      </td>
                      <td>
                              ${patient.contact.lastName}
                      </td>
                      <td>
                              ${patient.nin}
                      </td>
                      <c:choose>
                          <c:when test="${empty patient.contact.addressStreet}">
                              <td></td>
                          </c:when>
                          <c:otherwise>
                              <td>
                                      ${patient.contact.addressStreet}, ${patient.contact.addressHn}
                              </td>
                          </c:otherwise>
                      </c:choose>
                      <td>
                              ${patient.contact.addressCity}
                      </td>
                  </tr>
              </c:forEach> --%>
        </tbody>
    </table>
</div>

<div class="text-center">
    <ul class="pagination">
        <li><a class="start" href="#">&laquo;</a></li>
        <li><a class="prev" href="#">&lsaquo;</a></li>
            <%-- <li><a href="#">1</a></li>
             <li><a href="#">2</a></li>
             <li><a href="#">3</a></li>
             <li><a href="#">4</a></li>
             <li><a href="#">5</a></li> --%>
        <li class="next-li"><a class="next" href="#">&rsaquo;</a></li>
        <li><a class="end" href="#">&raquo;</a></li>
    </ul>
</div>


<%--
<c:set var="countOfPagesString" value="${countOfPatients/maxResults}" scope="page"/>

<fmt:formatNumber var="countOfPages" value="${countOfPagesString}" maxFractionDigits="0"/>

<c:if test="${countOfPages<countOfPagesString}">
    <c:set var="countOfPages" value="${countOfPages+1}" scope="page"/>
</c:if>

<div id="paginator" class="text-center">
    <ul class="pagination">
        <li>
            <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=1" />">&laquo;</a>
        </li>
        <c:choose>
            <c:when test="${pageNumber<=1}">
                <li class="disabled">
                    <a href="#">&lsaquo;</a>
                </li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${pageNumber-1}" />">&lsaquo;</a>
                </li>
            </c:otherwise>
        </c:choose>

        <c:if test="${pageNumber-2>0}">
            <li>
                <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${pageNumber-2}" />">
                        ${pageNumber-2} <span class="sr-only"></span>
                </a>
            </li>
        </c:if>

        <c:if test="${pageNumber-1>0}">
            <li>
                <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${pageNumber-1}" />">
                        ${pageNumber-1} <span class="sr-only"></span>
                </a>
            </li>
        </c:if>

        <li class="active">
            <a href="#">
                    ${pageNumber-i}<span class="sr-only"></span>
            </a>
        </li>

        <c:forEach var="i" begin="1" end="2">
            <c:if test="${countOfPages>=pageNumber+i}">
                <li>
                    <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${pageNumber+i}" />">
                            ${pageNumber+i} <span class="sr-only"></span>
                    </a>
                </li>
            </c:if>
        </c:forEach>

        <c:choose>
            <c:when test="${countOfPages<=pageNumber}">
                <li class="disabled">
                    <a href="#">&rsaquo;</a>
                </li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${pageNumber+1}" />">&rsaquo;</a>
                </li>
            </c:otherwise>
        </c:choose>

        <li>
            <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${countOfPages}" />">&raquo;</a>
        </li>
    </ul>
</div>

<div id="defaultPaginator" class="text-center" style="display: none">
    <c:set var="defaultPageNmuber" value="1" scope="page"/>
    <ul class="pagination">
        <li>
            <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=1" />">&laquo;</a>
        </li>
        <c:choose>
            <c:when test="${defaultPageNmuber<=1}">
                <li class="disabled">
                    <a href="#">&lsaquo;</a>
                </li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${defaultPageNmuber-1}" />">&lsaquo;</a>
                </li>
            </c:otherwise>
        </c:choose>

        <c:if test="${defaultPageNmuber-2>0}">
            <li>
                <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${defaultPageNmuber-2}" />">
                        ${defaultPageNmuber-2} <span class="sr-only"></span>
                </a>
            </li>
        </c:if>

        <c:if test="${defaultPageNmuber-1>0}">
            <li>
                <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${defaultPageNmuber-1}" />">
                        ${defaultPageNmuber-1} <span class="sr-only"></span>
                </a>
            </li>
        </c:if>

        <li class="active">
            <a href="#">
                    ${defaultPageNmuber-i}<span class="sr-only"></span>
            </a>
        </li>

        <c:forEach var="i" begin="1" end="2">
            <c:if test="${countOfPages>=defaultPageNmuber+i}">
                <li>
                    <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${defaultPageNmuber+i}" />">
                            ${defaultPageNmuber+i} <span class="sr-only"></span>
                    </a>
                </li>
            </c:if>
        </c:forEach>

        <c:choose>
            <c:when test="${countOfPages<=defaultPageNmuber}">
                <li class="disabled">
                    <a href="#">&rsaquo;</a>
                </li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${defaultPageNmuber+1}" />">&rsaquo;</a>
                </li>
            </c:otherwise>
        </c:choose>

        <li>
            <a href="<c:url value="/patient/list?maxResults=${maxResults}&pageNumber=${countOfPages}" />">&raquo;</a>
        </li>
    </ul>
</div>
--%>
</jsp:body>
</t:menuLVL1>
