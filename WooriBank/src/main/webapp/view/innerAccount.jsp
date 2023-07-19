<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.MemberDTO"%>
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
    margin-left: 40px;
}

.navbar-nav .nav-link {
    color: #000000;
}

.navbar {
    padding-top: 10px;
    padding-bottom: 10px;
}

.navbar-brand {
    margin-bottom: 10px;
}

.navbar-nav {
    margin-left: -30px;
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
    border-top: 2px solid #0082C9;
    margin-top: 0;
    margin-bottom: 0;
    width: 100%;
}

.navbar-nav .nav-item:nth-child(6) {
    margin-left: 30px;
}

/* 제목  */
.section1 {
    background-color: #FFFFFF;
    height: 800px;
}

.account-info {
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
    font-size: 30px;
    font-family: Noto Sans KR;
}

/* 은행선택 */
.select_bank {
    left: 300px;
    top: 40px;
    position: absolute;
}

.btn-group {
    display: flex;
    gap: 10px;
}

.btn-group .btn {
    font-size: 18px;
    font-family: Inter, sans-serif;
    font-weight: 500;
    line-height: 1.5;
    padding: 10px 20px;
    border-radius: 30px;
    background-color: #f7f7f7;
    color: #080C0C;
    transition: background-color 0.3s, color 0.3s;
    border: 2px solid #f7f7f7;
}

.btn-group .btn:hover, .btn-group .btn:focus {
    background-color: #0082C9;
    color: #ffffff;
    border-color: #0082C9;
}

/* 입출금, 예적금 */
/* .category-wrapper {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 200px;
    height: 10px;
    position: absolute;
    top: 120px;
    left: 180px;
}

.category {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    background-color: #ffffff;
    height: 40px;
    padding: 3px 15px;
    border-radius: 30px;
    font-size: 15px;
    white-space: nowrap;
    border: 2px solid #cccccc;
    border-radius: 30px;
    white-space: nowrap;
    margin-right: 20px;
} */

/* 아코디언 */
.accordion {
    width: 90%;
    margin: 0 auto;
    position: relative;
}

.accordion-item {
    margin-bottom: 10px;
}

.accordion-button {
    background-color: #f2f2f2;
    color: #333333;
    height: 80px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 30px;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.accordion-button:hover {
    background-color: #e6e6e6;
}

.accordion-body {
    padding: 10px;
    background-color: #ffffff;
    border: 1px solid #cccccc;
    border-radius: 5px;
    height: 200px;
    font-size: 20px;
    color: #555555;
    line-height: 1.5;
}

/*계좌 상세정보*/
.account-details {
    display: flex;
    align-items: center;
}

.right-column {
    flex: 1;
    text-align: right;
}

.left-column {
    flex: 1;
    margin-top: 30px;
    margin-left: 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 10px;
    display: flex;
}

.account-info {
    font-size: 20px;
    font-family: inherit;
}

.account-bank, .account-number, .account-name {
    display: block;
    margin-bottom: 5px;
}

.account-bank {
    color: #0082C9;
}

.account-number, .account-name {
    color: #080C0C;
}

.buttons {
    gap: 10px;
    margin-left: 50px;
    flex-direction: column;
}

.transfer-button, .transaction-button {
    padding: 5px 0;
    background-color: #30354B;
    color: white;
    border: none;
    font-weight: 500;
    font-size: 20px;
    border-radius: 30px;
    cursor: pointer;
    width: 150px;
}

.balance {
    font-size: 25px;
    font-family: inherit;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    margin-right: 20px;
    order: -1; /* Place the balance element before the buttons */
}
/* .section2 {
    background-color: #ECF0F1;
    height: 150px;
}

.section3 {
    background-color: #fffff;
    height: 200px;
}
 */
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
    color: #0082C9;
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
.center-screen {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh; /* vh is a viewport-height, will cover the full view */
}
</style>

<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<%
 MemberDTO dto = (MemberDTO)application.getAttribute("dto");
%>
<script>
$(document).ready(function(){
    $.ajax({
        type: 'GET',
        url: '/gwanjung/my-inner-accounts',
        data: { 'memberId':'<%=dto.getMemberId()%>'},
        beforeSend: function() {
            // AJAX 호출이 시작되기 전에 로딩 표시를 보입니다.
            $("#loading").show();
        },
        success: function(response) {
            var accountList = response;
            var accordionExample = $("#accordionExample");

            accordionExample.empty();

            for(let i = 0; i < accountList.length; i++) {
                var accountInfo = $("<div>").addClass("account-info");
                accountInfo.append("<span class='account-bank'>은행명: " + accountList[i].bankCode + "</span>")
                           .append("<span class='account-number'>계좌번호: " + accountList[i].accountNumber + "</span>");

                var balanceInfo = $("<span class='balance'>")
                .css({ 'text-align': 'right', 'display': 'inline-block' })
                .text("잔액: " + parseInt(accountList[i].balance).toLocaleString() + "원");

                var transferButton = $('<button>').addClass("transfer-button").text('이체');
                transferButton.click((function(i){
                    return function(){
                        sessionStorage.setItem('bankCode', accountList[i].bankCode);
                        sessionStorage.setItem('accountNumber', accountList[i].accountNumber);
                        sessionStorage.setItem('balance', accountList[i].balance)
                        location.href='accountTransferOuter.jsp';
                    };
                })(i));

                var accordionItem = $('<div>').addClass("accordion-item")
                    .append(
                        $('<h2>').addClass("accordion-header").attr('id', 'heading' + i)
                            .append($('<button>').addClass("accordion-button collapsed")
                                .attr({
                                    'type': 'button',
                                    'data-bs-toggle': 'collapse',
                                    'data-bs-target': '#collapse' + i,
                                    'aria-expanded': 'false',
                                    'aria-controls': 'collapse' + i
                                })
                                .text("계좌명: " + accountList[i].nickname)
                            )
                    )
                    .append(
                        $('<div>').attr({
                            'id': 'collapse' + i,
                            'class': 'accordion-collapse collapse',
                            'aria-labelledby': 'heading' + i,
                            'data-bs-parent': '#accordionExample'
                        })
                            .append($('<div>').addClass("accordion-body")
                                .append(
                                    $('<div>').addClass("account-details")
                                        .append(
                                            $('<div>').addClass("left-column")
                                                .append(accountInfo)
                                        )
                                        .append(
                                            $('<div>').addClass("right-column")
                                                .append(balanceInfo)
                                        )
                                        .append(
                                                                                    $('<div>').addClass("buttons")
                                                                                    .append(transferButton)
                                                                                    .append($('<button>').addClass("transaction-button")
                                                                                    .attr('onclick',"location.href='oneTransferInfo.jsp'")
                                                                                    .click(function() {
                                                                                         sessionStorage.setItem('accountNumber', accountList[i].accountNumber);
                                                                                         sessionStorage.setItem('bankCode', accountList[i].bankCode);
                                                                                         sessionStorage.setItem('nickname', accountList[i].nickname);
                                                                                         location.href = 'oneTransferInfo.jsp';
                                                                                    }).text('거래내역'))
                                                )
                                        )
                                
                            )
                    );
                accordionExample.append(accordionItem);
            }
            $("#loading").hide();
        },
        error: function(request, status, error) {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            $("#loading").hide();
        }
    });
});

</script>
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
        <div class="account-info">
            <div class="title">계좌조회</div>
            <div class="select_bank">
                <div class="btn-group" role="group" aria-label="Select Bank">
                    <button type="button" class="btn btn-secondary"
                        onclick="location.href='allAccount.jsp'">All</button>
                    <button type="button" class="btn btn-secondary"
                        onclick="location.href='innerAccount.jsp'">우리은행</button>
                    <button type="button" class="btn btn-secondary"
                        onclick="location.href='outerAccount.jsp'">다른은행</button>
                </div>
            </div>
            <!--        <div class="category-wrapper">
                <div class="category">입출금</div>
                <div class="category">예금・적금</div>
            </div> -->
        </div>
        	<div class="accordion" id="accordionExample">
			    <!-- 여기에 계좌 정보가 추가될 예정입니다 -->
			</div>
			<div id="loading" class="center-screen" style="display: none;">
				        <img src="../img/Loading.gif" alt="../img/woori.jpg" />
	  		</div>

        </div>




    <!--    <div class="section2"></div>

    <div class="section3"></div>

    <div class="section4"></div> -->







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

    <!-- 부트스트랩 JavaScript 연결 -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>
     
</body>
</html>
