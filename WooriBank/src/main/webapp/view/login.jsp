<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style>
/* CSS 스타일 지정 */
body {
   margin: 0;
   padding: 0;
}

.header {
   background-color: #FFFFFF;
   padding: 5px;
}

.section {
   padding: 50px;
}

.footer {
   background-color: #333;
   color: #fff;
   padding: 20px;
   text-align: center;
}

/* 메뉴바 */
.navbar-nav .nav-item {
   margin-left: 60px;
}

.navbar-nav .nav-link {
   color: #000000;
}

.navbar {
   padding-top: 10px;
   padding-bottom: 10px;
}

.navbar-brand {
   margin-left: 10px;
   margin-bottom: 10px;
}

.navbar-nav {
   margin-left: -50px;
}

.logo-img {
   margin-left: 40px;
   max-width: 100px;
   max-height: 100px;
}

.navbar {
   padding-top: 3px;
   padding-bottom: 3px;
}

.navbar-divider {
   border-top: 2px solid #009591;
   margin-top: 0;
   margin-bottom: 0;
   width: 100%;
}

.navbar-nav .nav-item:nth-child(6) {
   margin-left: 50px;
}

.section1 {
   background-color: #FFFFFF;
   height: 1100px;
}

.login {
   width: 100%;
   height: 150px;
   position: relative;
}

.title {
   left: 180px;
   top: 45px;
   position: absolute;
   text-align: center;
   color: black;
   font-size: 25px;
   font-family: Noto Sans KR;
}

.container {
   background-color: #ECECEC;
   width: 50%;
   padding: 20px;
   padding-top: 50px;
   margin-bottom: 30px;
}

h2 {
   display: flex;
   justify-content: center;
   align-items: center;
}

.form-label {
   font-weight: bold;
}

.form-control {
   padding: 20px;
   border: 1px solid #ccc;
   border-radius: 5px;
   box-sizing: border-box;
   background-color: #FEFAFA;
}

.btn-primary {
   margin-top: 10px;
   width: 180px;
   height: 45px;
   background-color: #009591 !important;
   color: #fff;
   border: none;
   padding: 20px 20px;
   border-radius: 5px;
   cursor: pointer;
   margin-left: 140px;
   background-color: #009591 !important;
}

.btn-primary:hover {
   background-color: #006f6c !important;
}

.signup-link {
   margin-top: 10px;
   margin-right: 5px;
}

.btn-link {
   color: #007bff;
   margin-right: 10px;
}

.logo-img2 {
   margin-top: 10px;
   transform: scale(1.1);
}

.mt-3 {
   margin-left: 180px;
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
<script type="text/javascript"
   src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
   Kakao.init("a833cca1f83f9b38586b88f445bd462f"); // API 키값

   function kakaoLogin() {
      Kakao.Auth
            .login({
               success : function(response) {
                  Kakao.API
                        .request({
                           url : '/v2/user/me',
                           success : function(response) {
                              var json = JSON.stringify(response);
                              var xhr = new XMLHttpRequest();
                              xhr.open("POST", "kakaoLogin.member");
                              xhr.setRequestHeader("Content-Type",
                                    "application/json");
                              xhr.onreadystatechange = function() {
                                 if (xhr.readyState === 4) {
                                    if (xhr.status === 200
                                          || xhr.status === 201) {
                                       if (JSON
                                             .parse(xhr.responseText).viewPage) {
                                          location.href = JSON
                                                .parse(xhr.responseText).viewPage;
                                       } else {
                                          var responseData = JSON
                                                .parse(xhr.responseText);
                                          console
                                                .log(responseData);
                                          console
                                                .log(responseData.name);
                                          console.log("hi");
                                          // 이제 responseData를 사용해서 필요한 작업을 수행할 수 있습니다.
                                          // 이후 성공적으로 처리되었다면 페이지를 이동
                                          //var responseDataStr = JSON.stringify(responseData);
                                          //var encodedData = encodeURIComponent(responseDataStr);
                                          //var url = "loginOk.jsp?responseData=" + encodedData;
                                          //location.href = "loginOk.jsp;"
                                          location.href = "mainHana.jsp"
                                       }
                                    } else {
                                       console
                                             .error(xhr.responseText);
                                    }
                                 }
                              };
                              xhr.send(json);
                           },
                           fail : function(error) {
                              alert(JSON.stringify(error));
                           }
                        });
               }
            });
   }
</script>
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
</head>
<body>
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
                     href="accountTransferInner.jsp">계좌이체</a></li>
                  <li class="nav-item"><a class="nav-link"
                     href="makeAccountSelect.jsp">계좌개설</a></li>
                  <li class="nav-item"><a class="nav-link" href="getAllList.board">고객센터</a></li>
                  <li class="nav-item dropdown"><a
                     class="nav-link dropdown-toggle" href="#" role="button"
                     data-bs-toggle="dropdown" aria-expanded="false"> 마이페이지 </a>
                     <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">로그인</a></li>
                        <li><a class="dropdown-item" href="#">회원가입</a></li>
                     </ul></li>
                  <li class="nav-item" style="margin-right: -50px;"><a
                     class="nav-link" href="#">로그인</a></li>

                  <li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>

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

      <div class="login">

         <div class="title">
            우리은행에 오신 걸 환영합니다.<br> 로그인 후 더 많은 서비스를 받아보세요.
         </div>
      </div>

      <div class="container">
         <h2>로그인</h2>
         <div class="row justify-content-center">
            <div class="col-md-6">
               <form id="loginForm" method="post">
                  <div class="mb-3">
                     <label for="username" class="form-label">아이디</label> <input
                        type="text" class="form-control" name="id" id="id">
                  </div>
                  <div class="mb-3">
                     <label for="password" class="form-label">비밀번호</label> <input
                        type="password" class="form-control" name="pw" id="pw">
                  </div>
                  <button type="submit" class="btn btn-primary">로그인</button>
                  <a href="javascript:kakaoLogin()"> <c:url var="logoUrl"
                        value="/img/ko/kakao_login_medium_narrow.png" />
                  </a> <a class="navbar-brand" href="javascript:kakaoLogin()"> <img
                     src="${logoUrl}" alt="로고" class="logo-img2">
                  </a>
                  <p class="mt-3">
                     <a href="/gwanjung/view/join.jsp" class="signup-link">회원가입
                        하러가기</a> <a href="mainHana.jsp" class="btn btn-link">메인화면으로 돌아가기</a>
                  </p>
               </form>

               <script>
                  document
                        .getElementById("loginForm")
                        .addEventListener(
                              "submit",
                              function(event) {
                                 var id = document
                                       .getElementById("id").value;
                                 var action = id === "admin" ? "login.admin"
                                       : "login.member";
                                 this.action = action;
                              });
               </script>


            </div>
         </div>
      </div>
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

         <p class="FooterDescription">하나은행오픈뱅킹입니다.</p>
      </div>

   </footer>

   <!-- 부트스트랩 JavaScript 연결 -->
   <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>