<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
    
<div style="padding: 5px; clear:both; text-align: center;">
 
   <a href="${pageContext.request.contextPath}/admin/products">${Bundle.getObject("products")}</a>
   |
   <a href="${pageContext.request.contextPath}/admin/allorders?all=true">${Bundle.getObject("allOrders")}</a>
   |
   <a href="${pageContext.request.contextPath}/admin/allorders?all=false">${Bundle.getObject("unpayedOrders")}</a>
   |
   <a href="${pageContext.request.contextPath}/admin/users?all=true">${Bundle.getObject("allUsers")}</a>
   |
   <a href="${pageContext.request.contextPath}/admin/users?all=false">${Bundle.getObject("userUnOrders")}</a>
   |
   <a href="${pageContext.request.contextPath}/admin/blacklist">${Bundle.getObject("blacklist")}</a>
    
</div>  