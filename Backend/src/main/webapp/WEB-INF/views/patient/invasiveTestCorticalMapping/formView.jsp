<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:useBean id="dateBeforeBirth"
             scope="request"
             type="java.lang.Boolean"/>

<div class="form-group">
    <label class="col-xs-4 control-label"
           for="date">
        <spring:message code="label.dateExamination"/>*
    </label>

    <div class="col-xs-8">
        <div class='input-group date datepicker-simple'>
            <form:input id="date"
                        class="input-sm form-control"
                        type="text"
                        autocomplete="off"
                        path="date"/>
            <span class="input-group-addon">
                <span class="glyphicon glyphicon-calendar"></span>
            </span>
        </div>
        <form:errors path="date"
                     cssClass="text-danger"/>
        <c:if test="${dateBeforeBirth}">
            <span id="date.errors"
                  class="text-danger">
                <spring:message code="label.cannotBeBeforeBirth"/>
            </span>
        </c:if>
    </div>
</div>

<div class="form-group">
    <label class="col-xs-4 control-label"
           for="done">
        <spring:message code="label.corticalMapping"/>
    </label>

    <div class="col-xs-8">
        <form:select id="done"
                     class="input-sm form-control"
                     type="text"
                     path="done">
            <form:option value="1">
                <spring:message code="label.done.1"/>
            </form:option>
            <form:option value="2">
                <spring:message code="label.done.2"/>
            </form:option>
        </form:select>
    </div>
</div>

<div id="section-done"
     class="section-hide">

    <div class="form-group">
        <label class="col-xs-4 control-label"
               for="corticalMapping">
            <spring:message code="label.corticalMapping"/>
        </label>

        <div class="col-xs-8">
            <form:select id="corticalMapping"
                         class="input-sm form-control"
                         type="text"
                         path="corticalMapping">
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
        <label class="col-xs-4 control-label"
               for="comment">
            <spring:message code="label.comment"/>
        </label>

        <div class="col-xs-8">
            <form:textarea id="comment"
                           class="form-control resize-vertical"
                           path="comment"/>
        </div>
    </div>
</div>