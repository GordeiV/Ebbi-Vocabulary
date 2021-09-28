<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/create-vocabulary-word.css">
  <title>Document</title>
</head>
<body>
    <div class="container">
        <form action="vocabulary" method="post">
            <div class="site">
                <div class="titel">
                    Write a word and its translation
                </div>
              <hr>
                <input type="text" name="word" placeholder="word" required>

                <input type="text" name="transcription" placeholder="transcription" >

                <input type="text" name="translation" placeholder="translation" required>
                <p></p>

                <input type="submit" class="btn" value="Add the word to the vocabulary">
                <p></p>
                <span class="error">${error}</span>
                <br>
              <hr>
            </div>
          </form>
          <div class="footer">
            <form action="../save/vocabulary" method="get">
                <input type="submit" class="btn" value="Save the vocabulary">
            </form>
          </div>

    </div>

</body>
</html>
