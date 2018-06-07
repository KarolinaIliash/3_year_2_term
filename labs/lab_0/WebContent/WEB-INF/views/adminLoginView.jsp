<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Login</title>
   </head>
   <body>
   	  <jsp:include page="header_.jsp"></jsp:include>
      <jsp:include page="admin_menu.jsp"></jsp:include>
   
      <p style="color: red;">${errorString}</p>
      <form method="POST" action="${pageContext.request.contextPath}/admin">
         <table border="0">
            <tr>
               <td>${Bundle.getObject("login")}</td>
               <td><input type="text" name="login" value= "${admin.login}"  required/> </td>
            </tr>
            <tr>
               <td>${Bundle.getObject("password")}</td>
               <td><input type="password" name="password" value= "${admin.password}" required /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= ${Bundle.getObject("submit")} />
                  <a href="${pageContext.request.contextPath}/">${Bundle.getObject("cancel")}</a>
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>