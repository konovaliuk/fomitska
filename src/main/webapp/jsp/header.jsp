<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="${language}">
<fmt:setBundle basename="I18n.pagecontent" var="rb"/>
	<div class="hotel-top-menu background-color-main">
		<div class="logo"></div>
		<span>
		<form name="langForm" method="POST">
			<select class="background-color-main" id="language" name="language" onchange="submit()">
				<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
				<option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
			</select>
		</form>
		</span>
		<span class="page-name"><fmt:message key="title" bundle="${rb}"/></span>
		<c:if test="${isUserAuthorized == true}">
			<a class="logout" href="FrontController?command=logout"><fmt:message key="logout" bundle="${rb}"/></a>
		</c:if>
	</div>



