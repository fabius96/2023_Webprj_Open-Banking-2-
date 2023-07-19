<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.MemberDTO"%>

<!DOCTYPE html>
<html>
<head>
<title>웹 페이지</title>
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
   background: linear-gradient(to bottom, #C1E9E8, #FFFFFF);
   height: 350px;
}

.hello-word {
   margin-left: 5%;
   text-align: left;
}

.fw-bold {
   padding-top: 50px;
   font-size: 25px;
}

.fw-normal {
   margin-top: 10px;
}

.DivQuickButtonWrap {
   display: flex;
   justify-content: flex-end;
   margin-right: 200px;
   margin-bottom: 10px;
}

.Link {
   width: 150px;
   height: 100px;
   position: relative;
   border-radius: 10px;
   border: 1px solid #CCCCCC;
   margin-right: 20px;
   margin-top: -50px;
}

.Link1 {
   width: 200px;
   height: 80px;
   position: relative;
   border-radius: 10px;
   border: 1px solid #CCCCCC;
   margin-left: 5%;
   margin-top: -50px;
}

.LinkText {
   position: absolute;
   left: 50%;
   top: 50%;
   transform: translate(-50%, -50%);
   color: white;
   font-size: 20px;
   font-family: Noto Sans KR;
   font-weight: 700;
   line-height: 18px;
   word-wrap: break-word;
}

.LinkText1 {
   position: absolute;
   left: 50%;
   top: 50%;
   transform: translate(-50%, -50%);
   color: #595959;
   font-size: 20px;
   font-family: 'Noto Sans KR', sans-serif;
   font-weight: 400;
   line-height: 29px;
   white-space: nowrap;
   text-align: center;
}

.Link:first-child {
   background: #009591;
}

.Link:nth-child(2) {
   background: #323850;
}

button.Link:focus {
   outline: none;
}

button.Link:hover {
   cursor: pointer;
}

.section2 {
   background-color: #ECF0F1;
   height: 160px;
}

.section-middle-wrap {
   display: flex;
   margin-top: -10px;
   justify-content: center;
   align-items: center;
   background: #ECF0F1;
   padding: 20px;
}

.link-container {
   display: flex;
   gap: 150px;
}

.link-item1 {
   display: flex;
   margin-left: 50px;
   margin-top: 20px;
   flex-direction: column;
   align-items: center;
   text-align: center;
   flex-direction: column;
}

.link-item2 {
   display: flex;
   margin-top: 20px;
   margin-left: 40px;
   flex-direction: column;
   align-items: center;
   text-align: center;
   flex-direction: column;
   flex-direction: column;
}

.link-item3 {
   display: flex;
   margin-top: 20px;
   flex-direction: column;
   align-items: center;
   text-align: center;
   flex-direction: column;
}

.link-text {
   color: #666666;
   font-size: 16px;
   margin-top: 5px;
   font-family: 'Noto Sans KR', sans-serif;
   font-weight: 350;
   line-height: 24px;
   word-wrap: break-word;
}

.link-item1 img {
   width: 40px; /* 이미지의 너비 설정 */
   height: 40px; /* 이미지의 높이 설정 */
}

.link-item2 img {
   width: 40px; /* 이미지의 너비 설정 */
   height: 40px; /* 이미지의 높이 설정 */
}

.link-item3 img {
   width: 40px; /* 이미지의 너비 설정 */
   height: 40px; /* 이미지의 높이 설정 */
}

.section3 {
   background-color: #fffff;
   height: 200px;
}

.DivSectionBottomconWrapBoard {
   width: 100%;
   height: 190px;
   position: relative;
   background: white;
}

.DivNwNewsBoard {
   width: 740px;
   height: 184px;
   left: 200px;
   top: 0;
   position: absolute;
}

.Heading3Board {
   width: 100%;
   height: 24px;
   position: absolute;
   left: 0;
   top: 46px;
   color: black;
   font-size: 16px;
   font-family: 'Noto Sans KR', sans-serif;
   font-weight: 700;
   line-height: 24px;
   word-wrap: break-word;
}

.ListBoard {
   width: 100%;
   height: 96px;
   left: 0;
   top: 88px;
   position: absolute;
}

.ItemLinkBoard1, .ItemLinkBoard2, .ItemLinkBoard3 {
   width: 100%;
   height: 20px;
   display: flex;
   margin-bottom: 10px;
   justify-content: space-between;
}

.DbBoard {
   width: 70%;
   color: #333333;
   font-size: 14px;
   font-family: 'Noto Sans KR', sans-serif;
   font-weight: 350;
   line-height: 20px;
   word-wrap: break-word;
}

.DeepSecurityBoard {
   width: 70%;
   color: #333333;
   font-size: 14px;
   font-family: 'Noto Sans KR', sans-serif;
   font-weight: 350;
   line-height: 20px;
   word-wrap: break-word;
}

.NoticeBoard {
   width: 70%;
   color: #333333;
   font-size: 14px;
   font-family: 'Noto Sans KR', sans-serif;
   font-weight: 350;
   line-height: 20px;
   word-wrap: break-word;
}

.0703Board {
   width: 30%;
   color: #666666;
   font-size: 14px;
   font-family: 'Noto Sans KR', sans-serif;
   font-weight: 350;
   line-height: 20px;
   word-wrap: break-word;
}

.LinkBoard {
   width: 49.75px;
   height: 20px;
   position: absolute;
   right: 0;
   top: 49px;
   color: #666666;
   font-size: 14px;
   font-family: 'Noto Sans KR', sans-serif;
   font-weight: 350;
   line-height: 20px;
   word-wrap: break-word;
   cursor: pointer;
}

.DivSectionAppconWrapBankLogo {
   width: 100%;
   height: 158px;
   display: flex;
   align-items: center;
   justify-content: center;
   background: white;
}

.DivSectionAppconInnerBankLogo {
   width: 1150px;
   height: 158px;
   position: relative;
}

.ListBankLogo {
   display: flex;
   justify-content: center;
   align-items: center;
   margin-top: 10px;
   gap: 100px;
}

.logoUrl_hana, .logoUrl_woori, .logoUrl_KB, .logoUrl_IBK, .logoUrl_NH {
   display: flex;
   justify-content: center;
   align-items: center;
   width: 100px;
   height: 100px;
   overflow: hidden;
}

.logoUrl_hana img, .logoUrl_woori img, .logoUrl_KB img, .logoUrl_IBK img,
   .logoUrl_NH img {
   width: auto;
   height: 100%;
   object-fit: contain;
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
<!-- 부트스트랩 JavaScript 연결 -->
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>
<!-- 부트스트랩 연결 -->
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
    function loginMessage(){
        alert("로그인을 환영합니다.");
    }
</script>
</head>
<body>
   <% MemberDTO dto = (MemberDTO)application.getAttribute("dto");  %>
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
                  <li class="nav-item"><a class="nav-link"
                     href="transferInfo.jsp">거래내역조회</a></li>
                  <li class="nav-item"><a class="nav-link" href="getAllList.board">고객센터</a></li>
                  <li class="nav-item dropdown"><a
                     class="nav-link dropdown-toggle" href="#" role="button"
                     data-bs-toggle="dropdown" aria-expanded="false"> 마이페이지 </a>
                     <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <li><a class="dropdown-item" href="join.jsp">회원가입</a></li>
                     </ul></li>
                  <li class="nav-item" style="margin-right: -30px;">
                     <% if (dto == null) { %>
                  
                  <li><a class="dropdown-item" href="login.jsp">로그인</a></li>
                  <% } else { %>
                  <!-- <script>loginMessage();</script> -->
                  <li><a class="dropdown-item" href="logout.member">로그아웃</a></li>
                  <% } %><li class="nav-item"><a class="nav-link" href="join.jsp">회원가입</a></li>
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
      <!-- 섹션 1 내용 -->
      <div class="hello-word">
         <%
        if (dto != null){%>
         <p class="fw-bold" style="font-size: 25px;"><%=dto.getName()%>
            님만을 위한 우리은행 오픈뱅킹 서비스
         </p>
         <%} %>

         <p class="fw-normal">
            일상에서 더 쉽고 편리하게!<br>우리오픈뱅킹이 언제나 함께 합니다!
         </p>
      </div>

      <div class="DivQuickButtonWrap">
         <button class="Link" onclick="location.href='innerAccount.jsp'">
            <div class="LinkText">조회</div>
         </button>
         <button class="Link"
            onclick="location.href='accountTransferOuter.jsp'">
            <div class="LinkText">이체</div>
         </button>
      </div>
      <button class="Link1"
         onclick="location.href='manageMember.admin'">
         <div class="LinkText1">회원관리 시작하기</div>
      </button>

   </div>


   <div class="section2">
      <!-- 섹션 2 내용 -->
      <div class="section-middle-wrap">
         <div class="link-container">
            <div class="link-item1">
               <c:url var="logoUrl_bankbook" value="/img/bankbook.svg" />
               <a class="logoUrl_bankbook" href="makeAccountSelect.jsp"> <img
                  src="${logoUrl_bankbook}" alt="아이콘" class="logoUrl_bankbook">
               </a>
               <div class="link-text">손 쉬운 계좌개설</div>
            </div>

            <div class="link-item2">
               <c:url var="logoUrl_send" value="/img/send.svg" />
               <a class="logoUrl_send" href="accountTransferOuter.jsp"> <img
                  src="${logoUrl_send}" alt="아이콘" class="logoUrl_send">
               </a>
               <div class="link-text">간편한 이체</div>

            </div>
            <div class="link-item3">
               <c:url var="logoUrl_glasses" value="/img/glasses.svg" />
               <a class="logoUrl_glasses" href="innerAccount.jsp"> <img
                  src="${logoUrl_glasses}" alt="아이콘" class="logoUrl_glasses">
               </a>
               <div class="link-text">내가 보유한 계좌를 한눈에!</div>
            </div>
         </div>
      </div>
   </div>

   <div class="section3">
      <!-- 섹션 3 내용 -->
      <div class="DivSectionBottomconWrapBoard">
         <div class="DivNwNewsBoard">
            <div class="Heading3Board">게시판</div>
            <div class="ListBoard">
               <div class="ItemLinkBoard1">
                  <div class="DbBoard">DB 백업용 인프라 자원 도입 입찰 공고</div>
                  <div class="0703Board">2023-07-03</div>
               </div>
               <div class="ItemLinkBoard2">
                  <div class="DeepSecurityBoard">서버백신(Deep Security) 도입 입찰 공고</div>
                  <div class="0703Board">2023-07-03</div>
               </div>
               <div class="ItemLinkBoard3">
                  <div class="NoticeBoard">'하나은행 경조사 지원업체 선정'을 위한 공고</div>
                  <div class="0703Board">2023-07-03</div>
               </div>
            </div>
            <div class="LinkBoard">+ 더보기</div>
         </div>
      </div>


   </div>

   <div class="section4">
      <!-- 섹션 4 내용 -->
      <div class="DivSectionAppconWrapBankLogo">
         <div class="DivSectionAppconInnerBankLogo">
            <div class="ListBankLogo">
               <c:url var="logoUrl_hana" value="/img/hana.jpg" />
               <a class="logoUrl_hana" href="#"> <img src="${logoUrl_hana}"
                  alt="로고" class="logoUrl_hana">
               </a>
               <c:url var="logoUrl_woori" value="/img/woori.jpg" />
               <a class="logoUrl_woori" href="#"> <img src="${logoUrl_woori}"
                  alt="로고" class="logoUrl_woori">
               </a>
               <c:url var="logoUrl_KB" value="/img/KB.jpg" />
               <a class="logoUrl_KB" href="#"> <img src="${logoUrl_KB}"
                  alt="로고" class="logoUrl_KB">
               </a>
               <c:url var="logoUrl_IBK" value="/img/IBK.jpg" />
               <a class="logoUrl_IBK" href="#"> <img src="${logoUrl_IBK}"
                  alt="로고" class="logoUrl_IBK">
               </a>
               <c:url var="logoUrl_NH" value="/img/NH.jpg" />
               <a class="logoUrl_NH" href="#"> <img src="${logoUrl_NH}"
                  alt="로고" class="logoUrl_NH">
               </a>
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

         <p class="FooterDescription">우리은행오픈뱅킹입니다.</p>
      </div>

   </footer>

</body>



</html>