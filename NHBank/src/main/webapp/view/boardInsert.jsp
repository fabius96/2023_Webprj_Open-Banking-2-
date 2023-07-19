<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.MemberDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

.container {
	width: 600px;
	margin: 0 auto;
	padding: 20px;
	background-color: #f8f8f8;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	margin-top: 30px;
	margin-bottom: 20px;
	color: #e91e63;
}

.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	font-weight: bold;
	margin-bottom: 10px;
	color: #333;
}

.form-group input[type="text"], .form-group textarea {
	width: 90%;
	padding: 10px;
	border: 1px solid #dddddd;
	border-radius: 4px;
	font-size: 16px;
	color: #333;
}

.button-container {
	text-align: center;
	margin-top: 30px;
}

.button-container button {
	display: inline-block;
	padding: 10px 20px;
	background-color: #e91e63;
	color: #ffffff;
	text-decoration: none;
	border: none;
	border-radius: 4px;
	font-size: 16px;
	font-weight: bold;
	cursor: pointer;
	margin-right: 10px;
}

.button-container button:last-child {
	margin-right: 0;
}

.button-container button:hover {
	background-color: #d81b5f;
}
</style>
</head>
<body>
	<%
	MemberDTO dto = (MemberDTO) application.getAttribute("dto");
	%>
	<div class="container">
		<h1>게시글 작성</h1>

		<form action="insert.board" method="post">

			<div class="form-group">
				<label for="writer">작성자</label> <input type="text" id="writer"
					name="writer" value=<%=dto.getMemberId() %> required style="font-size: 25px">
			</div>
			<div class="form-group">
				<label for="title">제목</label> <input type="text" id="title"
					name="title" required style="font-size: 25px">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea id="content" name="content" rows="5" required style="font-size: 30px"></textarea>
			</div>
			<div class="button-container">
				<button type="submit" value="Submit">완료</button>

				<button type="button" onclick="location.href='boardGetAllList.jsp'">목록으로
					돌아가기</button>
			</div>
		</form>
	</div>
</body>
</html>
