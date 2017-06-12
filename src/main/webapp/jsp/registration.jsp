
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
												pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="I18n.pagecontent" var="rb" />
<!DOCTYPE html>
<html>
<head>
	<title>Registation</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/register.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/Site.css" rel="stylesheet" type="text/css" />
</head>
<c:import url="header.jsp"></c:import>
<body>
<div class="site-container">
<div class="registration-container">
<form name="registrationForm" method="POST" action="FrontController">
	<input type="hidden" name="command" value ="registration"/>


		<div class="registration-field-name"><fmt:message key="firstname" bundle="${rb}"/></div>
		<input type="text" name="firstname" value ="${firstname}" required>
		<c:if test="${not empty invalid_firstname }">
			<div class="field-error error-text-color ">
				<fmt:message key="${invalid_firstname}"  bundle="${rb}"/>
			</div>
		</c:if>
		<div class="registration-field-name"><fmt:message key="lastname" bundle="${rb}"/></div>
		<input type="text" name="lastname" value ="${lastname}" required>
		<c:if test="${not empty invalid_lastname }">
			<div class="field-error error-text-color ">
				<fmt:message key="${invalid_lastname}"  bundle="${rb}"/>
			</div>
		</c:if>

		<div class="registration-field-name"><fmt:message key="login" bundle="${rb}"/></div>
		<input type="text" name="login" value ="${login}" required>
		<c:if test="${not empty invalid_login }">
			<div class="field-error error-text-color ">
				<fmt:message key="${invalid_login}"  bundle="${rb}"/>
			</div>
		</c:if>

		<div class="registration-field-name"><fmt:message key="password" bundle="${rb}"/></div>
		<input type="password" name="password" value ="" required>
		<c:if test="${not empty invalid_password }">
			<div class="field-error error-text-color ">
				<fmt:message key="${invalid_password}"  bundle="${rb}"/>
			</div>
		</c:if>
		<div class="registration-field-name"><fmt:message key="check_password" bundle="${rb}"/></div>
		<input type="password" name="checkedpassword" value ="" required>
		<div class="fe-error field-error error-text-color"><fmt:message key="invalid.checkpassword"  bundle="${rb}"/></div>

		<div class="registration-field-name"><fmt:message key="birth_date" bundle="${rb}"/></div>
		<input type="text" name="birthdate" value ="${birthdate}" placeholder="yyyy-mm-dd">
		<div class="fe-error field-error error-text-color"><fmt:message key="invalid.birthdate"  bundle="${rb}"/></div>
		<c:if test="${not empty invalid_birthdate }">
			<div class="field-error error-text-color ">
				<fmt:message key="${invalid_birthdate}"  bundle="${rb}"/>
			</div>
		</c:if>
		<div class="registration-field-name"><fmt:message key="city" bundle="${rb}"/></div>
		<input type="text" name="city" value ="${city}">
		<c:if test="${not empty invalid_city }">
			<div class="field-error error-text-color ">
				<fmt:message key="${invalid_city}"  bundle="${rb}"/>
			</div>
		</c:if>
		<div class="registration-field-name"><fmt:message key="mobile_number" bundle="${rb}"/></div>
		<input type="text" name="mobilenumber" placeholder="38-012-345-6789"
			   pattern="\d{2}-\d{3}-\d{3}-\d{4}" value ="${mobilenumber}" required>
		<c:if test="${not empty invalid_mobilenumber }">
			<div class="field-error error-text-color ">
				<fmt:message key="${invalid_mobilenumber}"  bundle="${rb}"/>
			</div>
		</c:if>
		<div class="registration-field-name"><fmt:message key="email" bundle="${rb}"/></div>
		<input type="email" name="email" value ="${email}" required>
		<c:if test="${not empty invalid_email }">
			<div class="field-error error-text-color ">
				<fmt:message key="${invalid_email}"  bundle="${rb}"/>
			</div>
		</c:if>
		<div class="registration-field-name"><fmt:message key="street" bundle="${rb}"/></div>
		<input type="text" name="street" value ="${street}" >
		<c:if test="${not empty invalid_street }">
			<div class="field-error error-text-color ">
				<fmt:message key="${invalid_street}"  bundle="${rb}"/>
			</div>
		</c:if>
		<div class="registration-field-name"><fmt:message key="house" bundle="${rb}"/></div>
		<input type="text" name="house" value ="${house}" >
		<c:if test="${not empty invalid_house }">
			<div class="field-error error-text-color ">
				<fmt:message key="${invalid_house}"  bundle="${rb}"/>
			</div>
		</c:if>
		<div class="registration-field-name"><fmt:message key="appartment" bundle="${rb}"/></div>
		<input type="text" name="appartment" value ="${appartment}" >
		<c:if test="${not empty invalid_appartment }">
			<div class="field-error error-text-color ">
				<fmt:message key="${invalid_appartment}"  bundle="${rb}"/>
			</div>
		</c:if>
		<c:if test="${not empty emailNonUnique }">
			<div class="field-error error-text-color ">
				<fmt:message key="${emailNonUnique}"  bundle="${rb}"/>
			</div>
		</c:if>
		<c:if test="${not empty loginNonUnique }">
			<div class="field-error error-text-color ">
				<fmt:message key="${loginNonUnique}"  bundle="${rb}"/>
			</div>
		</c:if>
		<input type ="submit" class="action-button" value=<fmt:message key="register.command" bundle="${rb}"/>>
	</form>
    </div>
	<c:import url="footer.jsp"></c:import>
</div>
<div class="site-background"></div>
</body>
</html>