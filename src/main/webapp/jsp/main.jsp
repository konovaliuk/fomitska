
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "pag" uri = "/WEB-INF/paginator.tld"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
												pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="I18n.pagecontent" var="rb"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title" bundle="${rb}"/></title>
    <link href="${pageContext.request.contextPath}/css/Site.css" rel="stylesheet" type="text/css" />
	<c:if test="${sessionScope.isAdmin == false}">
		<script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/css/jquery-ui.min.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/css/jquery-ui.theme.css" rel="stylesheet" type="text/css">
		<script src="${pageContext.request.contextPath}/scripts/booking.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/jquery-ui.min.js" type="text/javascript"></script>
	</c:if>
</head>
<c:import url="header.jsp"></c:import>
<body>
<div class="site-container booking-page">
    <div class="site-element">
        <div class="site-element-information">
			${user.firstName}, <fmt:message key="welcome"  bundle="${rb}"/>
        </div>
    </div>
    <div class="site-element">
        <div class="site-element-information">
			<c:if test="${sessionScope.isAdmin eq false}">
				<fmt:message key="booking.book"  bundle="${rb}"/>
				<form name="mainForm" method="POST" action="FrontController">
				<input type="hidden" name="command" value ="booking"/>
					<div class="booking-field"><span><fmt:message key="check.in.date" bundle="${rb}"/></span>
					<input type="text" name="checkin" value =""></div>
					<div class="booking-field"><span><fmt:message key="check.out.date" bundle="${rb}"/></span>
					<input type="text" name="checkout" value =""></div>
					<div class="booking-field"><span><fmt:message key="room.rating" bundle="${rb}"/></span>
					<select name="roomRating">
						<c:forEach items="${roomRatings}" var="roomRating">
							<option value="${roomRating.id}"> <c:out value="${roomRating.ratingName} ${roomRating.price}+" /></option>
						</c:forEach>
					</select>
					</div>

					<input type="hidden" name="roomrequestAmt" value ="1"/>
					<div class="room-request-list">
						<div class="room-request">
						   <span><fmt:message key="bed.amount" bundle="${rb}"/>
						   <input type="text" name = "bedAmt1" value=""/></span>
						   <span><fmt:message key="bed.type" bundle="${rb}"/>
						   <select name="bedType1">
							   <c:forEach items="${bedTypes}" var="bedtype">
								   <option value="${bedtype.id}"><c:out value="${bedtype.type}" /></option>
							   </c:forEach>
						   </select></span>
						   <span><fmt:message key="extra.bed" bundle="${rb}"/>
						   <input type="checkbox" name="extraBed1" value="false"></span>
						</div>
				   </div>
					<input type="button" value="Add" class="action-button add-room-button">
					<div class="fe-error error-text-color field-error">Maximum number of rooms per request is 10</div>

					<input type ="submit" class="action-button" value=<fmt:message key="enter" bundle="${rb}"/>>
				</form>
				<c:if test="${not empty exceptionMessage }">
					<div class="field-error error-text-color ">
						<fmt:message key="${exceptionMessage}"  bundle="${rb}"/>
					</div>
				</c:if>
				<c:if test="${not empty requestSuccess }">
					<fmt:message key="${requestSuccess}" bundle="${rb}" />
				</c:if>


			</c:if>

			<c:if test="${sessionScope.isAdmin eq true}">
				<c:url var="searchUri" value="/FrontController?command=getrequests&page=##" />

				<table>
					<tr>
						<th>Check in</th>
						<th>Check out</th>
						<th>Room Rating</th>
						<th>Status</th>
						<th>Amount of rooms</th>
						<th>Appointment</th>
					</tr>
					<c:forEach items="${requests}" var="brequest">
						<tr>
							<td><fmt:formatDate value="${brequest.bookingRequest.checkinDt}" pattern="yyyy-MM-dd" /></td>
							<td><fmt:formatDate value="${brequest.bookingRequest.checkoutDt}" pattern="yyyy-MM-dd" /></td>
							<td><c:out value="${brequest.bookingRequest.fkRating.ratingName}" /></td>
							<td><c:out value="${brequest.bookingRequest.fkStatus.description}" /></td>
							<td>${fn:length(brequest.roomRequests)}</td>
							<td>${brequest.requestId}</td>
							<c:url var="url" value="FrontController?command=appoint&requestId=${brequest.requestId}" >
							</c:url>
							<td><a href="${url}">Appoint</a></td>
						</tr>
					</c:forEach>
				</table>
				<pag:paginator maxLinks="10" currentPage="${page}" totalPages="${totalPages}" uri="${searchUri}" />
			</c:if>
        </div>
    </div>
    <c:import url="footer.jsp"></c:import>
</div>
<div class="site-background"></div>
</body></html>


