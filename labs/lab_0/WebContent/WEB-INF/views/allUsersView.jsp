<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Order List</title>
 </head>
 <body>
 
    <jsp:include page="header_.jsp"></jsp:include>
    <jsp:include page="admin_menu.jsp"></jsp:include>
 	
 	<c:if test="${not empty requestScope.userList}">
    <h3>${Bundle.getObject("users")}</h3>
    <table border="1" cellpadding="5" cellspacing="1" >
       <c:forEach items="${requestScope.userList}" var="userInfo" >
   		  <tr>
   		  	 <th style="color:green;">${Bundle.getObject("phone")}</th>
          	 <th style="color:green;">${Bundle.getObject("firstName")}</th>
          	 <th style="color:green;">${Bundle.getObject("lastName")}</th>
          	 <th style="color:green;">${Bundle.getObject("amountUnO")}</th>
          	 <th style="color:green;">${Bundle.getObject("sumUnO")}</th>
          	 <th style="color:green;">${Bundle.getObject("inBlacklist")}</th>
       	  </tr>
          <tr>
             <td>${userInfo.user.phone}</td>
             <td>${userInfo.user.firstName}</td>
             <td>${userInfo.user.lastName}</td>
             <td>${userInfo.amount}</td>
             <td>${userInfo.sum}</td>
             <c:if test="${userInfo.user.inBlackList == true}">
             <td>${Bundle.getObject("yes")}</td>
             </c:if>
             <c:if test="${userInfo.user.inBlackList == false}">
              <td>${Bundle.getObject("no")}</td>
             </c:if>
          </tr>
       </c:forEach>
    </table>
    </c:if>
    <c:if test="${empty requestScope.userList}">
    	<h3>${Bundle.getObject("emptyUsers")}</h3>
    </c:if>
 </body>
</html>