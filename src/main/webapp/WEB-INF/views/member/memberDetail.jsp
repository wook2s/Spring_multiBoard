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
       <td width="400">${member.id}</td>
    </tr>
    <tr>
        <td width="200"><p align="right">비밀번호</td>
        <td width="400">${member.pwd}</td>
    </tr>
    <tr>
        <td width="200"><p align="right">이름</td>
        <td width="400">${member.name}</td>
    </tr>
    <tr>
        <td width="200"><p align="right">이메일</td>
        <td width="400">${member.email}</td>
    </tr>
    <tr>
        <td width="200"><p align="right">전화번호</td>
        <td width="400">${member.phone}</td>
    </tr>
    <tr>
        <td width="200"><p>&nbsp;</p></td>
        <td width="400">
  </td>
    </tr>
</table>
</form>
</body>
</html>
