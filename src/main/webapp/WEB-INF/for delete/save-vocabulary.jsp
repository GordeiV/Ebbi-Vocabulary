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
       <p>All words in the vocabulary:</p>
       <table>
         <c:forEach items="${words}" var="word">
           <tr>
             <td><c:out value="${word.foreignWord}" /></td>
             <td><c:out value="${word.transcription}" /></td>
             <td><c:out value="${word.nativeWord}" /></td>
           </tr>
           <br>
         </c:forEach>
       </table>
    </div>
    <div>
      <p></p>
      <form action="vocabulary" method="post">
        <input type="submit" class="btn" value="Save the vocabulary">
      </form>
    </div>
</div>
</body>
</html>