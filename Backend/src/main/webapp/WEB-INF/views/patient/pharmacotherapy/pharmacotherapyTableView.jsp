<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:useBean id="pharmacotherapyDisplayBO"
             scope="request"
             type="cz.cvut.fit.genepi.businessLayer.BO.display.card.PharmacotherapyDisplayBO"/>

<sec:authorize ifAnyGranted="ROLE_DOCTOR,ROLE_SUPER_DOCTOR,ROLE_ADMIN"
               var="isAuthorized"/>

<table class="record-head table">
    <tr>
        <td class="col-xs-2">
            <a data-toggle="collapse" href="#collapse-pharmacotherapy-${pharmacotherapyDisplayBO.id}">
                ${pharmacotherapyDisplayBO.date}
            </a>
        </td>
        <td class="col-xs-2">
            <spring:message code="label.aed.${pharmacotherapyDisplayBO.aed}"/>
        </td>
        <td class="col-xs-2">
            <spring:message code="label.efficiency.${pharmacotherapyDisplayBO.efficiency}"/>
        </td>
        <td class="col-xs-2">
            <spring:message code="label.boolean.${pharmacotherapyDisplayBO.duringSurgery}"/>
        </td>
        <td class="col-xs-2">
            <c:if test="${isAuthorized}">
                <a href="<c:url value="/patient/${patient.id}/pharmacotherapy/${pharmacotherapyDisplayBO.id}/edit"/>">
                    <span class="glyphicon glyphicon-edit"></span>
                    <spring:message code="label.edit"/>
                </a>
            </c:if>
        </td>
        <td class="col-xs-2">
            <c:if test="${isAuthorized}">
                <a href="#delete-pharmacotherapy-${pharmacotherapyDisplayBO.id}"
                   data-toggle="modal">
                    <span class="glyphicon glyphicon-remove-circle"></span>
                    <spring:message code="label.delete"/>
                </a>
            </c:if>
        </td>
    </tr>
</table>

<div id="collapse-pharmacotherapy-${pharmacotherapyDisplayBO.id}" class="collapse">
    <table class="table">
        <tr>
            <td class="col-xs-4">
                <spring:message code="label.comment"/>
            </td>
            <td colspan="3" class="col-xs-8">
                ${pharmacotherapyDisplayBO.comment}
            </td>
        </tr>
    </table>
</div>

<jsp:include page="../../components/deleteModalComponentView.jsp">
    <jsp:param name="modalId"
               value="delete-pharmacotherapy-${pharmacotherapyDisplayBO.id}"/>
    <jsp:param name="bodyMessage"
               value="reallyDeleteRecord"/>
    <jsp:param name="deleteUrl"
               value="/patient/${patient.id}/pharmacotherapy/${pharmacotherapyDisplayBO.id}/hide"/>
</jsp:include>