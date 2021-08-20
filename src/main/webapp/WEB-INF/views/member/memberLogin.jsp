<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>로그인</title>
<body>
	<c:if test="${loginMessage eq 'idFail'}">
		<script type="text/javascript">
			alert("아이디가 없습니다. 회원가입해주세요");
		</script>
	</c:if>
	<c:if test="${loginMessage eq 'pwdFail'}">
		<script type="text/javascript">
			alert("비밀번호를 확인해주세요");
		</script>
	</c:if>
	<c:if test="${loginMessage eq 'needLogin'}">
		<script type="text/javascript">
			alert("로그인 후 이용해주세요");
		</script>
	</c:if>
<form method="post" action="<c:url value='/member/login'/>">
<h1  style="text-align:center">로그인 하세요</h1>
	<table  align="center">
	    <tr>
	       <td width="200"><p align="right">아이디</td>
	       <td width="400"><input type="text" name="id"></td>
	    </tr>
	    <tr>
	        <td width="200"><p align="right">비밀번호</td>
	        <td width="400"><input type="password"  name="pwd"></td>
	    </tr>
	    <tr>
	        <td width="200"><p>&nbsp;</p></td>
	        <td width="400">
			<input type="submit" value="로그인">
			<input type="reset" value="다시입력">
	  		</td>
	    </tr>
	</table>
	<br><br>
	
	<div align="center">
    <a href="<c:url value='/member/insert'/>">회원가입</a>
    </div>
</form>
</body>
</html>
