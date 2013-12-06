<%@ tag description="menu LVL1" pageEncoding="UTF-8"%>

<%-- Taglib section --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>

<%-- Attribute section --%>
<%@ attribute name="title" fragment="true"%>
<%@ attribute name="head" fragment="true"%>
<%@ attribute name="menuLVL2" fragment="true"%>
<%@ attribute name="script" fragment="true"%>

<%-- Template section --%>
<t:layout.NEW303>


	<jsp:attribute name="title">
	<%-- Hook for filling title of page --%>
	<jsp:invoke fragment="title" />
	</jsp:attribute>


	<jsp:attribute name="head">
	<%-- Hook for adding something to HEAD --%>
	<jsp:invoke fragment="head" />
	</jsp:attribute>

	<jsp:attribute name="menuLVL1">
	<%-- Menu hook --%>
	<jsp:invoke fragment="menuLVL2" />
				<div class="panel panel-default">
					<div class="panel-heading">Pacienti</div>
					<div class="panel-body">
						<ul class="nav">
							<li><a href="<c:url value="/patient/list"/>">Kartotéka</a></li>
							<li><a href="<c:url value="/advanced-search"/>">Pokročilé vyhledávání</a></li>
						</ul>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">Administrace</div>
					<div class="panel-body">
						<ul class="nav">
							<li><a href="<c:url value="/user/list"/>">Správa uživatelů</a></li>						
						</ul>
					</div>
				</div>
	</jsp:attribute>


	<jsp:attribute name="script">
	<%-- Hook for adding something to Script section --%>
	<jsp:invoke fragment="script" />
	</jsp:attribute>


	<jsp:body>
		<%-- Hook for body --%>
        <jsp:doBody />
    </jsp:body>

</t:layout.NEW303>
