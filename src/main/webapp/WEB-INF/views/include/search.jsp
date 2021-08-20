<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<select name="categoryId">
  		<option value="1">일반 게시판</option>
  		<option value="2">영상 게시판</option>
  		<option value="3">사진 게시판</option>
	</select>
	<select name="searchOption">
  		<option value="content">게시글</option>
  		<option value="writer">작성자</option>
	</select>
	<input id="area" type="text" name="search">&nbsp;<input id="button"type="submit" value="검색">
</div>
</body>
</html>