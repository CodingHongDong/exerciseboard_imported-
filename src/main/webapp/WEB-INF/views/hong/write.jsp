<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 부트스트랩, 제이쿼리 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>회원가입</title>
<style>
body {
	background-color: #F7FFD9;
}
img {
	margin: 10px;
	height: 100px;
	width: 100px;
	float: none;
}
</style>
<script type="text/javascript">
$(function() {
	
	   // id중복체크 변수, 비밀번호와 비밀번호확인이 같은지 체크 변수 -> 전역 변수 선언
	   var idCheck = false;
	   var pwCheck = false;
	
		// datepicker 클래스 이벤트 - 적정한 옵션을 넣어서 초기화 시켜 준다. 기본 datepicker()로 사용 가능
	   $(".datepicker").datepicker({
		   changeMonth: true,
		   changeYear: true,
		   dateFormat: "yy-mm-dd",
		   dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
		   monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ]
	   });

		// datepicker 클래스 이벤트
	   var now = new Date();
	   var startYear = now.getFullYear();
	   var yearRange = (startYear - 100) +":" + startYear ;
	   // datepicker - 초기값으로 셋팅하는 방법을 사용하면 2번째는 무시 당한다.
	   //원래 있던 datepicker에 option을 추가하는 방법이다.
	   $( ".datepicker" ).datepicker("option", {
	      "maxDate" : new Date(),
	      yearRange: yearRange
	   });
	   
	// 아이디 체크 이벤트
		$("#id").keyup(function() {
			
			idCheck = false;
			
			var id = $(this).val();
			
			// 4자 미만 처리
			if (id.length < 4) {
				$("#idCheckDiv").removeClass("alert-success");
				$("#idCheckDiv").addClass("alert-danger");
				$("#idCheckDiv").text("아이디는 4자 이상 영숫자이여야 합니다.");
				return;
			}
			// 20자 초과 처리
			if (id.length > 20) {
				$("#idCheckDiv").removeClass("alert-success");
				$("#idCheckDiv").addClass("alert-danger");
				$("#idCheckDiv").text("아이디는 20자 이내로 영숫자이여야 합니다.");
				return;
			}
			
			// 서버로 가서 아이디 중복 체크를 하러가자 -> url과 입력 데이터는 바뀌면 안된다-. ->Ajax
			// url /hong/idCheck
			// 가져온 데이터가 null이면 사용가능, 있으면 중복
			$("#idCheckDiv").load("/hong/idCheck?id="+id, function(result) {
				
				$("#idCheckDiv").removeClass("alert-success alert-danger");
				if(result.indexOf("가능한") == -1) {
					// 중복된 아이디인 경우 배경은 빨간색
					$("#idCheckDiv").addClass("alert-danger");
					idCheck = false;
				} else {
					// 사용가능한 아이디인 경우 배경은 파란색
					$("#idCheckDiv").addClass("alert-success");
					idCheck = true;
				}
			}); // 아이디 중복 처리 끝
						
		}); // 아이디 체크 이벤트 끝	
		
		// 비밀번호 처리 이벤트
		$("#pw").keyup(function() {
			pwCheck = false;
			var pw = $(this).val();
			//alert(pw.length);
			// 4자 미만 처리
			if (pw.length < 4) {
				$("#pwCheckDiv").removeClass("alert-success");
				$("#pwCheckDiv").addClass("alert-danger");
				$("#pwCheckDiv").text("비밀번호는 4자 이상이여야 합니다.");
				return;
			}
			// 20자 초과 처리
			if (pw.length > 20) {
				$("#pwCheckDiv").removeClass("alert-success");
				$("#pwCheckDiv").addClass("alert-danger");
				$("#pwCheckDiv").text("비밀번호는 20자 이내여야 합니다.");
				return;
			}
			
			// 4~20 사이 pw == pw2 같은지 체크
			var pw2 = $("#pw2").val();
			if(pw == pw2) {
				// pw와 pw2가 같은 경우
				$("#pwCheckDiv, #pw2CheckDiv").removeClass("alert-danger");
				$("#pwCheckDiv, #pw2CheckDiv").addClass("alert-success");
				$("#pwCheckDiv, #pw2CheckDiv").text("적당한 비밀번호입니다.");
				pwCheck = true;
			} else {
				// pw와 pw2가 같지 않은 경우
				$("#pwCheckDiv, #pw2CheckDiv").removeClass("alert-success");
				$("#pwCheckDiv, #pw2CheckDiv").addClass("alert-danger");
				$("#pwCheckDiv").text("비밀번호와 비밀번호 확인은 같아야 합니다.");
				if(pw2.length < 4)
					$("#pw2CheckDiv").text("비밀번호확인은 4자 이상이여야 합니다.");
				else if(pw2.length > 20)
					$("#pw2CheckDiv").text("비밀번호는 20자 이내여야 합니다.");
				else
					$("#pw2CheckDiv").text("비밀번호와 비밀번호 확인은 같아야 합니다.");
			}	
			
		});	
		
		// 비밀번호 확인 처리 이벤트
		$("#pw2").keyup(function() {
			pwCheck = false;
			var pw2 = $(this).val();
			//alert(pw2.length);
			// 4자 미만 처리
			if (pw2.length < 4) {
				$("#pw2CheckDiv").removeClass("alert-success");
				$("#pw2CheckDiv").addClass("alert-danger");
				$("#pw2CheckDiv").text("비밀번호확인은 4자 이상이여야 합니다.");
				return;
			}
			// 20자 초과 처리
			if (pw2.length > 20) {
				$("#pw2CheckDiv").removeClass("alert-success");
				$("#pw2CheckDiv").addClass("alert-danger");
				$("#pw2CheckDiv").text("비밀번호는 20자 이내여야 합니다.");
				return;
			}
			
			// 4~20 사이 pw와 같은지 체크
			var pw = $("#pw").val();
			if(pw == pw2) {
				// pw와 pw2가 같은 경우
				$("#pw2CheckDiv, #pwCheckDiv").removeClass("alert-danger");
				$("#pw2CheckDiv, #pwCheckDiv").addClass("alert-success");
				$("#pw2CheckDiv, #pwCheckDiv").text("적당한 비밀번호입니다.");
				pwCheck = true;
			} else {
				// pw와 pw2가 같지 않은 경우
				$("#pw2CheckDiv, #pwCheckDiv").removeClass("alert-success");
				$("#pw2CheckDiv, #pwCheckDiv").addClass("alert-danger");
				$("#pw2CheckDiv").text("비밀번호와 비밀번호 확인은 같아야 합니다.");
				if(pw.length < 4)
					$("#pwCheckDiv").text("비밀번호확인은 4자 이상이여야 합니다.");
				else if(pw.length > 20)
					$("#pwCheckDiv").text("비밀번호는 20자 이내여야 합니다.");
				else
					$("#pwCheckDiv").text("비밀번호와 비밀번호 확인은 같아야 합니다.");
			}
			
		}); 		
		// 비밀번호 처리 이벤트의 끝
		
		// 회원가입 이벤트
		$("#writeForm").submit(function() {
			
			//alert("아이디 체크 : " + idCheck + "\n비밀번호 체크 : " + pwCheck)
			
			// 아이디 중복체크 - 사용 가능한 아이디 인지 확인
			if(!idCheck) {
				alert("중복이 되지 않는 적당한 형식의 아이디를 사용하셔야 합니다.");
				$("#id").focus();
				// form 전송을 무시시킨다.
				return false;
			}
			// 비밀번호와 비밀번호 확인
			if(!pwCheck) {
				alert("비밀번호와 비밀번호 확인의 길이가 4~20이여야 하고 같아야 합니다.");
				$("#pw").focus();
				// form 전송을 무시시킨다.
				return false;
			}
			
			//return false; 
		});
});
</script>
</head>
<body>
<script src="<c:url value="/resources/user/js/bootstrap.min.js"/>"></script>

<!-- 로고 -->
<header>
	<h1 class="text-center">
		<a href="home.do"><img src="<c:url value="/resources/user/dog.jpg"/>"></a>
	</h1>
</header>

<div class="container">
	<form action="write.do" method="post" id="writeForm">
		<div class="form-group">
			<label>아이디</label>
			<input id="id" name="id" required="required" pattern="[A-Za-z0-9]{4,20}" placeholder="아이디 입력"
			class="form-control" autocomplete="off">
			<div id="idCheckDiv" class="alert alert-danger">아이디는 4자 이상 입력하셔야 합니다.</div>
		</div>
		<div class="form-group">
			<label>비밀번호</label>
			<input id="pw" name="pw" required="required" pattern=".{4,20}" placeholder="비밀번호 입력"	class="form-control" type="password">
			<div id="pwCheckDiv" class="alert alert-danger">비밀번호는 4자 이상이여야 합니다.</div>
		</div>
		<div class="form-group">
			<label>비밀번호 확인</label>
			<input id="pw2" name="pw2" required="required" pattern=".{4,20}" placeholder="비밀번호 확인"
			class="form-control" type="password">
			<div id="pw2CheckDiv" class="alert alert-danger">비밀번호는 4자 이상이여야 합니다.</div>
		</div>
		<div class="form-group">
			<label>이름</label>
			<input id="name" name="name" required="required" pattern="[가-힣]{2,10}" placeholder="이름 입력"
			class="form-control">
		</div>
		<div class="form-group">
			<label>생년월일</label>
			<input id="birth" name="birth" required="required" placeholder="yyyy-MM-dd"
			class="form-control datepicker" autocomplete="off">
		</div>
		<div class="form-group">
			<label>이메일</label>
			<input id="email" name="email" required="required" placeholder="email 입력"
			class="form-control" type="email">
		</div>
		
		<button class="btn btn-default">등록</button>
		<button class="btn btn-default" type="reset">새로입력</button>
		<button class="btn btn-default" type="button" onclick="location.href='home.do'">취소</button>
	</form>
</div>
</body>
</html>