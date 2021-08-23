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
		}
		#subbody .sub2{
			flex : 1;
			display:inline-block;
			width: 32%;
			height: 220px;
		}
		ul{
	   list-style:none;
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
			<li><a href="<c:url value='/board/list/1/1'/>">1번 카테고리</a></li>
			<li><a href="<c:url value='/board/list/2/1'/>">2번 카테고리</a></li>
			<li><a href="<c:url value='/board/list/3/1'/>">3번 카테고리</a></li>
		</ul>
	</nav>
</div>
<hr>
<div id="subbody">
		<div class="sub1"></div>	
		<div class="sub1"></div>	
		<div class="sub2"></div>	
</div>	
</body>
</html>
