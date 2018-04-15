<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Product List</title>
 </head>
 <body>
 
    <jsp:include page="header_.jsp"></jsp:include>
    <jsp:include page="user_menu.jsp"></jsp:include>
 
    <h3>${Bundle.getObject("products")}</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>${Bundle.getObject("nameDefault")}</th>
          <th>${Bundle.getObject("price")}</th>
          <th>${Bundle.getObject("buy")}</th>
       </tr>
       <c:forEach items="${productList}" var="product" >
          <tr>
             <c:if test="${Lang eq 'en'}" >
             <td>${product.name}</td>
             </c:if>
             <c:if test="${Lang eq 'ua'}" >
             <td>${product.nameUA}</td>
             </c:if>
             <td>${product.priceBeforePoint}.${product.priceAfterPoint}</td>
             <td>
               <form method="POST" action="${pageContext.request.contextPath}/addInBasket">
               <input type="hidden" name="prod" value="${product.id}"/>
               <input type="number" name="amount" min="1" max="50" value="1"/>
               <input type="submit" value="${Bundle.getObject('addButton')}"/>
               </form>
             </td>
          </tr>
       </c:forEach>
    </table>
 </body>
</html>