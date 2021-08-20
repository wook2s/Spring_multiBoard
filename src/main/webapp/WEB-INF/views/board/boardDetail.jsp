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
	<h1>보드 디테일 페이지입니다.</h1>
	<p>보드 번호 : ${board.boardId}<br> 
	작성자 : ${board.id}<br>
	보드 제목 : ${board.title}<br>
	읽은 횟수 : ${board.readCount}<br>
	<hr>
	내용 : ${board.content}</p>
	<a href="<c:url value='/board/detail/downloadFile/${board.boardId}'/>">${board.fileName}</a>
	<hr>
	<p>댓글</p>
	<c:if test="${empty replyList}">
		<p>댓글 없음</p>
	</c:if>
	<c:forEach var="reply" items="${replyList }">
		<p>작성자 : ${reply.id} 댓글 내용 : ${reply.content}
		<c:if test="${reply.id == id}">
		<a href="<c:url value='/board/detail/replyModify/${board.boardId}/${reply.replyId}'/>">수정</a>
		<a href="<c:url value='/board/detail/replyDelete/${board.boardId}/${reply.replyId}'/>">삭제</a>
		</c:if>
		</p>
	</c:forEach>
	
	<form action="<c:url value='/board/detail/replyInsert/${board.boardId}'/>" method="post">
		<input type="text" name="id" value="${id}" readonly="readonly"/>
		<input type="text" name="content"/>
		<input type="hidden" name="_boardId" value="${board.boardId}/">
		<input type="submit" value="댓글달기">
	</form>
	
	
	<c:if test="${board.id == id}">
	<a href="<c:url value='/board/modify/${board.boardId}'/>">게시글 수정</a><br>
	<a href="<c:url value='/board/delete/${board.boardId}'/>">게시글 삭제</a><br>
	</c:if>
	<a href="<c:url value='/board/list/1'/>">게시판 돌아가기</a>
	
</body>
</html>