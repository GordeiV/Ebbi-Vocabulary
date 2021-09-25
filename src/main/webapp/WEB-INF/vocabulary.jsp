<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    </head>

    <body>
    <div class ="main">
        <p>${login}</p>
        <div>
           <p>Vocabulary: ${vocabulary.name}</p>
            <hr>
            <p>Words: </p>
            <table>
            <c:forEach items="${vocabulary.words}" var="word">
               <tr>
                 <td><c:out value="${word.foreignWord}" /></td>
                 <td><c:out value="${word.transcription}" /></td>
                 <td><c:out value="${word.nativeWord}" /></td>
               </tr>
            </c:forEach>
           </table>
        </div>

    </div>
    </body>
</html>