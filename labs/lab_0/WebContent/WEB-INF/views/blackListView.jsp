<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Black List</title>
 </head>
 <body>
 
    <jsp:include page="header_.jsp"></jsp:include>
    <jsp:include page="admin_menu.jsp"></jsp:include>
 	
 	<c:if test="${not empty requestScope.userList}">
    <h3>${Bundle.getObject("users")}</h3>
    <table border="1" cellpadding="5" cellspacing="1" >
       <c:forEach items="${requestScope.userList}" var="userInfo" >
   		  <tr>
   		  	 <th style="color:red;">${Bundle.getObject("phone")}</th>
          	 <th style="color:red;">${Bundle.getObject("firstName")}</th>
          	 <th style="color:red;">${Bundle.getObject("lastName")}</th>
          	 <th style="color:red;">${Bundle.getObject("amountUnO")}</th>
          	 <th style="color:red;">${Bundle.getObject("sumUnO")}</th>
          	 <th style="color:green;">${Bundle.getObject("removeBlacklist")}</th>
       	  </tr>
          <tr>
             <td>${userInfo.user.phone}</td>
             <td>${userInfo.user.firstName}</td>
             <td>${userInfo.user.lastName}</td>
             <td>${userInfo.amount}</td>
             <td>${userInfo.sum}</td>
             <td><a href="setBlackList?phone=${userInfo.user.phone}&add=false">${Bundle.getObject("removeBlacklist")}</a></td>
          </tr>
       </c:forEach>
    </table>
    </c:if>
    <c:if test="${empty requestScope.userList}">
    	<h3>${Bundle.getObject("emptyUsers")}</h3>
    </c:if>
 </body>
</html>