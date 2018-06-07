<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>${Bundle.getObject("products")}</title>
 </head>
 <body>
 
    <jsp:include page="header_.jsp"></jsp:include>
    <jsp:include page="admin_menu.jsp"></jsp:include>
 
    <h3>${Bundle.getObject("products")}</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>${Bundle.getObject("nameEn")}</th>
          <th>${Bundle.getObject("nameUa")}</th>
          <th>${Bundle.getObject("price")}</th>
          <th>${Bundle.getObject("editButton")}</th>
          <th>${Bundle.getObject("deleteButton")}</th>
       </tr>
       <c:forEach items="${productList}" var="product" >
          <tr>
             <td>${product.name}</td>
             <td>${product.nameUA}</td>
             <td>${product.priceBeforePoint}.${product.priceAfterPoint}</td>
             <td><a href="editProduct?id=${product.id}">${Bundle.getObject("editButton")}</a></td>
             <td><a href="deleteProduct?id=${product.id}">${Bundle.getObject("deleteButton")}</a></td>
          </tr>
       </c:forEach>
    </table>
    <a href="createProduct">${Bundle.getObject("createButton")}</a>
 </body>
</html>