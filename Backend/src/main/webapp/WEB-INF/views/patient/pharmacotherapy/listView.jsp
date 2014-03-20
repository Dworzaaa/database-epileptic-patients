<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<t:menuLVL2>

    <jsp:attribute name="title">
      <spring:message code="label.pharmacotherapy"/>
    </jsp:attribute>

    <jsp:attribute name="head">
     <link href="<c:url value="/resources/custom/css/custom.css" />" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-xs-6">
                <h2>
                    <spring:message code="label.pharmacotherapy"/>
                </h2>

            </div>
            <div class="col-xs-6">
                <h3 class="pull-right">
                    <a href="<c:url value="/patient/${patient.id}/pharmacotherapy/create" />">
                        <spring:message code="label.addRecord"/>
                    </a>
                </h3>
            </div>
        </div>

        <%@ include file="../patientDetails.jsp" %>

        <!-- neurologicalFinding list START -->

        <c:choose>
            <c:when test="${empty pharmacotherapyDisplayVoList}">
                <div class="alert alert-info">
                    <spring:message code="label.noRecords"/>
                </div>
            </c:when>
            <c:otherwise>
                <div class="list-striped">

                    <table class="record-head table">
                        <tr>
                            <th class="col-xs-2">Datum
                            </th>
                            <th class="col-xs-2">
                                <spring:message code="label.aed"/>
                            </th>
                            <th class="col-xs-2">
                                <spring:message code="label.efficiency"/>
                            </th>
                            <th class="col-xs-2">
                                <spring:message code="label.duringSurgery"/>
                            </th>
                            <th class="col-xs-4"></th>
                        </tr>
                    </table>
                    <c:forEach items="${pharmacotherapyDisplayVoList}" var="pharmacotherapyDisplayVo">
                        <div>

                            <%@ include file="pharmacotherapyTableView.jsp" %>

                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>

        <!-- neurologicalFinding list END -->
    </jsp:body>
</t:menuLVL2>