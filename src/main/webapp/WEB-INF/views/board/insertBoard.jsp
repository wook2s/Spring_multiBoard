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
	<p>보드 번호 : ${board.boardId}, 보드 제목 : ${board.title}, 읽은 횟수${board.readCount}</p>
	
	<p>댓글</p>
	<c:forEach var="reply" items="${replyList }">
		<p>${reply.id},${reply.content}</p>
	</c:forEach>
	
</body>
</html>