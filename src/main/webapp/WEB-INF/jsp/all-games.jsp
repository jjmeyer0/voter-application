<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All Games</title>
</head>
<body>

    <h1>Owned Games</h1>
    <c:forEach items="${ownedGames}" var="owned">
        Title: ${owned.title}<br>
    </c:forEach>

    <h1>Wanted Games</h1>
    <form:form method="post" modelAttribute="wantedGame" action="vote-for-game">
        <c:forEach items="${wantedGames}" var="game">
            <form:radiobutton path="title" value="${game.title}"/>Title: ${game.title} Votes: ${fn:length(game.votes)}<br>
        </c:forEach>
        <c:if test="${fn:length(wantedGames) !=  0}">
            <input type="submit" value="Vote">
        </c:if>
    </form:form>
</body>
</html>
