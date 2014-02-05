<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page pageEncoding="UTF-8" %>

<t:menuLVL2.NEW303>

<jsp:attribute name="head">
      <link href="<c:url value="/resources/jquery-ui-datepicker/jquery-ui.min.css" />" rel="stylesheet">
     <link href="<c:url value="/resources/custom/css/custom.NEW303.css" />" rel="stylesheet">
    </jsp:attribute>

	<jsp:attribute name="title">
      <spring:message code="label.addRecord"/>
    </jsp:attribute>

	<jsp:attribute name="script">
		<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
		<script src="<c:url value="/resources/custom/js/datepicker.NEW303.js" />"></script>
        <script src="<c:url value="/resources/custom/js/cardForm/customjs.js" />"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-xs-12">
                <h2>
                    <spring:message code="label.invasiveTestsCorticalMapping"/>
                </h2>
            </div>
        </div>

        <%@include file="../patientDetails.jsp" %>

        <%-- mapping resource in action with c:url caused errors --%>
        <form:form class="form-horizontal" role="form" method="POST"
                   action="/GENEPI/patient/${patient.id}/invasive-test-cortical-mapping/save"
                   commandName="invasiveTestCorticalMapping">

            <div class="form-group">
                <label for="date" class="col-xs-3 control-label">
                    <spring:message code="label.dateExamination"/>
                </label>

                <div class="col-xs-8">
                    <form:input path="date" id="date" type="text" class="form-control datepicker-today"
                                autocomplete="off"/>
                    <form:errors path="date" cssClass="error">
                    </form:errors>
                </div>
            </div>

            <div class="form-group">
                <label for="done" class="col-xs-3 control-label">
                    <spring:message code="label.corticalMapping"/>
                </label>

                <div class="col-xs-8">
                    <form:select path="done" id="done" type="text" class="form-control">
                        <form:option value="1">
                            <spring:message code="label.done.1"/>
                        </form:option>
                        <form:option value="2">
                            <spring:message code="label.done.2"/>
                        </form:option>
                    </form:select>
                </div>
            </div>

            <div id="section-done" class="section-hide">

                <div class="form-group">
                    <label for="corticalMapping" class="col-xs-3 control-label">
                        <spring:message code="label.corticalMapping"/>
                    </label>

                    <div class="col-xs-8">
                        <form:select path="corticalMapping" id="corticalMapping" type="text" class="form-control">
                            <form:option value="0">
                                <spring:message code="label.corticalMapping.0"/>
                            </form:option>
                            <form:option value="1">
                                <spring:message code="label.corticalMapping.1"/>
                            </form:option>
                            <form:option value="2">
                                <spring:message code="label.corticalMapping.2"/>
                            </form:option>
                            <form:option value="3">
                                <spring:message code="label.corticalMapping.3"/>
                            </form:option>
                            <form:option value="4">
                                <spring:message code="label.corticalMapping.4"/>
                            </form:option>
                            <form:option value="5">
                                <spring:message code="label.corticalMapping.5"/>
                            </form:option>
                            <form:option value="6">
                                <spring:message code="label.corticalMapping.6"/>
                            </form:option>
                        </form:select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="comment" class="col-xs-3 control-label">
                        <spring:message code="label.comment"/>
                    </label>

                    <div class="col-xs-8">
                        <form:textarea path="comment" id="comment" class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-8">
                    <button class="btn btn-primary" type="submit">
                        <spring:message code="label.add"/>
                    </button>
                </div>
            </div>
        </form:form>

    </jsp:body>
</t:menuLVL2.NEW303>