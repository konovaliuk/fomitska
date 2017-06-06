
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--<fmt:setLocale value="${param.locale}" scope="session"/>--%>
<html><head>
    <title>Registation</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/register.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/Site.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="hotel-top-menu background-color-main">
		<span class="logo"></span>
		<span class="page-name">Registration</span>
	</div>
	<fmt:setBundle basename="I18n.pagecontent" var = "rb" />
	<fmt:setLocale value="uk_UA"  scope="request"/>
	<fmt:message key="register.newaccount" bundle="${rb}"/>
<form name="registrationForm" method="POST" action="FrontController">
	<input type="hidden" name="command" value ="registration"/>
	<div class="registration-container">
		
		<div class="registration-field-name"><fmt:message key="register.firstname" bundle="${rb}"/></div>
		<input type="text" name="firstname" value ="" required> 
		<div class="field-error error-text-color unvisible">Error</div>
		
		<div class="registration-field-name"><fmt:message key="register.last_name" bundle="${rb}"/></div>
		<input type="text" name="lastname" value ="" required> 
		<div class="field-error error-text-color unvisible">Error</div>
			
		<div class="registration-field-name"><fmt:message key="login" bundle="${rb}"/></div>
		<input type="text" name="login" value ="" required> 
		<div class="field-error error-text-color unvisible">Error</div>
		
		<div class="registration-field-name"><fmt:message key="password" bundle="${rb}"/></div>
		<input type="password" name="password" value ="" required>
		<div class="field-error error-text-color unvisible">Error</div>
		
		<div class="registration-field-name"><fmt:message key="register.check_password" bundle="${rb}"/></div>
		<input type="password" name="checkedpassword" value ="" required>
		<div class="field-error error-text-color unvisible">Error</div>
		
		<div class="registration-field-name"><fmt:message key="register.birth_date" bundle="${rb}"/></div>
		<input type="text" name="birthdate" value ="" placeholder="yyyy-mm-dd">
		<div class="field-error error-text-color unvisible">Error</div>
		
		<div class="registration-field-name"><fmt:message key="register.city" bundle="${rb}"/></div>
		<input type="text" name="city" value ="" required> 
		<div class="field-error error-text-color unvisible">Error</div>
		
		<div class="registration-field-name"><fmt:message key="register.mobile_number" bundle="${rb}"/></div>
		<input type="text" name="mobilenumber" placeholder="+38-012-345-6789"
			   pattern="\+\d{2}-\d{3}-\d{3}-\d{4}" value ="" required> 
		<div class="field-error error-text-color unvisible">Error</div>
			   
		<div class="registration-field-name"><fmt:message key="register.email" bundle="${rb}"/></div>
		<input type="email" name="email" value ="" required>
		<div class="field-error error-text-color unvisible">Error</div>
		
		<div class="registration-field-name"><fmt:message key="register.street" bundle="${rb}"/></div>
		<input type="text" name="street" value ="" >
		<div class="field-error error-text-color unvisible">Error</div>
		
		<div class="registration-field-name"><fmt:message key="register.house" bundle="${rb}"/></div>
		<input type="text" name="house" value ="" >
		<div class="field-error error-text-color unvisible">Error</div>
		
		<div class="registration-field-name"><fmt:message key="register.appartment" bundle="${rb}"/></div>
		<input type="text" name="appartment" value ="" >
		<div class="field-error error-text-color unvisible">Error</div>
		
		<input type ="submit" class="action-button" value=<fmt:message key="register.command" bundle="${rb}"/>>
		<div class="error-text-color">
			<jsp:useBean id="exception" class="ua.training.exception.FailedOperationException" scope="request"/>
			<c:if test="${not empty exception and not empty exception.messageWithCause}">
				<fmt:message key="${exception.messageWithCause}"  bundle="${rb}"/>
			</c:if>
			<fmt:message key="${exception.messageWithCause}"  bundle="${rb}"/>
			${errorLoginPassMessage}

			${wrongAction}

			${nullPage}

			<input type ="submit" value="Enter">
			<c:out value="Welcome to JSTL"/>
		</div>
	</div>
</form>

	<div class="site-background"></div>
</body>
</html>