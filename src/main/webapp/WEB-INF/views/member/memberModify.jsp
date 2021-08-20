<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>회원 정보</title>
<body>

<form method="post"   action="<c:url value='/member/insert'/>">
<h1  style="text-align:center">회원 정보</h1>
<table  align="center">
    <tr>
       <td width="200"><p align="right">아이디</td>
       <td width="400"><input type="text" name="id" required="required"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">비밀번호</td>
        <td width="400"><input type="password"  name="pwd" required="required"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">이름</td>
        <td width="400"><p><input type="text"  name="name" required="required"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">이메일</td>
        <td width="400"><p><input type="text"  name="email" required="required"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">전화번호</td>
        <td width="400"><p><input type="text"  name="phone" required="required"></td>
    </tr>
    <tr>
        <td width="200"><p>&nbsp;</p></td>
        <td width="400">
	<input type="submit" value="가입하기">
	<input type="reset" value="다시입력">
  </td>
    </tr>
</table>
</form>
</body>
</html>
