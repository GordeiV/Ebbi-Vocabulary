<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vocabularies.css">
    <title>Vocabularies</title>
</head>
<body>
    <div class="container">

        <table>

            <c:forEach items="${vocabularies}" var="vocabulary">
                <tr>
                    <th>
                        <a href="vocabulary?id=<c:out value='${vocabulary.id}' />">
                            <c:out value="${vocabulary.name}" />
                        </a>
                    </th>

                    <th>
                        <input type="button" value="Delete"
                        onclick="window.location.href = 'http://localhost:8080/MyFirstOwnProject-1.0/delete?id=${vocabulary.id}'"
                        class="cancel_button">
                    </th>
                </tr>
            </c:forEach>

        </table>

        <div class="back">
            <form action="start">
                <button type="submit">Back</button>
              </form>
        </div>
    </div>
</body>
</html>