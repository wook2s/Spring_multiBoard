<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
	<title>Home</title>
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

<h1>
	Hello !  
</h1>

<P>  메인 페이지입니다. </P>

<nav>
<ul>
	<li> <a href="<c:url value='/board/list/1'/>">1번 카테고리</a>  </li>
	<li> <a href="<c:url value='/board/list/2'/>">2번 카테고리</a>  </li>
	<li> <a href="<c:url value='/board/list/3'/>">3번 카테고리</a>  </li>
</ul>
</nav>
<hr>
<a href="<c:url value='/member/login'/>">로그인</a><br>
<a href="<c:url value='/member/insert'/>">회원가입</a>
</body>
</html>
