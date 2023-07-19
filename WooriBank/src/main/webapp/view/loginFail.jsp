<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String message = (String) session.getAttribute("loginResult"); %>

<script>
  // 메시지를 alert로 표시
  alert("<%= message %>");
  
  // 다른 페이지로 이동
  location.href = "login.jsp";
</script>

</body>
</html>