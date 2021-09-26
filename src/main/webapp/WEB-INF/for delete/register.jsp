<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Document</title>
</head>
<body>
  <form action="register" method="post">
    <div>
      <h1>Register!</h1>
      <p>Please fill in this form to create an account</p>
      <hr>
        <label for="User name"><b>Email</b></label>
        <input type="text" placeholder="User" name="user" id="user" required>
        <p></p>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" id="psw" required>
        <p></p>

        <label for="psw-repeat"><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>
        <p></p>

        <input type="submit" class="btn" value="register">

        <p></p>

        <span class="error"><b><h3>${error}</h3></b></span>
      <hr>
    </div>
    <div>
  </form>
  <form action="login">
      <input type="submit" value="Go to Log In" />
  </form>
</body>
</html>