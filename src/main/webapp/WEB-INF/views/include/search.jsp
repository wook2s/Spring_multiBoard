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
		#search{
			text-align: center;
		}
		#area{
			width: 200px;
			height: 20px;
		}
		#button{
			width: 50px;
			height: 25px;
		}
	</style>

</head>
<body>
<div id="search">
	<form action="<c:url value='/board/search'/>" method="POST">
	<select name="categoryId">
  		<option value="1">일반 게시판</option>
  		<option value="2">사진 게시판</option>
  		<option value="3">영상 게시판</option>
	</select>
	<select name="option">
  		<option value="title">제목</option>
  		<option value="content">게시글</option>
  		<option value="id">작성자</option>
	</select>
	<input type="hidden" name="nowPage" value="1">
	<input id="area" type="text" name="word" required="required">
	&nbsp;
	<input id="button"type="submit" value="검색">
	</form>
</div>
</body>
</html>