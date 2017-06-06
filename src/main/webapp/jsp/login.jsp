
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="I18n.pagecontent" var = "rb" />
<fmt:setLocale value="en_US"  scope="request"/>
<html>
    <head>
    <title><fmt:message key="title" bundle="${rb}"/></title>
	<link href="${pageContext.request.contextPath}/css/Site.css" rel="stylesheet" type="text/css" />
    </head>
<body>
<form name="loginForm" method="POST" action="FrontController">

	<div class="hotel-top-menu background-color-main">
		<span class="logo"></span>
		<span class="page-name">Four Points by Benetton</span>
	</div>
	<div class="hotel-login text-color-white">
		<input type="hidden" name="command" value ="login"/>
		<span><fmt:message key="login" bundle="${rb}"/></span>
		<input type="text" name="login" value ="">
		<span><fmt:message key="password" bundle="${rb}"/></span>
		<input type="password" name="password" value="">
		
		<jsp:useBean id="exception" class="ua.training.exception.FailedOperationException" scope="request"/>
			<c:if test="${not empty exception and not empty exception.messageWithCause}">
				<div class="login-error">
					<fmt:message key="${exception.messageWithCause}"  bundle="${rb}"/>
				</div>
			</c:if>

		<input type ="submit" class="action-button" value=<fmt:message key="enter" bundle="${rb}"/>>
		
		<c:url value="${pageContext.request.contextPath}/jsp/registration.jsp" var="registration"/>
		<a href='<c:out value="${ registration}" />' class="create-account-link" >Create account</a>
	</div>
	<div class="site-container">
		<div class="site-element">
			<div class="site-element-information">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ipsum purus, congue vitae cursus vitae, rhoncus at magna. Nulla vitae vulputate dolor. Suspendisse elementum turpis eget nisl ultricies ultricies. Aliquam sit amet nunc tortor. Morbi urna massa, bibendum mattis pharetra eget, rutrum et tellus. Mauris luctus ullamcorper nisi aliquet luctus. Etiam fermentum sed ante sit amet tempus. Sed ac dapibus massa. Cras lectus justo, rhoncus nec tincidunt non, dictum non urna. Nullam euismod tincidunt augue et vulputate. Nam luctus justo ornare nunc scelerisque, sit amet suscipit orci condimentum.</div>
		</div>
	</div>
	<div class="site-background"></div>
</form>
<hr/>
</body>
</html>

