<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/start-size.css">
    <title>Ebbi-Vocabularies</title>
</head>
<body>
    <div class = "main">

        <div class="header">

            <div class="logo">
                <h1>Ebbi-Vocabularies</h1>
            </div>


            <div class="mebubar">
                <ul class="menu">
                    <li><a href="">Your Profil</a></li>
                    <li><a href="logout">Log out</a></li>
                </ul>
            </div>

        </div>

        <div class="site_content">

            <div class="actions">
                <ul>
                    <li><a href="vocabularies">My vocabularies</a></li>
                    <li><a href="repeat">Vocabularies for repeat</a></li>
                    <li><a href="create/vocabulary">Create new vocabulary</a></li>

                </ul>

            </div>

            <div class="find">
                <form action="find" method="POST" id="search_form">
                    <input type="search" name="vocabulary-name" placeholder="Vocabulary's name">
                    <input type="submit" class="btn" value="Find">
                    <p class="message">${message}</p>
                </form>
            </div>

            <div class="about">
                <p>It's for...</p>
            </div>
        </div>
    </div>
</body>
</html>