
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
												pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="I18n.pagecontent" var="rb"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title><fmt:message key="title" bundle="${rb}"/></title>
    <link href="${pageContext.request.contextPath}/css/Site.css" rel="stylesheet" type="text/css" />
    <script src="http://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="scripts/appoint.js" type="text/javascript"></script>
</head>
<c:import url="header.jsp"></c:import>
<body>
    <form name="appointForm" method="get" action="FrontController">
        <input type="hidden" name="command" value="">
        <input type="hidden" name="billId" value="">
    </form>
    <div class="site-container appointment-page">
        <div class="site-element">
            <div class="site-element-information">
                <table class="request-info-table">
                    <tr><td colspan="2"><fmt:message key="request.info" bundle="${rb}"/></td></tr>
                    <tr>
                        <td><fmt:message key="check.in.date" bundle="${rb}"/></td>
                        <td><fmt:formatDate value="${wrapper.bookingRequest.checkinDt}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="check.out.date" bundle="${rb}"/></td>
                        <td><fmt:formatDate value="${wrapper.bookingRequest.checkoutDt}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="room.rating" bundle="${rb}"/></td>
                        <td><c:out value="${wrapper.bookingRequest.fkRating.ratingName}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="request.status" bundle="${rb}"/></td>
                        <td><c:out value="${wrapper.bookingRequest.fkStatus.description}"/></td>
                    </tr>
                    <tr><td colspan="2"><fmt:message key="user.info" bundle="${rb}"/></td></tr>
                    <tr>
                        <td><fmt:message key="firstname" bundle="${rb}"/></td>
                        <td><c:out value="${wrapper.bookingRequest.fkUser.firstName}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="lastname" bundle="${rb}"/></td>
                        <td><c:out value="${wrapper.bookingRequest.fkUser.lastName}"/></td>
                    </tr>
                </table>
                <input type="button" data-request-id="${wrapper.requestId}" class="action-button bill-btn"
                       value=<fmt:message key="bill" bundle="${rb}"/>>
                <input type="button" data-request-id="${wrapper.requestId}" class="action-button reject-btn"
                       value=<fmt:message key="reject" bundle="${rb}"/>>
            </div>
        </div>
        <div class="site-element">
            <div class="site-element-information">
                <table>
                    <tr>
                        <th><fmt:message key="bed.amount" bundle="${rb}"/></th>
                        <th><fmt:message key="bed.type" bundle="${rb}"/></th>
                        <th><fmt:message key="extra.bed" bundle="${rb}"/></th>
                        <th colspan="2"><fmt:message key="status" bundle="${rb}"/></th>
                    </tr>
                    <c:forEach items="${wrapper.roomRequests}" var="roomrequest">
                    <tr>
                        <td><c:out value="${roomrequest.bedAmt}" /></td>
                        <td><c:out value="${roomrequest.fkBedtype.type}"/></td>
                        <td><span class=<c:out value="${roomrequest.extraBed}" />-icon></span></td>
                        <td><input type ="button" class="appointment-button action-button" value="Approve"
                            data-room-request-id="${roomrequest.id}"/>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                <div class="fe-error field-error error-text-color">
                    <fmt:message key="no.free.rooms" bundle="${rb}"/>
                </div>
            </div>
        </div>
        <c:import url="footer.jsp"></c:import>
    </div>
    <div class="window-background"></div>
        <div class="room-window ajax-window">
            <table class="as-table">
                <thead>
                <tr>
                    <th><fmt:message key="room.number" bundle="${rb}"/></th>
                    <th><fmt:message key="square" bundle="${rb}"/></th>
                    <th><fmt:message key="floor" bundle="${rb}"/></th>
                    <th><fmt:message key="city.view" bundle="${rb}"/></th>
                    <th><fmt:message key="balcony" bundle="${rb}"/></th>
                    <th><fmt:message key="extra.bed" bundle="${rb}"/></th>
                    <th><fmt:message key="bed.type" bundle="${rb}"/></th>
                    <th><fmt:message key="price.bed.type" bundle="${rb}"/></th>
                    <th><fmt:message key="room.rating" bundle="${rb}"/></th>
                    <th><fmt:message key="price.rating" bundle="${rb}"/></th>
                    <th><fmt:message key="price.extra.bed" bundle="${rb}"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    <div class="site-background"></div>
</body></html>

