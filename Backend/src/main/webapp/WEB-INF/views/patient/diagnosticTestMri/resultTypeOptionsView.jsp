<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<div class="form-group">
    <label class="col-xs-4 control-label"
           for="${param.propertyName}">
        <spring:message code="label.${param.labelName}"/>
    </label>

    <div class="col-xs-8">
        <form:select id="${param.propertyName}"
                     class="input-sm form-control"
                     type="text"
                     path="${param.propertyName}">
            <form:option value="0">
                <spring:message code="label.resultType.0"/>
            </form:option>
            <form:option value="1">
                <spring:message code="label.resultType.1"/>
            </form:option>
            <form:option value="2">
                <spring:message code="label.resultType.2"/>
            </form:option>
            <form:option value="3">
                <spring:message code="label.resultType.3"/>
            </form:option>
            <form:option value="4">
                <spring:message code="label.resultType.4"/>
            </form:option>
            <form:option value="5">
                <spring:message code="label.resultType.5"/>
            </form:option>
            <form:option value="6">
                <spring:message code="label.resultType.6"/>
            </form:option>
            <form:option value="7">
                <spring:message code="label.resultType.7"/>
            </form:option>
            <form:option value="8">
                <spring:message code="label.resultType.8"/>
            </form:option>
        </form:select>
    </div>
</div>