<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <title>Profil</title>
</head>
<body>
    <div class="container">
        <h1 class="titel">${login}</h1>
        <div class="information">
            <p>Number of vocabularies: <b>${num_voc}</b> </p>
            <p>Number of words: <b>${num_words}</b></p>
        </div>
        <div class="back">
            <form action="start">
                <button type="submit">Back</button>
              </form>
        </div>
    </div>
</body>
</html>