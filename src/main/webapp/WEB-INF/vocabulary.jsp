<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vocabulary.css">
</head>

<body>
<div class ="container">
    <div class="site">
        <div class="titel">
            All words in the vocabulary:
        </div>
       <table>
         <tr>
               <th>Word</th>
               <th>Transcription</th>
               <th>Translation</th>
         </tr>
         <br>
         <c:forEach items="${words}" var="word">
            <tr>
                <td><c:out value="${word.foreignWord}" /></td>
                <td><c:out value="${word.transcription}" /></td>
                <td><c:out value="${word.nativeWord}" /></td>
            </tr>
         </c:forEach>
       </table>
    </div>
    <div>
      <p></p>
      <form action="start" method="get">
         <input type="submit" class="btn" value="Back">
      </form>
    </div>
</div>
</body>
</html>