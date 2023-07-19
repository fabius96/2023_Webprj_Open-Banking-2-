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
	margin-left: 50px;
}

.navbar-nav .nav-link {
	color: #000000;
}

.navbar {
	padding-top: 10px; /* 헤더 상단 여백 조절 */
	padding-bottom: 10px; /* 헤더 하단 여백 조절 */
}

.navbar-brand {
	margin-left: 50px; /* 로고 이미지와 메뉴 간격 조절 */
}

.navbar-nav {
	margin-left: -50px; /* 메뉴 간격 조절 */
}

.logo-img {
	margin-left: 80px;
	max-width: 100px;
	max-height: 100px;
}

.navbar {
	padding-top: 3px; /* 헤더 상단 여백 조절 */
	padding-bottom: 3px; /* 헤더 하단 여백 조절 */
}

.navbar-divider {
	border-top: 2px solid #0082C9;
	margin-top: 0;
	margin-bottom: 0;
	width: 100%;
}

/* 제목  */
.section1 {
	background-color: #FFFFFF;
	height: 800px;
}

.title {
	position: absolute;
	text-align: center;
	color: black;
	font-size: 30px;
	font-family: Noto Sans KR;
	left: 400px;
	top: 200px;
	margin-bottom: 20px;
	transform: translate(-50%, -50%);
}

.container {
	width: 80%;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
	margin-top: 120px;
}

.form-container {
	background-color: #f8f8f8;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	padding: 40px;
	border-radius: 10px;
	max-width: 800px;
	width: 100%;
}

.form-container h2 {
	text-align: center;
	font-size: 24px;
	margin-bottom: 20px;
	color: #333333;
}

.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	font-weight: bold;
	margin-bottom: 8px;
	color: #666666;
}

.form-group input[type="text"], .form-group input[type="password"],
	.form-group input[type="checkbox"] {
	width: 100%;
	padding: 12px;
	border: 1px solid #cccccc;
	border-radius: 5px;
	transition: border-color 0.3s ease;
	font-size: 16px;
}

.form-group input[type="text"]:focus, .form-group input[type="password"]:focus,
	.form-group input[type="checkbox"]:focus {
	outline: none;
	border-color: #0082C9;
}

.checkbox-group label {
	display: flex;
	align-items: center;
	font-size: 14px;
	color: #666666;
	cursor: pointer;
}

.checkbox-group input[type="checkbox"] {
	margin-right: 5px;
}

.button-container {
	text-align: center;
}

.button-container button {
	background-color: #0082C9;
	color: #ffffff;
	padding: 12px 24px;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	font-weight: bold;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.button-container button:hover {
	background-color: #0082C9;
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

.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.5);
}

.modal1 {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.5);
}

.modal2 {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
	background-color: #fff;
	margin: 10% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
	border-radius: 5px;
	position: relative;
}

.modal-content1 {
	background-color: #fff;
	margin: 10% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
	border-radius: 5px;
	position: relative;
}

.modal-content2 {
	background-color: #fff;
	margin: 10% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
	border-radius: 5px;
	position: relative;
}

.close {
	color: #aaa;
	position: absolute;
	top: 10px;
	right: 20px;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.close1 {
	color: #aaa;
	position: absolute;
	top: 10px;
	right: 20px;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}

.close1:hover, .close1:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.close2 {
	color: #aaa;
	position: absolute;
	top: 10px;
	right: 20px;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}

.close2:hover, .close2:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.show-more {
	color: #4CAF50;
	cursor: pointer;
	text-decoration: underline;
	margin-top: 10px;
}

.hidden {
	display: none;
}
</style>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
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

		<div class="title">새로운 계좌를 개설해드리겠습니다.</div>

		<div class="container">

			<div class="form-container">
				<form action="accountInfo/insertAccount" method="post">
					<h2>계좌개설</h2>
					<div class="form-group">

						<label for="product-name">상품명</label> <input type="text"
							id="product-name" name="product-name" required
							value="<%=request.getParameter("text")%>">
					</div>
					<div class="form-group">
						<label for="bank">은행</label> <input type="text" id="bank"
							name="bank" required value="우리은행">
					</div>
					<div class="form-group">
						<label for="account-nickname">계좌 별명</label> <input type="text"
							id="account-nickname" name="account-nickname" required>
					</div>
					<div class="form-group">
						<label for="account-password">계좌 비밀번호</label> <input
							type="password" id="account-password" name="account-password"
							required>
					</div>
					<div class="form-group">
						<label for="confirm-password">비밀번호 확인</label> <input
							type="password" id="confirm-password" name="confirm-password"
							required>
					</div>
					<!-- modal -->

					<div class="modal">

						<!-- 첫 번째 Modal의 내용 -->
						<div class="modal-content">
							<span class="close">&times;</span>
							<p>
								<b>개인(신용)정보 수집·이용 동의서(상품서비스 안내·오픈뱅킹용)</b> <br> 귀 행이 상품서비스
								안내 등을 위하여 본인의 개인(신용)정보를 수집·이용하는 경우에는 「신용정보의 이용 및 보호에 관한 법률」 제15조
								제2항, 제32조 제1항, 제33조 및 제34조, 「개인정보 보호법」 제15조 제1항 제1호, 제22조 제3항에
								따라 본인의 동의가 필요합니다. <br> 아래에서 (금융)거래라 함은 은행업무(여신, 수신, 외국환),
								겸영업무(신탁, 펀드, 방카슈랑스, 신용카드 등), 부수업무(보증, 팩토링, 대여금고, 보호예수 등)와 관련된
								거래를 의미합니다. <br> 1. 수집 이용 목적 <br> 타행 (금융)거래정보를 활용한 상품,
								서비스 홍보 및 판매 권유<br> 2. 수집‧이용할 항목<br> ￭ 개인정보<br> ‣
								성명, 생년월일, 주소, 연락처(전자우편주소, 집/직장/휴대폰 전화번호 등), 국적, 연소독, 직업, 직장정보,
								결혼여부, 주거 및 가족사항, 거주기간, 관심 금융/서비 스 정보, 금융/비금융 자산현황<br> ￭
								오픈뱅킹 및 통합조회 서비스 이용을 위하여 등록한 타행 (금융)거래정보<br> ‣ 상품종류,
								거래조건(이자율, 만기 등), 거래일시, 금액 등 거래 설정·내역정보 및 오픈 뱅킹 서비스의 설정·유지·이행·관리를
								위한 상담 등을 통해 생성되는 정보 ※ 본 동의 이전에 발생한 개인(신용)정보도 포함됩니다.<br> 3.
								보유 이용 기간<br> 위 개인(신용)정보는 (금융)거래종료일* 또는 동의 철회 시까지 보유· 이용할 수
								있습니다. 이후에는 관련된 사고조사, 분쟁해결, 민원처리, 법령상 의 무이행을 위하여 필요한 범위 내에서만
								보유․이용됩니다. * (금융)거래 종료일이란 당행과 거래중인 모든 계약(여·수신, 내·외국환, 카드 및 제3자 담보
								제공 등)해지 및 서비스(대여금고, 보호예수, 외국환거래지정, 인터넷뱅킹 포함 전자금융 거래 등)가 종료된 날을
								의미합니다.
							</p>
						</div>
					</div>




					<div class="modal1">

						<!-- 두 번째 Modal의 내용 -->
						<div class="modal-content1">
							<span class="close1">&times;</span>
							<p>
								<b>개인 정보 수집·이용에 관한 동의서</b> <br> 주식회사 우리은행은 개인정보보호법 등 관련 법상의
								개인정보호 규정을 준수하여 근로자의 개인정보 보호에 최선을 다하고 있습니다. 회사는 개인정보보호법 제17조에
								근거하여 다음과 같이 회사 업무의 수행 및 소속직원들의 채용, 해지, 발령 등에 따른 인사기록의 보관 등과 같이
								반드시 필요한 범위내에서 직원의 개인정보 수집, 이용, 보관하고 이를 제3자에게 제공하는데 동의를 받고자합니다. <br>
								1. 개인정보 수집 목적 <br> 채용, 배치, 평가, 취업 알선, 기타 법령으로 정한 용도로의 활용 및
								제3자에 대한 제공 <br> 2. 수집항목 <br> 가. 필수항목 : 이름, 주민등록번호 앞
								6자리, 주소, 전화번호(연락을 목적으로 사용 가능), 보유자격 및 교육이수현황 나. 선택항목 : 가족관계, 이력
								및 경력사항 <br> 3. 개인정보 보유 및 이용기간 <br> 가. 채용 전 : 제출일로부터
								3개월간 <br> 나. 채용 시 : 채용일로부터 퇴사 후 5년간 <br> 4. 제3자에 대한 제공
								동의(□ 동의 함 / □ 동의하지 않음) <br> 가. 제공 받은 자 : 배치예정 또는 배치하고자하는
								사업장의 사업주 또는 담당자 <br> 나. 제공 받은 자의 목적 : 정보제공자의 배치적정 여부 판단 및
								인적사항 확보 <br> 다. 제공하는 항목 : 정보 주체가 제출한 이력서, 자격증 사본에 기재된 사항 일체
								<br> 라. 제공 받은 자의 보유/이용기간 : 정보 주체가 해당 사업장에 배치되어 종사하는 기간 동안 <br>
								마. 동의 거부권 : 귀 개인정보의 수집·이용에 대한 동의를 거부할 수 있으며, 동의하지 않는 경우 사업장의 배치
								거부로 채용 또는 배치가 제한 또는 거절 될 수 있습니다. <br> 5. 개인정보의 수집·이용에 대한 동의
								거부 <br> 개인정보의 수집·이용을 거부할 수 있습니다. 다만, 개인정보의 수집·이용 등에 동의하지 않을
								경우 신원확인 및 선임, 직무능력 등이 입증되지 않아 채용되지 않거나 채용 이후에도 채용이 취소될 수 있습니다.
							</p>
						</div>
					</div>

					<div class="modal2">

						<!-- 세 번째 Modal의 내용 -->
						<div class="modal-content2">
							<span class="close2">&times;</span>
							<p>
								<b>개인(신용)정보 수집,이용(조회)<br> 제공동의서 관련 고객 권리 안내문
								</b> <br> 1. 금융서비스 이용 범위 <br> 고객의 개인(신용)정보는 고객이 동의한 목적을
								위하여만 수집ㆍ이용 및 제공됩니다. 필수적 정보에 대한 수집ㆍ이용 및 제공은 계약의 체결 및 이행을 위하여
								필수적이므로, 위 사항에 동의하셔야만 [(금융)거래] 관계의 설정 및 유지가 가능합니다. 반면 선택적 정보에 대한
								수집ㆍ이용 및 제공에 대하여는 거부하실 수 있으며, 다만 동의하지 않으실 경우 [(금융)거래] 조건 등에 불이익을
								받으실 수 있습니다. <br> 2. 고객 개인(신용)정보의 제공 및 마케팅 활용 중단 신청<br>
								가. 고객은 가입 신청 시 동의한 본인 개인(신용)정보의 제3자 제공 또는 당사의 [금융상품(서비스)] 소개 등
								영업목적의 사용에 대하여 전체 또는 사안별로 당사에 제공 활용을 중단시킬 수 있습니다(개인정보 보호법 제37조,
								신용정보의 이용 및 보호에 관한 법률 제37조). 다만, ① 법률에 특별한 규정*이 있거나 법령상 의무를 준수하기
								위하여 불가피한 경우, ② 다른 사람의 생명ㆍ신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게
								침해할 우려가 있는 경우, ③ 개인(신용)정보를 처리하지 아니하면 정보주체와 약정한 서비스를 제공하지 못하는 등
								계약의 이행이 곤란한 경우로서 정보주체가 그 계약의 해지 의사를 명확하게 밝히지 아니한 경우에는 제공 활용중단
								신청이 거절될 수 있습니다. * 신용조회회사 또는 신용정보집중기관에 제공하여 개인의 신용도를 평가하기 위한 목적으로
								행한 신용정보의 제공 동의는 철회할 수 없습니다(신용정보의 이용 및 보호에 관한 법률 제37조제1항 단서).
								*[(금융)거래]관계를 설정하면서 동의를 한 경우 계약 체결일로부터 3개월 내에는 신용정보의 제공 동의를 철회할 수
								없습니다(신용정보의 이용 및 보호에 관한 법률 시행령 제32조제1항 단서). <br> 3. 고객
								개인(신용)정보의 자기정보결정권<br> 가. 고객은 개인정보 보호법, 신용정보의 이용 및 보호에 관한 법률
								및 신용정보업감독규정 등에 따라 아래의 권리가 부여되어 있습니다. 동 권리의 세부내용에 대해서는 당사
								홈페이지(www.shinhansavings.com) 또는 금융감독원 홈페이지(www.fss.or.kr)에 게시되어
								있습니다. 동 권리를 행사하고자 하는 고객은 연락중지청구권의 경우 두낫콜
								홈페이지(www.donotcall.or.kr)에서, 그 밖의 권리는 당사 각 영업점 앞으로 신청하여 주시기
								바랍니다. 개인신용정보 이용 및 제공 사실 조회 요청권(신용정보의 이용 및 보호에 관한 법률 제35조) :
								금융회사가 내부경영관리의 목적으로 이용하거나 반복적인 업무위탁을 위해 제공하는 경우 등을 제외하고 개인신용정보를
								이용하거나 제공중인 현황을 확인할 수 있는 권리 연락중지청구권(신용정보의 이용 및 보호에 관한 법률 제37조
								제2항) : 원치 않는 마케팅 목적의 연락(휴대폰 전화 또는 문자메세지)을 거부할 수 있는 권리 개인(신용)정보
								열람 및 오류 개인(신용)정보의 정정 삭제 요구권(개인정보보호법 제35조 제36조, 신용정보의 이용 및 보호에 관한
								법률 제38조) : 당사에 본인의 개인(신용)정보에 대한 열람을 요구할 수 있는 권리 및 자신의 개인(신용)정보를
								열람한 후 사실과 다르거나 확인할 수 없는 개인(신용)정보에 대하여 정정 또는 삭제를 요구할 수 있는 권리
								개인신용정보 이용 제공 사실 통보 요구권(신용정보의 이용 및 보호에 관한 법률 제35조) : 신용정보회사 등이
								본인에 관한 신용정보를 이용 제공한 경우 매 1년마다 이용 제공현황을 통보해줄 것을 요구할 수 있는 권리(단,
								신용정보 이용 제공사실 통보에 대한 수수료가 발생할 수 있습니다.) 개인신용정보 삭제요구권(신용정보의 이용 및
								보호에 관한 법률 제38조의3) : 당사와의 금융거래 종료 후 법령에서 정한 기간이 경과 시 당사가 보유한 본인
								정보의 파기를 요구할 수 있는 권리
							</p>
						</div>
					</div>

					<!-- modal -->

					<div class="checkbox-group">
						<label> <input type="checkbox" name="terms" required>
							약관 동의
						</label>
					</div>
					<div class="checkbox-group">
						<label> <input type="checkbox" name="terms1" required>
							약관 동의2
						</label>
					</div>
					<div class="checkbox-group">
						<label> <input type="checkbox" name="terms2" required>
							약관 동의3
						</label>
					</div>
					<%

					String memberId = "";
					if (dto != null) {
						memberId = dto.getMemberId();
					}
					%>
					<input type="hidden" name="memberId" value="<%=memberId%>">
					<div></div>
					<div class="button-container">
						<button type="submit">계좌 개설</button>
					</div>

				</form>
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

	<!-- 부트스트랩 JavaScript 연결 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>

	<script>
		// 모달 요소를 가져옵니다.
		var modal = document.getElementsByClassName("modal")[0];

		// 모달을 열고 닫는 함수를 정의합니다.
		function openModal() {
			modal.style.display = "block";
		}

		function closeModal() {
			modal.style.display = "none";
		}

		// "약관 동의" 체크박스 클릭 시 모달 열기
		var checkbox = document.getElementsByName("terms")[0];
		checkbox.addEventListener("change", function() {
			if (checkbox.checked) {
				openModal();
			} else {
				closeModal();
			}
		});

		// 모달 닫기 버튼 클릭 시 모달 닫기
		var closeBtn = document.getElementsByClassName("close")[0];
		closeBtn.addEventListener("click", closeModal);

		// 모달 영역 외 클릭 시 모달 닫기
		window.addEventListener("click", function(event) {
			if (event.target == modal) {
				closeModal();
			}
		});
	</script>
	<script>
		// 모달 요소를 가져옵니다.
		var modal1 = document.getElementsByClassName("modal1")[0];

		// 모달을 열고 닫는 함수를 정의합니다.
		function openModal1() {
			modal1.style.display = "block";
		}

		function closeModal1() {
			modal1.style.display = "none";
		}

		// "약관 동의" 체크박스 클릭 시 모달 열기
		var checkbox1 = document.getElementsByName("terms1")[0];
		checkbox1.addEventListener("change", function() {
			if (checkbox1.checked) {
				openModal1();
			} else {
				closeModal1();
			}
		});

		// 모달 닫기 버튼 클릭 시 모달 닫기
		var closeBtn1 = document.getElementsByClassName("close1")[0];
		closeBtn1.addEventListener("click", closeModal1);

		// 모달 영역 외 클릭 시 모달 닫기
		window.addEventListener("click", function(event) {
			if (event.target == modal1) {
				closeModal1();
			}
		});
	</script>

	<script>
		// 모달 요소를 가져옵니다.
		var modal2 = document.getElementsByClassName("modal2")[0];

		// 모달을 열고 닫는 함수를 정의합니다.
		function openModal2() {
			modal2.style.display = "block";
		}

		function closeModal2() {
			modal2.style.display = "none";
		}

		// "약관 동의" 체크박스 클릭 시 모달 열기
		var checkbox2 = document.getElementsByName("terms2")[0];
		checkbox2.addEventListener("change", function() {
			if (checkbox2.checked) {
				openModal2();
			} else {
				closeModal2();
			}
		});

		// 모달 닫기 버튼 클릭 시 모달 닫기
		var closeBtn2 = document.getElementsByClassName("close2")[0];
		closeBtn2.addEventListener("click", closeModal);

		// 모달 영역 외 클릭 시 모달 닫기
		window.addEventListener("click", function(event) {
			if (event.target == modal2) {
				closeModal2();
			}
		});
	</script>


</body>
</html>
