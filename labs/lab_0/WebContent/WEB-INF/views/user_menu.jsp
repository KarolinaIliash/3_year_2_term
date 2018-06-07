<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
    
<div style="padding: 10px; clear:both; text-align: center;" >
 
   <a href="${pageContext.request.contextPath}/products" style="padding: 10px;">${Bundle.getObject("products")}</a>
   |
   <a href="${pageContext.request.contextPath}/basket" style="padding: 10px;">${Bundle.getObject("basket")}</a>
   |
    <a href="${pageContext.request.contextPath}/orders" style="padding: 10px;">${Bundle.getObject("orders")}</a>
   |
   <a href="${pageContext.request.contextPath}/login" style="padding: 10px;">${Bundle.getObject("loginButton")}</a>
   |
   <a href="${pageContext.request.contextPath}/signUp" style="padding: 10px;">${Bundle.getObject("signUp")}</a>
    
</div>  