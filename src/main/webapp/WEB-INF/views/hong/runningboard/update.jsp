<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="with=divice-width,initial-scale=1">
<link rel="stylesheet" 
		href="<c:url value="/resources/user/css/bootstrap.min.css" />">
<script src="http://code.jquery.com/jquery-2.2.3.min.js"></script>
<script src="<c:url value="/resources/user/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<meta charset="UTF-8">
<style type="text/css">
body {
	background-color: #46b8a1;
}
</style>
<title>Running Board</title>
</head>
<body>
<div class="container">
<h2>Running Board > 글수정</h2>
<form action="update.do" method="post">
<input type="hidden" name="page" value="${param.page }">
<input type="hidden" name="perPageNum" value="${param.perPageNum }">
<table class="table">
	<tr>
		<th>번호</th>
		<td><input name="no" value="${vo.no}" readonly="readonly"></td>
	</tr>
	
	<tr>
		<th>제목</th>
		<td><input name="title" value="${vo.title}"></td>
	</tr>
	
	<tr>
		<th>내용</th>
		<td><textarea rows="5" style="width: 600px;" name="content">${vo.content}</textarea></td>
	</tr>
	
	<tr>
		<th>작성자</th>
		<td><input name="memberId" value="${vo.memberId}"></td>
	</tr>
	
	<tr>
		<td colspan="2">
			<button class="btn btn-default">수정</button>
			<button type="reset" class="btn btn-default">새로입력</button>
			<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>