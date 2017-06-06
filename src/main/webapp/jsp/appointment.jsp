
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="I18n.pagecontent" var = "rb"/>
<fmt:setLocale value="en_US" scope="request"/>
    <html><head>
        <title><fmt:message key="title" bundle="${rb}"/></title>
    </head>
    <body>
        <table>
            <tr>
                <th>Check in</th>
                <th>Check out</th>
                <th>Room Rating</th>
                <th>Status</th>
                <th>Amount of rooms</th>
                <th>Appointment</th>
            </tr>
                <c:out value="${bookingrequest.bookingRequest.fkUser.lastName}"/>

            <c:forEach items="${rooms}" var="room">
                <tr>
                    <td>${room.roomNumber}</td>
                    <%--<td><c:out value="${brequest.bookingRequest.fkRating.ratingName}" /></td>--%>
                    <%--<td><c:out value="${brequest.bookingRequest.fkStatus.description}" /></td>--%>
                    <%--<td><a href="FrontController?command=appoint&requestId=${brequest.requestId}">Appoint</a></td>--%>
                </tr>
            </c:forEach>
        </table>
   
        <a href="FrontController?command=logout">Logout</a>

    <fmt:message key="footer"  bundle="${rb}"/>
    </body></html>

