<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>

<!-- 부트스트랩, 제이쿼리 -->
<meta name="viewport" content="with=divice-width,initial-scale=1">
<link rel="stylesheet" 
		href="<c:url value="/resources/user/css/bootstrap.min.css" />">
<script src="http://code.jquery.com/jquery-2.2.3.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/resources/user/js/bootstrap.min.js"/>"></script>

<style>
nav{
	margin: 10px;
}

body {
	background-color: #F7FFD9;
}

ul {
	list-style: none;
	text-align: center;
	border-top: 2px solid #41D97E;
	border-bottom: 2px solid  #41D97E;
	padding: 10px 0;
}

ul li {
	display: inline;
	text-transform: uppercase;
	padding: 0 10px;
	letter-spacing: 10px;
}

ul li a {
	text-decoration: none;
	color: black;
}

ul li a:hover {
	text-decoration: underline;
	color: white;
}

</style>
</head>
<body>

<!-- 로그인, 회원가입, 로그아웃 -->
<br>
<div style="text-align:right; color: #3B3538;">
<c:choose>
	<c:when test="${empty login}">
		<a style="color:#5ADB87" href="login.do">login</a>
		<a style="color:#5ADB87" href="write.do">join</a>
	</c:when>
	<c:otherwise>
		<b>${!empty login}</b>
		<a style="color: #D9418C" href="logout.do">logout</a>
	</c:otherwise>
</c:choose>
</div>

<!-- 로고 -->
<header>
	<h1 class="text-center">
		<a href="home.do"><img src="<c:url value="/resources/user/dog.jpg"/>"></a>
	</h1>
</header>

<!-- nav -->
<nav>
	<ul>
		<li><b><a href="fitnessboard/list.do">fitness</a></b></li>
		<li><b><a href="runningboard/list.do">running</a></b></li>
		<li><b><a href="pilatesboard/list.do">pilates</a></b></li>
		<li><b><a href="freeboard/list.do">자유게시판</a></b></li>	
	</ul>
</nav>

</body>
</html>