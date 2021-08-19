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
	<form action="/myapp/board/modify/${board.boardId}" method="post" enctype="multipart/form-data">
		카테고리<input type="text" name="categoryId" value="${board.categoryId}"/>	<br>
		제목<input type="text" name="title" value="${board.title}"/>	<br>
		아이디<input type="text" name="id" value="${board.id}" readonly="readonly"/><br>	
		내용<input type="text" name="content" value="${board.content}"/><br>	
		파일<input type="file" name="file" value="${board.fileName}"/><br>
		<input type="hidden" name="preFileName" value="${board.fileName}"/>	
		<input type="submit" value="수정"/><br>	
	</form>
	
</body>
</html>