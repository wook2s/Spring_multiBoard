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
		#asideContainer{
			text-align: center;
		}
		#asideContainer a{
			text-decoration: none;
		}
		#asideButton{
			display : inline-block;
			height : 50px;
			width: 80%;
			border:  thin black solid;
			vertical-align:middle;
			line-height: 50px;
			font-size: large;
			background-color: ivory;
		}
		a:link {
 		 color : black;
		}
		a:visited {
  		color : black;
		}
	
	</style>
</head>
<body>
</body>
<div id="asideContainer">
	<c:if test="${empty id}">
		<p>로그인 후 서비스를 이용해주세요</p>
		<a href="<c:url value='/member/login'/>"><div id="asideButton">로그인</div></a><br><br>
		<a href="<c:url value='/member/insert'/>"><div id="asideButton">회원가입</div></a>
	</c:if>
	<c:if test="${!empty id}">
		<p>${id}님 환영합니다!</p><br>
		<a href="<c:url value='/member/logout'/>"><div id="asideButton">로그아웃</div></a><br>
	</c:if>
</div>
</html>