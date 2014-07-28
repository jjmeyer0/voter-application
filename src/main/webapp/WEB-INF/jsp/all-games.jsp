<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- TODO merge this and owned-games.jsp -->
<html>
<head>
    <title>All Games</title>
</head>
<body>
<c:choose>
    <c:when test="${fn:length(games) == 0}">
        No games exist! <a href="/add-game">Add a game you want!</a>

    </c:when>

    <c:otherwise>
        <c:forEach var="game" items="${games}">
            <li>${game.title}</li>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
