
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="I18n.pagecontent" var = "rb"/>
<fmt:setLocale value="en_US" scope="request"/>
    <html><head>
        <title><fmt:message key="title" bundle="${rb}"/></title>
    </head>
    <body>
    ${user.firstName}, <fmt:message key="welcome"  bundle="${rb}"/>
    <br/>
    <%--<c:set var="userType" value="${userType}" scope="request"/>--%>
    <c:if test="${isAdmin == false}">
        <fmt:message key="booking.book"  bundle="${rb}"/>
        <form name="mainForm" method="POST" action="FrontController">
        <input type="hidden" name="command" value ="main"/>
            <fmt:message key="booking.check.in.date" bundle="${rb}"/><br/>
            <input type="text" name="checkin" value =""> <br/>
            <fmt:message key="booking.check.out.date" bundle="${rb}"/><br/>
            <input type="text" name="checkout" value =""> <br/>
            <fmt:message key="booking.bed.amount" bundle="${rb}"/><br/>
            <input type="text" name="beds" value =""> <br/>
            <fmt:message key="${exception.messageWithCause}"  bundle="${rb}"/>
            <br/>

            <input type ="submit" value=<fmt:message key="enter" bundle="${rb}"/>>
        </form>
    </c:if>

    <c:if test="${isAdmin == true}">
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
                    <td><a href="FrontController?command=appoint?requestId=${brequest.requestId}">Appoint</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
        <hr/>
        <a href="FrontController?command=logout">Logout</a>

    <fmt:message key="footer"  bundle="${rb}"/>
    </body></html>

