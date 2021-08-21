<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#container{
		width: 80%;
		margin: 0px auto;
	}
	th{
		text-align: left;
		width: 100px;
	}
	#content{
		border: thin solid black;
		height: 300px;
		border-radius: 10px 10px;
	}
	#replyId{
		font-size: large;
	}
	#goList{
			display : inline-block;
			height : 50px;
			width: 180px;
			border:  thin black solid;
			vertical-align:middle;
			line-height: 50px;
			font-size: large;
			background-color: ivory;
			text-align: center;
		}
	#goListDiv{
		text-align: right;
	}
</style>
</head>
<body>
	<div id="container">
		<table>
			<tr>
				<th>번호</th>
				<td>${board.boardId}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${board.id}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${board.title}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${board.readCount}</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><a href="<c:url value='/board/detail/downloadFile/${board.boardId}'/>">${board.fileName}</a></td>
			</tr>
		</table><br>
		
	<div id="content">
		<p>${board.content}</p>
		
	</div>
	<br>
	<hr>
	<p style="text-align: center;">댓글</p>
	
	<form action="<c:url value='/board/detail/replyModify/${board.boardId}/${replyId}'/>" method="post">
		<table style="width: 100%">
			<c:forEach var="reply" items="${replyList }">
				<c:if test="${reply.replyId != replyId}">
					<tr>
						<th>${reply.id}</th>
						<td>${reply.content}</td>
					</tr>
				</c:if>
				<c:if test="${reply.replyId == replyId}">
						<tr>
							<th><input type="text" name="id" value="${reply.id}" readonly="readonly"></th>
							<td><input type="text" name="content" value="${reply.content}"></td>
							<td><input type="submit" value="댓글 수정하기"/></td>
						</tr>
				</c:if>
			</c:forEach>
		</table>
	
	</form>
	<br>
	<hr><br><br>
		<a href="<c:url value='/board/list/1'/>"><div id="goList">게시판 돌아가기</div></a>
	<div style="margin-bottom: 100px;"></div>
	</div>
</body>
</html>
<%-- 
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
</html> --%>