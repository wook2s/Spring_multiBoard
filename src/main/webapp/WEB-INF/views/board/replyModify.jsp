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
		<c:if test="${reply.replyId != replyId}">
			<p>작성자 : ${reply.id} 댓글 내용 : ${reply.content}</p>
		</c:if>
		
		<c:if test="${reply.replyId == replyId}">
			<form action="<c:url value='/board/detail/replyModify/${board.boardId}/${reply.replyId}'/>" method="post">
				<p>
				작성자 : <input type="text" name="id" value="${reply.id}" readonly="readonly">
				댓글 내용 : <input type="text" name="content" value="${reply.content}">
				<input type="submit" value="댓글 수정하기"/>
				</p>
			</form>
		</c:if>
	</c:forEach>
	
	<a href="<c:url value='/board/list/1'/>">게시판 돌아가기</a>
	
</body>
</html>