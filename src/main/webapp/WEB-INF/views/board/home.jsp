<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
	<title>Home</title>
	<style type="text/css">
		#mainbody{
			display:inline-block;
			width: 100%;
			height: 250px;
			text-align: center;
		}
		#subbody{
			display:inline-block;
			display: flex;
		}
		#subbody .sub1{
			flex : 1;
		 	border-right: thin black solid;
			display:inline-block;
			width: 32%;
			height: 220px;
			text-align: center;
		}
		#subbody .sub2{
			flex : 1;
			display:inline-block;
			width: 32%;
			height: 220px;
			text-align: center;
		}
		ul{
	   list-style:none;
	   }
	   .heat{
	   	text-align: center;
	   	margin-bottom: 10px;
	   }
	
	</style>
</head>
<body>
	<c:if test="${loginMessage eq 'success'}">
		<script type="text/javascript">
			alert("로그인 하였습니다");
		</script>
	</c:if>
	<c:if test="${memberDeleteMessage eq 'deleteSuccess'}">
		<script type="text/javascript">
			alert("회원 탈퇴 완료");
		</script>
	</c:if>
	<c:if test="${memberDeleteMessage eq 'deleteFail'}">
		<script type="text/javascript">
			alert("회원 탈퇴 실패");
		</script>
	</c:if>
	
<div id="mainbody">
	<h1>Hello!</h1>
	<p>게시판 메인 페이지입니다.</p>
	<nav>
		<ul>
			<li><a href="<c:url value='/board/list/1/1'/>">일반 게시판</a></li>
			<li><a href="<c:url value='/board/list/2/1'/>">사진 게시판</a></li>
			<li><a href="<c:url value='/board/list/3/1'/>">영상 게시판</a></li>
		</ul>
	</nav>
</div>
<hr>
<div id="subbody">
		<div class="sub1">
			<div class="heat">일반 게시판 최대 조회</div>
			<div>
			 <a href="<c:url value='/board/detail/${board1.categoryId}/${board1.boardId}'/>">제목 : ${board1.title}, 조회수 ${board1.readCount}</a>
			</div>
		</div>	
		<div class="sub1">
			<div class="heat">사진 게시판 최대 조회</div>
			<div>
			 <a href="<c:url value='/board/detail/${board2.categoryId}/${board2.boardId}'/>">제목 : ${board2.title}, 조회수 ${board2.readCount}</a>
			</div>
		</div>	
		<div class="sub2">
			<div class="heat">영상 게시판 최대 조회</div>
			<div>
			 <a href="<c:url value='/board/detail/${board3.categoryId}/${board3.boardId}'/>">제목 : ${board3.title}, 조회수 ${board3.readCount}</a>
			</div>
		</div>	
</div>	
</body>
</html>
