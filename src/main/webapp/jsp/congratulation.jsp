
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

	<div class="hotel-top-menu background-color-main">
		<span class="logo"></span>
		<span class="page-name">Four Points by Benetton</span>
	</div>
	<div class="site-container">
		<div class="site-element">
			<div class="site-element-information">Congratulations</div>
		</div>
	</div>
	<div class="site-background"></div>
</form>
<hr/>
</body>
</html>

