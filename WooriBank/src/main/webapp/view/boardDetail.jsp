<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.MemberDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세 페이지</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
	background-color: #f5f5f5;
}

/* 메뉴바 */
.navbar-nav .nav-item {
	margin-left: 2rem;
}

.navbar-nav .nav-link {
	color: #000000;
}

.navbar {
	padding-top: 1rem;
	padding-bottom: 1rem;
}

.navbar-brand {
	margin-bottom: 1rem;
}

.navbar-nav {
	margin-left: -1.5rem;
}

.logo-img {
	margin-left: 5rem;
	max-width: 100px;
	max-height: 100px;
}

.navbar {
	padding-top: 0.3rem;
	padding-bottom: 0.3rem;
}

.navbar-divider {
	border-top: 2px solid #009591;
	margin-top: 0;
	margin-bottom: 0;
	width: 100%;
}

.navbar-nav .nav-item:nth-child(6) {
	margin-left: 1.5rem;
}

.section1 {
	background-color: #FFFFFF;
	height: 800px;
}

.title {
	text-align: center;
	color: black;
	font-size: 30px;
	font-family: Noto Sans KR;
	margin: 50px;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	border: 1px solid #ddd;
}

.container h2 {
	margin-bottom: 20px;
	color: #333;
}

.container table {
	width: 100%;
}

.container th, .container td {
	padding: 5px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

.container th {
	background-color: #1F9FE4;
	font-weight: bold;
	width: 15%;
	text-align: center;
}

.container td {
	width: 70%;
	height: 50px; /* 변경된 값 */
}

.container tr:hover {
	background-color: #f9f9f9;
}

.form-container {
	margin-top: 20px;
	text-align: center;
}

.form-container input[type="text"], .form-container textarea {
	width: 100%;
	padding: 10px;
	font-size: 16px; /* 변경된 값 */
	box-sizing: border-box;
}

.btn-container {
	margin-top: 20px;
	text-align: center;
}

.btn-container .btn-group {
	display: flex;
	justify-content: center;
}

.btn-container input[type="submit"], .btn-container a, .btn-container button
	{
	margin: 5px;
	padding: 10px 20px;
	font-size: 16px;
	border: none;
	border-radius: 4px;
	background-color: #1F9FE4;
	color: #fff;
	cursor: pointer;
	text-decoration: none;
}

.btn-container a {
	border: 1px solid #1F9FE4;
	background-color: transparent;
	color: #1F9FE4;
}

.btn-container a:hover, .btn-container input[type="submit"]:hover,
	.btn-container button:hover {
	background-color: #ff80ab;
}

a {
	display: block;
	margin-top: 20px;
	text-align: center;
	text-decoration: none;
	color: #333;
}
/* 푸터 */
.BankFooter {
	background-color: #f7f7f7;
	padding: 20px;
	text-align: center;
}

.BankFooterContent {
	max-width: 800px;
	margin: 0 auto;
	margin-top: 30px;
	margin-left: -50px;
}

.FooterLinks {
	list-style: none;
	padding: 0;
	margin-bottom: 10px;
}

.FooterLinks li {
	display: inline-block;
	margin-right: 10px;
}

.FooterLinks li:last-child {
	margin-right: 0;
}

.FooterLinks a {
	color: #333;
	text-decoration: none;
	font-size: 14px;
}

.FooterDescription {
	color: #666;
	font-size: 12px;
}

.ContactItem {
	width: 250px;
	height: 40px;
	position: absolute;
	margin-right: 150px;
	right: 0;
}

.ContactTitle {
	width: 50px;
	height: 20px;
	text-align: right;
	color: #009591;
	font-size: 14px;
	font-family: Noto Sans KR;
	font-weight: 350;
	line-height: 10px; /* Increase line-height to add spacing */
	word-wrap: break-word;
	position: absolute;
	top: 2px;
	right: 30px;
}

.ContactNumber {
	width: 122.17px;
	height: 36px;
	text-align: right;
	color: #333333;
	font-size: 24px;
	font-family: Noto Sans KR;
	font-weight: 350;
	line-height: 24px;
	word-wrap: break-word;
	position: absolute;
	top: 19px;
}

.ContactNumber.Strong15881111 {
	left: 0;
	right: 30px;
}

.ContactSeparator {
	width: 9.12px;
	height: 36px;
	text-align: right;
	color: #DDDDDD;
	font-size: 24px;
	font-family: Noto Sans KR;
	font-weight: 350;
	line-height: 24px;
	word-wrap: break-word;
	position: absolute;
	top: 19px;
	left: 124.68px;
}

.ContactNumber.Strong15991111 {
	left: 105px;
}
</style>
<script>
	function selectAll() {
		var checkboxes = document.getElementsByName('postIds');
		var selectAllCheckbox = document.getElementById('selectAllCheckbox');

		for (var i = 0; i < checkboxes.length; i++) {
			checkboxes[i].checked = selectAllCheckbox.checked;
		}
	}
</script>
<!-- 부트스트랩 연결 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
</head>
<body>
	<%
	MemberDTO dto = (MemberDTO) application.getAttribute("dto");
	%>
	<div class="header">
		<!-- 메뉴바 내용 -->
		<nav class="navbar navbar-expand-lg">
			<div class="container-fluid">
				<c:url var="logoUrl" value="/img/woori.jpg" />
				<a class="navbar-brand" href="mainHana.jsp"> <img
					src="${logoUrl}" alt="로고" class="logo-img">
				</a>

				<div class="collapse navbar-collapse justify-content-center"
					id="navbarSupportedContent">
					<ul class="navbar-nav mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="allAccount.jsp">계좌조회</a></li>
						<li class="nav-item"><a class="nav-link"
							href="accountTransferOuter.jsp">계좌이체</a></li>
						<li class="nav-item"><a class="nav-link"
							href="makeAccountSelect.jsp">계좌개설</a></li>
						<li class="nav-item"><a class="nav-link" href="">거래내역조회</a></li>
						<li class="nav-item"><a class="nav-link"
							href="getAllList.board">고객센터</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> 마이페이지 </a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">

								<li><a class="dropdown-item" href="join.jsp">회원가입</a></li>
							</ul></li>
						<li class="nav-item">
							<%
							if (dto == null) {
							%>
						
						<li><a class="nav-link" href="login.jsp">로그인</a></li>
						<%
						} else {
						%>
						<!-- <script>loginMessage();</script> -->
						<li><a class="nav-link" href="logout.member">로그아웃</a></li>
						<%
						}
						%><li class="nav-item"><a class="nav-link" href="join.jsp">회원가입</a></li>
					</ul>
				</div>

				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</nav>


	</div>

	<hr class="navbar-divider">



	<div class="section1">

		<div class="title">게시물 상세 내용</div>

		<c:if test="${not empty post}">
			<div class="container">
				<h2>게시물 정보</h2>
				<form action="update.board" method="post">
					<input type="hidden" name="id" value="${post.id}"> <input
						type="hidden" name="category" value="${post.category}"> <input
						type="hidden" name="levelNum" value="${post.levelNum}"> <input
						type="hidden" name="blank" value="${post.blank}">
					<table>
						<tr>
							<th style="text-align: center;">번호</th>
							<td style="font-size: 20px;">${post.id}</td>
						</tr>
						<tr>
							<th style="text-align: center;">조회수</th>
							<td style="font-size: 20px;">${post.views}</td>
						</tr>
						<tr>
							<th style="text-align: center;">이름</th>
							<td style="font-size: 20px;"><input type="text" name="writer" value="${post.writer}"
								required style="font-size: 16px;"></td>
						</tr>
						<tr>
							<th style="text-align: center;">제목</th>
							<td><input type="text" name="title" value="${post.title}"
								required style="font-size: 20px;"></td>
						</tr>
						<tr>
							<th style="text-align: center;">내용</th>
							<td><textarea name="content" required rows="5" style="width: 100%; font-size: 30px">${post.content}</textarea>
							</td>
						</tr>
					</table>
					<div class="btn-container">
						<div class="btn-group">
							<input type="submit" value="수정"> <a
								href="getAllList.board">목록보기</a> <a
								href="delete.do?id=${post.id}">삭제</a>
							<%
							if (dto.getMemberId().equals("admin")) {
							%>
							<button type="submit" formaction="boardReply.jsp"
								formmethod="post" class="btn-reply">답글</button>
							<%
							}
							%>
						</div>
					</div>
				</form>
			</div>
		</c:if>
	</div>



	<footer class="BankFooter">
		<div class="BankFooterContent">
			<ul class="FooterLinks">
				<li><a href="#">이용약관</a></li>
				<li><a href="#">개인정보처리방침</a></li>
				<li><a href="#">보안정책</a></li>
				<li><a href="#">고객센터</a></li>
			</ul>
			<div class="ContactItem">
				<div class="ContactTitle">고객센터</div>
				<div class="ContactNumber Strong15881111">1588-1111</div>
				<div class="ContactSeparator">/</div>
				<div class="ContactNumber Strong15991111">1599-1111</div>
			</div>

			<p class="FooterDescription">우리은행 오픈뱅킹입니다.</p>
		</div>

	</footer>

	<!-- 부트스트랩 JavaScript 연결 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
