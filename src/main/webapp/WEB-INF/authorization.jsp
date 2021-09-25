<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Authorization</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/authorization.css"/>
</head>
<body class="container">
    <div class="form">
        <h1 class="titel">Ebbi-Vocabularies</h1>
        <div class="input-form">
            <form action="login" method="post" id="login">
                <input type="text" name="login" placeholder="Username">
                <input type="password" name="password" placeholder="Password">
                <button type="submit">LOGIN</button>
            </form>
            <a href="" id="forgot-pass">Forgot Password?</a>
        </div>

        <div class="error">${error}</div>

        <div id="sign-up">
            <h3 class="under_titel">Don't have an account?</h3>
            <a href="register">Sign up</a>
        </div>
    </div>
</body>
</html>