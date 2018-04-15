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
 	
 	<c:if test="${not empty requestScope.orderList}">
    <h3>${Bundle.getObject("orders")}</h3>
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
       	<th colspan="3">${Bundle.getObject("orders")}</th>
       </tr>
       <c:forEach items="${requestScope.orderList}" var="order" >
   		  <tr>
   		  	 <th style="color:green;">${Bundle.getObject("userPhone")}</th>
          	 <th style="color:green;">${Bundle.getObject("date")}</th>
          	 <th style="color:green;">${Bundle.getObject("price")}</th>
          	 <th style="color:green;">${Bundle.getObject("setPayed")}</th>
       	  </tr>
          <tr>
             <td>${order.userPhone}</td>
             <td>${order.date}</td>
             <!--<td>${order.priceBeforePoint}.${order.priceAfterPoint}</td>-->
             <td>${order.curPrice}</td>
             <td><a href = "setPayed?id=${order.id}">${Bundle.getObject("setPayed")}</a></td>
          </tr>
          <tr>
          <th colspan = "3">${Bundle.getObject("products")}</th>
          </tr>
          <tr>
          <th>${Bundle.getObject("nameDefault")}</th>
          <th>${Bundle.getObject("price")}</th>
          <th>${Bundle.getObject("amount")}</th>
          <c:forEach items="${order.products}" var="productAmount" >
          <tr>
              <c:if test="${Lang=='en'}" >
             <td>${productAmount.product.name}</td>
             </c:if>
             <c:if test="${Lang=='ua'}" >
             <td>${productAmount.product.nameUA}</td>
             </c:if>
             <td>${productAmount.product.priceBeforePoint}.${productAmount.product.priceAfterPoint}</td>
             <td>${productAmount.amount}</td>
          </tr>
       </c:forEach>
          </tr>
       </c:forEach>
    </table>
    </c:if>
    <c:if test="${empty requestScope.orderList}">
    	<h3>${Bundle.getObject("emptyOrders")}</h3>
    </c:if>
 </body>
</html>