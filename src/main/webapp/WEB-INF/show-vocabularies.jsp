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
       <table>
         <c:forEach items="${vocabularies}" var="vocabulary">
           <tr>
             <td><c:out value="${vocabulary.name}" /></td>
             <td><c:out value="${vocabulary.vocabularyStatus}" /></td>
           </tr>
           <br>
         </c:forEach>
       </table>
    </div>

</div>
</body>
</html>