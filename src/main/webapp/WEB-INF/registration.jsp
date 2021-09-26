<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">
  <title>Document</title>
</head>
<body class=container>
  <form action="register" method="post">
    <div>
      <h1 class="titel">Register!</h1>
      <p class="under_titel">Please fill in this form to create an account</p>
      <br>
      <table>
        <tr>
            <td><label for="user"><b>Username: </b></label></td>
            <td><input type="text" name="user" id="user" required></td>
        </tr>


        <tr>
            <td><label for="psw"><b>Password: </b></label></td>
            <td><input type="password" name="psw" id="psw" required></td>
        </tr>

        <tr>
            <td><label for="psw-repeat"><b>Repeat Password: </b></label></td>
            <td><input type="password" name="psw-repeat" id="psw-repeat" required></td>
        </tr>

        <tr>
            <td colspan="2" id="reg-button"><button type="submit">Register</button></td>
        </tr>

      </table>

        <span class="error"><b><h3>${error}</h3></b></span>
      <hr>
    </div>
    <div>
  </form>
  <form action="login">
    <button type="submit" id="back">Back to Log in</button>
  </form>
</body>
</html>