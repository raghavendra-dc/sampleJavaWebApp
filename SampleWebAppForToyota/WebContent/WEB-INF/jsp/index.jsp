<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
   <head>
      <title>Login page</title>
   </head>
   
   <body>
   <h1><b><u>Toyota POC</u></b></h1>
   <h2>Please login using AD credentials:</h2>
   <form action="login" method="post">  
   <table>
            <tr>
               <td>User Name:</td>
               <td><input type="text" name="userName"/></td>
            </tr>
            <tr>
               <td>Password:</td>
               <td><input type="password" name="password"/></td>
            </tr>
            <tr>
               <td colspan = "2" ><p style="color: red;">${errorMessage}</p></td>
            </tr>
            <tr>
               <td colspan = "2"><input type="submit" value="Login"/> </td>
            </tr>
   </table>
		  
  		
	</form>
   
      
   </body>
</html>