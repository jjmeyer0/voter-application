<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vote for you favorite game!</title>
</head>
<body>
<c:forEach items="${games}" var="game">
    <li>${game.title} has ${fn:length(game.votes)} votes</body></li>
</c:forEach>
</body>
</html>
