<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page pageEncoding="UTF-8"%>


<t:menuLVL3>

	<jsp:attribute name="title">
      Neurologické nálezy
    </jsp:attribute>
	<jsp:attribute name="header">
      Neurologické nálezy
    </jsp:attribute>

    <jsp:attribute name="script">
    	
	</jsp:attribute>

	<jsp:body>
		<div>
			<div>
				<div class="span5">
					<h2>Neurologické nálezy</h2>
				</div>
				<div>
					<h3 class="pull-right">
						<a id="export"
					href="<c:url value="/patient/${patient.id}/neurologicalFinding/create" />"><spring:message code="label.addRecord"/></a>
					</h3>
				</div>
			</div>
			<table class="table">
			<tbody>
					<tr>
						<th>Pacient:</th>
						<td>${patient.contact.firstName}</td>

						<th>Rodné číslo:</th>
						<td>${patient.nin}</td>

						<th>Datum narození:</th>
						<td>${patient.birthday}</td>
							
					</tr>
					<tr>	
						<th>Adresa:</th>
						<td>${patient.contact.addressStreet}</td>
							
						<th>Telefon:</th>
						<td>${patient.contact.phoneNumber}</td>
							
						<th>Email:</th>
						<td>${patient.contact.email}</td>
												
							
					</tr>
					<tr>
						<th>Pohaví:</th>
						<td>${patient.gender}</td>
							
						<th>Věk při začátku epilepsie:</th>
						<td></td>
							
						<th>Ošetřující lékař:</th>
						<td></td>
							
					</tr>
				</tbody>
			</table>
 
			<c:if test="${empty patient.neurologicalFindingList}">
 				<div class="alert alert-block">
		  			<button type="button" class="close" data-dismiss="alert">&times;</button>
		  			<h4>Žádné záznamy!</h4>
				</div>
 			</c:if>

			<!-- Anamnesis list START -->
			<div class="accordion">
				<c:forEach items="${patient.neurologicalFindingList}" var="neurologicalFinding">
					<div >
						<div class="accordion-heading">
					    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse${neurologicalFinding.id}">
					    	    <strong>Vyšetření dne:</strong> ${neurologicalFinding.date}
					    	</a>
						</div>

					    <div id="collapse${neurologicalFinding.id}" class="accordion-body collapse">

					      	<div class="accordion-inner">
						      	<a class="close" href="<c:url value="/patient/${patientID}/neurologicalFinding/${neurologicalFinding.id}/delete"/>">×</a>
								<table class="table">
				               		<tbody>
										<tr class="info">
											<td>Dominance hemisféry</td>
											<c:if test="${neurologicalFinding.hemisphereDominanceIdcom==1}">
												<td>Ambidexter</td>
											</c:if>
											<c:if test="${neurologicalFinding.hemisphereDominanceIdcom==2}">
												<td>Levák</td>
											</c:if>
											<c:if test="${neurologicalFinding.hemisphereDominanceIdcom==3}">
												<td>Nespecifikováno</td>
											</c:if>
											<c:if test="${neurologicalFinding.hemisphereDominanceIdcom==4}">
												<td>Pravák</td>
											</c:if>
										</tr>
										<tr class="info">
											<td>Abnormální neurologický nález</td>
											<c:if test="${neurologicalFinding.abnormalNeurologicalFinding==true}">
												<td style="column-span: 2"><spring:message code="label.yes"/></td>
											</c:if>
											<c:if test="${neurologicalFinding.abnormalNeurologicalFinding==false}">
												<td><spring:message code="label.no"/></td>
											</c:if>
										</tr>
										<tr class="info">
											<td>Hemiparéza</td>
											<c:if test="${neurologicalFinding.hemiparesis==true}">
												<td style="column-span: 2"><spring:message code="label.yes"/></td>
											</c:if>
											<c:if test="${neurologicalFinding.hemiparesis==false}">
												<td><spring:message code="label.no"/></td>
											</c:if>
										</tr>
										<tr class="info">
											<td>Defekt zorného pole</td>
											<c:if test="${neurologicalFinding.visualCut==true}">
												<td style="column-span: 2"><spring:message code="label.yes"/></td>
											</c:if>
											<c:if test="${neurologicalFinding.visualCut==false}">
												<td><spring:message code="label.no"/></td>
											</c:if>
										</tr>
										<tr class="info">
											<td><spring:message code="label.comment" /></td>
											<c:choose>
												<c:when test="${empty neurologicalFinding.comment}">
													<td>Žádný</td>
												</c:when>
												<c:otherwise>
													<td>${neurologicalFinding.comment}</td>
												</c:otherwise>
											</c:choose>
										</tr>
										
				              		</tbody>
	            				</table>
		            		</div>
					    </div>
	            	</div>
	            </c:forEach>
            </div>
        </br> 
	</jsp:body>
</t:menuLVL3>


