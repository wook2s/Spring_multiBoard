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
			text-align: center;
		}
		#writeButton{
			display : inline-block;
			height : 50px;
			width: 200px;
			border:  thin black solid;
			vertical-align:middle;
			line-height: 50px;
			font-size: large;
			background-color: ivory;
		}
		#writeDiv{
			text-align: right;
		}
		#writeDiv a{
			text-align: center;
		}
		table{
			margin: auto;
			width: 80%;		
		}
		th{
			background-color: ivory;
		}
	</style>

</head>
<body>
<div id="container">
	<h1>1번 카테고리 리스트 페이지입니다.</h1>
	<table border="1">
		<tr>
			<th width="1%">글번호</th>
			<th width="1%">작성자</th>
			<th width="5%">제목</th>
			<th width="2%">작성일</th>
			<th width="1%">조회수</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.boardId}</td>
				<td>${board.id}</td>
				<td><a href="<c:url value='#'/>">${board.title} [${board.replyNum}]</a></td>
				<td>${board.writeDate}</td>
				<td>${board.readCount}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<div id="writeDiv" >
	<a href="<c:url value='/board/insert'/>"><div id="writeButton">글쓰기</div></a>
	</div>
</div>
</body>

</html>