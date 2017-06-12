
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
												pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="I18n.pagecontent" var="rb"/>
<!DOCTYPE html>
<html>
<head>
	<title><fmt:message key="title" bundle="${rb}"/></title>
	<link href="${pageContext.request.contextPath}/css/Site.css" rel="stylesheet" type="text/css" />
</head>
<c:import url="header.jsp"></c:import>
<body>
	<div class="hotel-login text-color-white">
		<c:if test="${not empty registerSuccess }">
			<fmt:message key="${registerSuccess}" bundle="${rb}" />
		</c:if>

		<form name="loginForm" method="POST" action="FrontController">
		<input type="hidden" name="command" value ="login"/>
		<label for="login"><fmt:message key="login" bundle="${rb}"/>:</label>
		<input type="text" id="login" name="login">
		<label for="password"><fmt:message key="password" bundle="${rb}"/>:</label>
		<input type="password" id="password" name="password">
		<c:if test="${not empty messageError }">
			<div class="login-error">
				<label for="login"><fmt:message key="${messageError}" bundle="${rb}"/></label>
				<%--<fmt:message key="${param.messageError}" bundle="${rb}" />--%>
			</div>
		</c:if>
		<input type ="submit" class="action-button" value=<fmt:message key="enter" bundle="${rb}"/>>
		<c:url var="registration" value="${pageContext.request.contextPath}/jsp/registration.jsp"/>
		<%--<a href='<c:out value="${registration}" />' class="create-account-link" >--%>
			<%--<fmt:message key="register.command" bundle="${rb}" /></a>--%>
			<a class="create-account-link" href="FrontController?command=register"><fmt:message key="register.command" bundle="${rb}" /></a>
		</form>
	</div>

	<div class="site-container login-page">
		<div class="site-element">
			<div class="site-element-information">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ipsum purus, congue vitae cursus vitae, rhoncus at magna. Nulla vitae vulputate dolor. Suspendisse elementum turpis eget nisl ultricies ultricies. Aliquam sit amet nunc tortor. Morbi urna massa, bibendum mattis pharetra eget, rutrum et tellus. Mauris luctus ullamcorper nisi aliquet luctus. Etiam fermentum sed ante sit amet tempus. Sed ac dapibus massa. Cras lectus justo, rhoncus nec tincidunt non, dictum non urna. Nullam euismod tincidunt augue et vulputate. Nam luctus justo ornare nunc scelerisque, sit amet suscipit orci condimentum.</div>
		</div>
		<c:import url="footer.jsp"></c:import>
	</div>
	<div class="site-background"></div>
</body>
</html>

