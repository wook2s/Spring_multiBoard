<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 업로드 화면</h1>
	<form action="/myapp/board/insert" method="post" enctype="multipart/form-data">
		카테고리<input type="text" name="categoryId"/>	<br>
		제목<input type="text" name="title"/>	<br>
		아이디<input type="text" name="id" value="${id}" readonly="readonly"/><br>	
		내용<input type="text" name="content"/><br>	
		파일<input type="file" name="file"/><br>	
		<input type="submit" value="제출"/><br>	
	</form>
	
</body>
</html>