<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>SignUp</title>
   </head>
   <body>
      <jsp:include page="header_.jsp"></jsp:include>
      <jsp:include page="user_menu.jsp"></jsp:include>
 
      <h3>${Bundle.getObject("signUp")}</h3>
      <p style="color: red;">${errorString}</p>
 
      <form method="POST" action="${pageContext.request.contextPath}/signUp" onsubmit="return validateForm()">
      	 <input type="hidden" id="lang" value="${Lang}">
         <table border="0">
            <tr>
               <td>${Bundle.getObject("phone")}</td>
               <td><input type="text" name="userPhone" id="phone" required value= "${user.phone}" /> </td>
            </tr>
            <tr>
            	<td>${Bundle.getObject("firstName")}</td>
            	<td><input type="text" name="firstName" required value="${user.firstName}"/></td>
            </tr>
            <tr>
            	<td>${Bundle.getObject("lastName")}</td>
            	<td><input type="text" name="lastName" required value="${user.lastName}"/></td>
            </tr>
            <tr>
               <td>${Bundle.getObject("password")}</td>
               <td><input type="password" name="password" required value= "${user.password}" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "${Bundle.getObject('submit')}" />
                  <a href="${pageContext.request.contextPath}/">${Bundle.getObject("cancel")}</a>
               </td>
            </tr>
         </table>
      </form>
      <p id="notValid" style="color: red;" ></p>
      <script>
	  function validateForm() {
      	var x, text;
      	// Get the value of the input field with id="phone"
      	x = document.getElementById("phone").value;
      	if (!x.match(/^\d{10}$/)) {
      		lang = document.getElementById("lang").value;
      		if(lang == "en")
        		text = "Phone should consists only from digits and have length 10";
      		else
      			text = "Номер телефону має містити тільки цифри та мати довжину в 10 символів";
          	document.getElementById("notValid").innerHTML = text;
          	return false;
      	}
      	return true;
	  }
	  </script>
   </body>
</html>