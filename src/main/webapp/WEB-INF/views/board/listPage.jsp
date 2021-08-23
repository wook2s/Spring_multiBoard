<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
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
			 border-collapse: collapse;
		}
		th{
			background-color: ivory;
		}
		p{
			margin :2% 10%;
			text-align: right;
		}
		#tableContainer{
			min-height: 300px;
		}
	</style>

</head>
<body>
<div id="container">
	<p>총 게시물 : ${totalBoardCount}</p>
	<div id="tableContainer">
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
				<td><a href="<c:url value='/board/detail/${board.categoryId}/${board.boardId}'/>">${board.title} [${board.replyNum}]</a></td>
				<td>${board.writeDate}</td>
				<td>${board.readCount}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	
	<div id="pager">
		<span style="display: inline-block; width: 21px ">
		<c:if test="${nowPage != 1}">
			<%-- <span><a href="<c:url value="/board/list/${categoryId}/${nowPage-1}"/>">[&lt]</a></span> --%>
			<span><a href="${path}${nowPage-1}">[&lt]</a></span>
		</c:if>
		</span>
					<!-- /board/search/{categoryId}/{option}/{word}/{nowPage} -->
		<span  style="width: 70px; display:inline-block;">
		<fmt:parseNumber var="totalBoardCount" value="${totalBoardCount}"/>
		<c:set var="start" value="${(((nowPage-1)/5) - (((nowPage-1)/5)%1))*5+1}" />
		<c:forEach begin="${start}" end="${start+4}" varStatus="cnt">
			<c:if test="${cnt.current <= lastPage }">
				<c:if test="${nowPage != cnt.current}">
					<%-- <a href="<c:url value="/board/list/${categoryId}/${cnt.current}"/>">${cnt.current}</a> --%>
					<a href="${path}${cnt.current}">${cnt.current}</a>
				</c:if>
				<c:if test="${nowPage == cnt.current}">
					<%-- <a href="<c:url value="/board/list/${categoryId}/${cnt.current}"/>" style="color: red;">${cnt.current}</a> --%>
					<a href="${path}${cnt.current}" style="color: red;">${cnt.current}</a>
				</c:if>
			</c:if>
		</c:forEach>
		</span>
		
		<span style="display: inline-block; width: 21px">
		<c:if test="${nowPage != lastPage && lastPage != 0}">
			<%-- <span><a href="<c:url value="/board/list/${categoryId}/${nowPage+1}"/>">[&gt]</a></span> --%> 
			<span><a href="${path}${nowPage+1}">[&gt]</a></span> 
		</c:if>
		</span>
	</div>
	
	<br>
	<div id="writeDiv" >
	<a href="<c:url value='/board/insert'/>"><div id="writeButton">글쓰기</div></a>
	</div>
</div>
</body>

</html>