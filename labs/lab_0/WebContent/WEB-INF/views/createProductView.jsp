<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Create Product</title>
   </head>
   <body>
    
      <jsp:include page="header_.jsp"></jsp:include>
      <jsp:include page="admin_menu.jsp"></jsp:include>
       
      <h3>Create Product</h3>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/admin/createProduct" onsubmit="return validateForm()">
         <table border="0">
            <tr>
               <td>${Bundle.getObject("nameEn")}</td>
               <td><input type="text" name="name" value="${product.name}" /></td>
            </tr>
            <tr>
               <td>${Bundle.getObject("nameUa")}</td>
               <td><input type="text" name="nameUA" value="${product.nameUA}" /></td>
            </tr>
            <tr>
               <td>${Bundle.getObject("price")}</td>
               <td><input type="text" name="price" id="price" value="${product.price}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value=${Bundle.getObject('submit')} />
                   <a href="products">${Bundle.getObject("cancel")}</a>
               </td>
            </tr>
         </table>
      </form>
      
      <p id="notValid" style="color: red;" ></p>
      <script>
	  function validateForm() {
      	var x, text;
      	// Get the value of the input field with id="phone"
      	x = document.getElementById("price").value;
      	if (!x.match(/^\d+\.\d{0,2}$/)) {
          	lang = document.getElementById("lang").value;
      		if(lang == "en")
        		text = "Price should be a real number, have dot and maximum two numbers after dot";
      		else
      			text = "Ціна має бути дійсним числом, мати крапку і максимум два символи після неї";
          	document.getElementById("notValid").innerHTML = text;
          	return false;
      	}
      	return true;
	  }
	  </script>
   </body>
</html>