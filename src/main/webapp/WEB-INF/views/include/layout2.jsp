<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <style type="text/css">
    	#tilesHeader{
    		width: 100%;
    		height: 70px
    	}
    	#tilesSearch{
    		width: 100%;
    		height: 30px
    	}
    	#tilesBody{
    		float:left;
    		width: 100%;
    		margin-right: 1%;
    		height: 500px;
    		
    	}
    	#tilesFooter{
    		background: #313131;	
    		clear : both;
    		width: 100%;
    		height: 100px
    	}
    
    
    </style>
</head>

<body>
<div id="tilesHeader">
	<tiles:insertAttribute name="header"/>
</div>
<hr>
<div id="tilesSearch">
	<tiles:insertAttribute name="search"/>
</div>
<hr>
<div id="tilesBody">
	<tiles:insertAttribute name="body"/>
</div>
<div id="tilesFooter">
	<tiles:insertAttribute name="footer"/>
</div>
	

    
</body>

</html>