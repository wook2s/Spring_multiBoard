<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>회원 등록창</title>
   
   <style type="text/css">
   </style>
   
</head>   
<body>
<c:url var = "actionURL" value='/member/insert' scope="page"/>
<form:form action="${actionoURL}" modelAttribute="member">
<h1  style="text-align:center">회원 등록창</h1>
<table  align="center">
    <tr>
       <td width="200"><p align="right">아이디</td>
       <td width="400"><input type="text" name="id" required="required" autocomplete = "off" value="${member.id}"><form:errors path="id"/></td>
    </tr>
    <tr>
        <td width="200"><p align="right">비밀번호</td>
        <td width="400"><input type="password"  name="pwd" required="required" autocomplete="new-password" value="${member.pwd}"><form:errors path="pwd"/></td>
    </tr>
    <tr>
        <td width="200"><p align="right">이름</td>
        <td width="400"><input type="text"  name="name" required="required" value="${member.name}"><form:errors path="name"/></td>
    </tr>
    <tr>
        <td width="200"><p align="right">이메일</td>
        <td width="400"><input type="text"  name="email" required="required" value="${member.email}"><form:errors path="email"/></td>
    </tr>
    <tr>
        <td width="200"><p align="right">전화번호</td>
        <td width="400"><input type="text"  name="phone" required="required" value="${member.phone}"><form:errors path="phone"/></td>
    </tr>
    <tr>
        <td width="200"><p>&nbsp;</p></td>
        <td width="400">
	<input type="submit" value="가입하기">
	<input type="reset" value="다시입력">
  </td>
    </tr>
</table>
</form:form>
</body>
</html>
