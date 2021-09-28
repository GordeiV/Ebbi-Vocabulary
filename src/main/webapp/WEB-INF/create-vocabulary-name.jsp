<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/create-vocabulary-name.css">
  <title>Document</title>
</head>
<body>
    <div class="container">
        <form action="vocabulary" method="post">
            <div class="titel">
              <b>Write the name of vocabulary</b>
            </div>
            <br>
            <div class="site">
                <input type="text" placeholder="name" name="name" required>

                <input type="submit" class="btn" value="OK">
                <p></p>
                <span class="error">${error}</span>
            </div>
        </form>
        <div class="back">
            <form action="../start">
                <button type="submit">Back</button>
            </form>
        </div>
    </div>
</body>
</html>