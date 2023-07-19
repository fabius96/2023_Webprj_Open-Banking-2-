<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 작성</title>
<style>
/* 스타일링을 원하는 대로 추가해주세요 */
body {
	font-family: Arial, sans-serif;
	margin: 20px;
	background-color: #f5f5f5;
}

.title {
	text-align: center;
	color: black;
	font-size: 30px;
	font-family: Noto Sans KR;
	margin: 5%;
}

table {
	width: 80%;
	margin: 0 auto;
	background-color: #fff;
	border-collapse: collapse;
	border: 1px solid #ddd;
}

.form-container {
	width: 50%;
	margin: 0 auto;
}

.form-container table {
	margin: 0 auto;
}

.form-container th, .form-container td {
	padding: 10px;
	border: 1px solid #ccc;
}

.form-container th {
	background-color: #f2f2f2;
	font-weight: bold;
}

.form-container input[type="text"] {
	width: 100%;
	padding: 5px;
	box-sizing: border-box;
}

.form-container textarea {
	width: 100%;
	height: 100px;
	padding: 5px;
	box-sizing: border-box;
}

.btn-container {
	margin-top: 20px;
	text-align: center;
}

.btn-container input[type="submit"] {
	margin: 5px;
	padding: 10px 20px;
	font-size: 16px;
	border: none;
	background-color: #1F9FE4;
	color: #fff;
	cursor: pointer;
}

.btn-container a {
	display: inline-block;
	margin: 5px;
	padding: 10px 20px;
	font-size: 16px;
	border: 1px solid #1F9FE4;
	background-color: transparent;
	color: #1F9FE4;
	text-decoration: none;
	cursor: pointer;
}

.btn-container a:hover {
	background-color: #1F9FE4;
	color: #fff;
}
</style>
</head>

<body>
	<div class="title">답글 작성</div>
	<div class="form-container">
		<form action="reply.board" method="post">
			<input type="hidden" name="parentPostId"
				value="<%=request.getParameter("id") %>">
			<table>
				<tr>
					<th>이름</th>
					<td><input type="text" name="writer" value= "admin" required style="font-size: 25px"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" required style="font-size: 25px"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" required style="width: 100%; font-size: 30px"></textarea></td>
				</tr>
			</table>
			<div class="btn-container">
				<input type="submit" value="작성"> <a href="getAllList.board">목록보기</a>
			</div>
		</form>
	</div>
</body>
</html>
