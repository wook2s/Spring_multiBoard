<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>1번 카테고리 리스트 페이지입니다.</h1>
<c:forEach var="board" items="${boardList}">
	<a href="<c:url value='/board/detail/${board.boardId}'/>" >  ${board.boardId}, ${board.title} [${board.replyNum}]</a><br>
	
</c:forEach>

<br>
<a href="<c:url value='/board/insert'/>">글쓰기</a>
</body>
</html>