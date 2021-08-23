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
	#container{
		width: 80%;
		text-align: center;
	}
	table{
		display: inline-block;
		max-width: 100%;
	}
	th{
		text-align: left;
		width: 200px;
	}
	tr{
		text-align: left;
	}
	#submitButton{
		text-align: right;
	}
	textarea{
		resize: none;
	}

</style>
</head>
<body>
	<div id="container">
	<form action="/myapp/board/modify/${board.categoryId}/${board.boardId}" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>카테고리</th>
				<td>
					<!-- <select name="categoryId">
						<option value="1" selected="selected"/>일반 게시판</option>
						<option value="2"/>사진 게시판</option>
						<option value="3"/>영상 게시판</option>
					</select> -->
					${board.categoryId}
					<input type="hidden" name="categoryId" value="${board.categoryId}"/>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="id" value="${board.id}" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${board.title}"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="20" cols="80" style="border-radius: 10px 10px;">${board.content}</textarea></td>
			</tr>
			<tr>
				<th>파일</th>
				<td><input type="file" name="file" value="${board.fileName}"/><br>	</td>
			</tr>
		</table>
		<input type="hidden" name="preFileName" value="${board.fileName}"/>	
		<div id="submitButton">
		<input type="submit" value="수정" onclick="return confirm('${board.boardId}번 글을 등록하시겠습니까?');"/><br>	
		</div>
	</form>
</div>	
	
</body>
</html>