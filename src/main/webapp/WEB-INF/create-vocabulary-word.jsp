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
  <form action="vocabulary" method="post">
    <div>
      <h1>Write the name of vocabulary</h1>
      <hr>
        <label for="word"></label>
        <input type="text" name="word" required>

        <label for="transcription"></label>
        <input type="text" name="transcription">

        <label for="translation"></label>
        <input type="text" name="translation" required>

        <input type="submit" class="btn" value="Add the word to vocabulary">
        <p></p>
        <span class="error">${error}</span>
        <br>
      <hr>
    </div>
  </form>
  <form action="../save/vocabulary" method="get">
    <input type="submit" class="btn" value="Save vocabulary">
  </form>
</body>
</html>
