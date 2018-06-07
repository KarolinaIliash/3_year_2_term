<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
 </head>
 <body>
 	<jsp:include page="header_.jsp"></jsp:include>
    <jsp:include page="admin_menu.jsp"></jsp:include>
 
 	<p><b>${Bundle.getObject("message")}${message}</b></p>
 </body>
</html>