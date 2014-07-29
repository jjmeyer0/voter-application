<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Mark Games as Owned</title>
</head>
<body>

<h1>Wanted Games</h1>
<form:form method="post" modelAttribute="markedGame" action="marked-game">
    <c:forEach items="${wantedGames}" var="game">
        <form:radiobutton path="title" value="${game.title}"/>Title: ${game.title} Votes: ${fn:length(game.votes)}<br>
    </c:forEach>
    <c:if test="${fn:length(wantedGames) != 0}">
        <input type="submit" value="Mark as Owned">
    </c:if>
</form:form>

<br><br>
<a href="/">Take me back home</a>
</body>
</html>
