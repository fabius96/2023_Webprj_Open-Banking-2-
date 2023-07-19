<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
    
    boolean joinResult = (boolean)request.getAttribute("result");
    String alertMessage = "";
    String redirectPage = "mainHanaAdmin.jsp";
    if (joinResult) {
        alertMessage = "가입승인이 완료되었습니다.";
    } else {
        alertMessage = "ID를 다시 입력해주세요.";
    }
    %>
    
    <script>
        alert("<%= alertMessage %>");
        location.href = "<%= redirectPage %>";
    </script>
</body>
</html>