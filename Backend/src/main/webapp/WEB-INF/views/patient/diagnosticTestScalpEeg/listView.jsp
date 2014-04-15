<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<t:menuLVL2>
    <jsp:attribute name="title">
      <spring:message code="label.diagnosticTestScalpEeg"/>
    </jsp:attribute>

    <jsp:attribute name="head">
     <link href="<c:url value="/resources/custom/css/custom.css" />" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-xs-6">
                <h2>
                    <spring:message code="label.diagnosticTestsScalpEeg"/>
                </h2>

            </div>
            <div class="col-xs-6">
                <h3 class="pull-right">
                    <a href="<c:url value="/patient/${patient.id}/diagnostic-test-scalp-eeg/create" />">
                        <spring:message code="label.addRecord"/>
                    </a>
                </h3>
            </div>
        </div>

        <jsp:include page="../patientDetails.jsp"/>

        <c:choose>
            <c:when test="${empty diagnosticTestScalpEegDisplayVoList}">
                <div class="alert alert-info">
                    <spring:message code="label.noRecords"/>
                </div>
            </c:when>
            <c:otherwise>
                <c:set var="count"
                       value="0"
                       scope="request"/>
                <div class="list-striped">
                    <c:forEach items="${diagnosticTestScalpEegDisplayVoList}"
                               var="diagnosticTestScalpEegDisplayVo">
                        <c:set var="diagnosticTestScalpEegDisplayVo"
                               value="${diagnosticTestScalpEegDisplayVo}"
                               scope="request"/>
                        <div>
                            <table class="record-head table">
                                <tbody>
                                <tr>
                                    <th class="col-xs-8">
                                        <a data-toggle="collapse"
                                           href="#collapse-diagnostic-test-scalp-eeg-${diagnosticTestScalpEegDisplayVo.id}">
                                            <spring:message
                                                    code="label.dateAdded"/>: ${diagnosticTestScalpEegDisplayVo.date}
                                        </a>
                                    </th>
                                    <th class="col-xs-2">
                                        <a class="pull-right"
                                           href="<c:url value="/patient/${patient.id}/diagnostic-test-scalp-eeg/${diagnosticTestScalpEegDisplayVo.id}/edit"/>">
                                            <span class="glyphicon glyphicon-edit"></span>
                                            <spring:message code="label.edit"/>
                                        </a>
                                    </th>
                                    <th class="col-xs-2">
                                        <a class="pull-right"
                                           href="<c:url value="/patient/${patient.id}/diagnostic-test-scalp-eeg/${diagnosticTestScalpEegDisplayVo.id}/hide"/>">
                                            <span class="glyphicon glyphicon-remove-circle"></span>
                                            <spring:message code="label.delete"/>
                                        </a>
                                    </th>
                                </tr>
                                </tbody>
                            </table>

                            <jsp:include page="diagnosticTestScalpEegTableView.jsp"/>

                        </div>
                        <c:set var="count" value="1" scope="request"/>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>

    </jsp:body>
</t:menuLVL2>