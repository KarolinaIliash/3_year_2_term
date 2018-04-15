<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
  <div style="float: left">
  	 <h1>${Bundle.getObject("pageName")}</h1>
  </div>
 
  <div style="float: right; padding: 10px; text-align: right;">
  
     <!-- User store in session with attribute: loginedUser -->
     <c:if test = "${not empty loginedUser}">
     ${Bundle.getObject("hello")} <b>${loginedUser.firstName}</b>
     </c:if>
     <c:if test = "${not empty loginedAdmin}">
     ${Bundle.getObject("hello")} <b>${loginedAdmin.login}</b>
     </c:if>
      
     <a href="changeLang?lang=ua">UA</a> | <a href = "changeLang?lang=en">EN</a>
  </div>
</div>