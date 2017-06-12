<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="I18n.pagecontent" var="rb" />
<html>
    <body>
        <div class="footer">
            <div class="footer-text"><fmt:message key="footer"  bundle="${rb}"/></div>
        </div>
    </body>
</html>
