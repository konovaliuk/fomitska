
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
</head>
<c:import url="header.jsp"></c:import>
<body>
    <div class="site-container appointment-page">
        <div class="site-element">
            <div class="site-element-information">
                <div><fmt:message key="title" bundle="${rb}"/></div>
                <div><fmt:message key="hotel.address" bundle="${rb}"/></div>
                <div><fmt:message key="hotel.city" bundle="${rb}"/></div>
                <div><fmt:message key="hotel.tel" bundle="${rb}"/></div>
                <div><fmt:message key="hotel.website" bundle="${rb}"/></div>

                <div><fmt:message key="bill.title" bundle="${rb}"/></div>

                <br/>
                <div><fmt:message key="customer.info" bundle="${rb}"/></div>
                <div><fmt:message key="firstname" bundle="${rb}"/>: <c:out value="${bill.fkUser.firstName}"/></div>
                <div><fmt:message key="lastname" bundle="${rb}"/>: <c:out value="${bill.fkUser.lastName}"/></div>
                <div><fmt:message key="check.in.date" bundle="${rb}"/>: <fmt:formatDate value="${bill.fkBookingrequest.checkinDt}" pattern="yyyy-MM-dd"/></div>
                <div><fmt:message key="check.out.date" bundle="${rb}"/>: <fmt:formatDate value="${bill.fkBookingrequest.checkoutDt}" pattern="yyyy-MM-dd"/></div>
                <table >
                    <tr>
                        <th><fmt:message key="subtotal" bundle="${rb}"/></th>
                        <th><fmt:message key="discount" bundle="${rb}"/></th>
                        <th><fmt:message key="total" bundle="${rb}"/></th>
                    </tr>
                    <tr>
                        <td><fmt:formatNumber type = "number"
                                              maxFractionDigits  = "2" value = "${bill.subtotal}"/></td>
                        <td><c:out value="${bill.discountPercentage}"/></td>
                        <td><fmt:formatNumber type = "number"
                                              maxFractionDigits  = "2" value = "${total}" /></td>
                    </tr>
                </table>
                <fmt:formatDate value="${bill.creationDt}" pattern="yyyy-MM-dd"/>
            </div>
        </div>
        <div class="footer">
            <div class="footer-text">
            <fmt:message key="footer"  bundle="${rb}"/>
            </div>
        </div>
    </div>
    <div class="site-background"></div>
</body></html>

