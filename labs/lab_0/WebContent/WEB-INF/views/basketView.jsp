<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Basket</title>
 </head>
 <body>
 
    <jsp:include page="header_.jsp"></jsp:include>
    <jsp:include page="user_menu.jsp"></jsp:include>
 
 	<c:if test="${not empty Basket}">
    <h3>${Bundle.getObject("products")}</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>${Bundle.getObject("nameDefault")}</th>
          <th>${Bundle.getObject("price")}</th>
          <th>${Bundle.getObject("changeAmount")}</th>
       </tr>
       <c:forEach items="${Basket}" var="productAmount" >
          <tr>
             <c:if test="${Lang eq 'en'}" >
             <td>${productAmount.product.name}</td>
             </c:if>
             <c:if test="${Lang eq 'ua'}" >
             <td>${productAmount.product.nameUA}</td>
             </c:if>
             <td>${productAmount.product.priceBeforePoint}.${productAmount.product.priceAfterPoint}</td>
             <td>
               <form method="POST" action="${pageContext.request.contextPath}/changeInBasket">
               <input type="hidden" name="prod" value="${productAmount.product.id}"/>
               <input type="number" name="amount" min="0" max="50" value="${productAmount.amount}"/>
               <input type="submit" value="${Bundle.getObject('change')}"/>
               </form>
             </td>
          </tr>
       </c:forEach>
    </table>
 	${Bundle.getObject("sum")} = ${Sum}<br>
    <a href="addOrder">${Bundle.getObject("order")}</a>
    </c:if>
    <c:if test="${empty Basket}">
    <h3>${Bundle.getObject("emptyProducts")}</h3>
    </c:if>
 
 </body>
</html>