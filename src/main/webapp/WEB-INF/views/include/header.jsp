<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
		a{
			text-decoration: none;
		}
		a:link {
 		 color : black;
		}
		a:visited {
  		color : black;
		}
		#header{
			height : 100%;
			background-color: ivory;
			text-align: center;
			vertical-align: middle;
			line-height: 60px;
		}
</style>
</head>
<body>
<div id="header">
	<h1><a href="<c:url value='/'/>">Multiboard by jade</a></h1>
</div>
</body>
</html>