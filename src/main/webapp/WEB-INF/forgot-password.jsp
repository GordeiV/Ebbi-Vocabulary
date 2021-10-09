<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/forgot-password.css">
</head>

<body>
<div class ="container">
    <div class="titel">
        I'm sorry to say but it was silly of you to forget the password
    </div>
    <br>

    <div class="image">
        <img src="${pageContext.request.contextPath}/images/cat.png">
    </div>

    <div>
        <p></p>
        <form action="login" method="get">
          <input type="submit" class="btn" value="Back">
        </form>
      </div>
</div>
</body>
</html>