<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
</head>
<body>
    <div class="sidebar">
        <h2>Log in</h2>
        <form action="login" method="post" id="login">
            <input type="text" name="login" placeholder="login">
            <input type="password" name="password" placeholder="password">
            <input type="submit" class="btn" value="log in">
            <span class="error">${error}</span>
            <div class="lables_passreg_text">
                <span><a href="register">Register</a></span>
            </div>

        </form>
    </div>
</body>
</html>