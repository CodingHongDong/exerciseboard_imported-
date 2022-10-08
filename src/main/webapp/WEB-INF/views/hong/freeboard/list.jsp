<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Free Board</title>
<!-- 부트스트랩, 제이쿼리 -->
<meta name="viewport" content="with=divice-width,initial-scale=1">
<link rel="stylesheet" 
		href="<c:url value="/resources/user/css/bootstrap.min.css" />">
<script src="http://code.jquery.com/jquery-2.2.3.min.js"></script>
<script src="<c:url value="/resources/user/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript">
$(function() {
	$(".dataRow").click(function() {
		var no = $(this).find(".no").text();
		location = "view.do?no=" + no + "&inc=1"
				+ "&page=${pageObject.page}"
				+ "&perPageNum=${pageObject.perPageNum}"
				+ "&key=${pageObject.key}"
				+ "&word=${pageObject.word}"
	});
	
	// perPageNum 데이터 변경 이벤트 처리 -> jQuery에 대한 이벤트
	$("#perPageNumSelect").change(function() {
		$("#perPageNumForm").submit();
	});
	
});
</script>
<style type="text/css">
body {
	background-color: #e6e5dc;
}

.dataRow:hover {
	background: #eee;
	cursor: pointer;
	}

img {
	margin: 10px;
	height: 100px;
	width: 100px;
	float: none;
}
</style>
</head>
<body>

<!-- 로고 -->
<header>
	<h1 class="text-center">
		<a href="http://localhost/hong/home.do"><img src="<c:url value="/resources/user/dog.jpg"/>"></a>
	</h1>
</header>

<div class="container">
<h2>Free 게시판 > 리스트</h2>
<div class="row" style="margin-bottom: 5px;">
	<!-- 검색 기능 -->
	<div class="col-md-8">
		<form class="form-inline">
		<input type="hidden" name="perPageNum" value="${pageObject.perPageNum}">
			<div class="input-group">
			  	<select name="key" class="form-control">
			  		<option value="t" ${(pageObject.key == "t")? "selected" : ""}>제목</option>
			  		<option value="c" ${(pageObject.key == "c")? "selected" : ""}>내용</option>
			  		<option value="w" ${(pageObject.key == "w")? "selected" : ""}>작성자</option>
			  		<option value="tc" ${(pageObject.key == "tc")? "selected" : ""}>제목/내용</option>
			  		<option value="tw" ${(pageObject.key == "tw")? "selected" : ""}>제목/작성자</option>
			  		<option value="cw" ${(pageObject.key == "cw")? "selected" : ""}>내용/작성자</option>
			  		<option value="tcw" ${(pageObject.key == "tcw")? "selected" : ""}>전체</option>
			  	</select> 			
			</div>
		  <div class="input-group">
		    <input type="text" class="form-control" placeholder="Search" name="word" value="${pageObject.word}">
		    <div class="input-group-btn">
		      <button class="btn btn-default" type="submit">
		        <i class="glyphicon glyphicon-search"></i>
		      </button>
		    </div>
		  </div>
		</form>
	</div>
	<!-- 한 페이지 당 보여주는 데이터 갯수 -->
	<div class="col-md-4 text-right">
		<form action="list.do" class="form-inline" id="perPageNumForm">
			<input type="hidden" name="page" value="1">
			<input type="hidden" name="key" value="${pageObject.key}">
			<input type="hidden" name="word" value="${pageObject.word}">
			<div class="form-group">
				<label> 1페이지 당 개수
					<select name="perPageNum" class="form-control" id="perPageNumSelect">
						<option ${(pageObject.perPageNum == 5)? "selected" : "" }>5</option>
						<option ${(pageObject.perPageNum == 10)? "selected" : "" }>10</option>
						<option ${(pageObject.perPageNum == 15)? "selected" : "" }>15</option>
						<option ${(pageObject.perPageNum == 20)? "selected" : "" }>20</option>
					</select>
				</label>
			</div>
		</form>	
	</div>
</div>

<table class="table">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	
	<c:forEach items="${vo}" var="vo">
	<tr class="dataRow">
		<td class="no">${vo.no}</td>
		<td>${vo.title}</td>
		<td>${vo.memberId}</td>
		<td><fmt:formatDate value="${vo.regDate}" pattern="yyyy-MM-dd"/> </td>
		<td>${vo.hit}</td>
	</tr>
	</c:forEach>
	
	<tr>
		<td colspan="5">
			<a href="write.do?perPageNum=${pageObject.perPageNum}" class="btn btn-default">글쓰기</a>
		</td>
	</tr>
	
	<c:if test="${pageObject.totalPage > 1 }">
	<!-- 전체 페이지가 2페이지 이상이면 보여주는 부분 -->
		<tr>
			<td colspan="5">
				<pageNav:pageNav listURI="list.do" 
					pageObject="${pageObject }" />
			</td>
		</tr>
	</c:if>
	
</table>
</div>
</body>
</html>