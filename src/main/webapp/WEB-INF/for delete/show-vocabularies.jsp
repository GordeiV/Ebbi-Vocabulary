<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>

<body>
<div class ="main">
    <p>User: ${login}</p>
    <div>
       <table>
         <c:forEach items="${vocabularies}" var="vocabulary">
           <tr>
             <a href="/MyFirstOwnProject-1.0/vocabulary?id=<c:out value='${vocabulary.id}' />"> <c:out value="${vocabulary.name}" /> </a>
           </tr>
           <br>
         </c:forEach>
       </table>
    </div>

</div>
</body>
</html>