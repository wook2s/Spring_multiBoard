<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#container {
	width: 80%;
	text-align: center;
}

table {
	display: inline-block;
	max-width: 100%;
}

th {
	text-align: left;
	width: 200px;
}

tr {
	text-align: left;
}

#submitButton {
	text-align: right;
}

textarea {
	resize: none;
}
</style>
<script type="text/javascript">
	function button_event() {
		if (confirm("글을 등록하시겠습니까?") == true) { 
			document.form.submit();
		} else { 
			return;
		}
	}
</script>
</head>
<body>
	<div id="container">
		<form action="/myapp/board/insert" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<th>카테고리</th>
					<td><select name="categoryId">
							<option value="1" />일반 게시판
							</option>
							<option value="2" />사진 게시판
							</option>
							<option value="3" />영상 게시판
							</option>
					</select></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="id" value="${id}"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" rows="20" cols="80"
							style="border-radius: 10px 10px;"></textarea></td>
				</tr>
				<tr>
					<th>파일</th>
					<td><input type="file" name="file" /><br></td>
				</tr>
			</table>
			<div id="submitButton">
				<input type="submit" value="제출" onclick="button_event();"/><br>
			</div>
		</form>
	</div>
</body>
</html>