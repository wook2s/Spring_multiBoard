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
	<c:if test="${empty replyList}">
		<p>댓글 없음</p>
	</c:if>
	
	<table style="width: 100%">
		<c:forEach var="reply" items="${replyList }">
		<tr>
			<th width="5%">${reply.id}</th>
			<td width="70%">${reply.content}</td>
			<c:if test="${reply.id == id}">
			<td width="10%"><a href="<c:url value='/board/detail/replyModify/${board.boardId}/${reply.replyId}'/>">수정</a></td>
			<td width="10%"><a href="<c:url value='/board/detail/replyDelete/${board.boardId}/${reply.replyId}'/>">삭제</a></td>
			</c:if>
		</tr>
		</c:forEach>
	</table>
	<br>
	
	<form action="<c:url value='/board/detail/replyInsert/${board.boardId}'/>" method="post">
		<input id="replyId" type="text" name="id" value="${id}" readonly="readonly" style="border:none; width: 50px;"/>
		<input type="text" name="content" style="width: 70%; height: 20px;"/>
		<input type="hidden" name="_boardId" value="${board.boardId}/">
		<input type="submit" value="댓글달기">
	</form>
	
	<hr><br><br>
	<div id="goListDiv">
		<c:if test="${board.id == id}">
			<a href="<c:url value='/board/modify/${board.boardId}'/>"><div id="goList">게시물 수정</div></a>
			<a href="<c:url value='/board/delete/${board.boardId}'/>"><div id="goList">게시물 삭제</div></a>
		</c:if>
		<a href="<c:url value='/board/list/1'/>"><div id="goList">게시판 돌아가기</div></a>
	</div>
	
	<div style="margin-bottom: 100px;"></div>
	
	</div>
</body>
</html>