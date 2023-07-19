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
	height: 1000px;
}

.select-register-account {
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

table {
	width: 80%;
	margin: 0 auto;
	background-color: #fff;
	border-collapse: collapse;
	border: 1px solid #ddd;
}

th, td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #0082C9;
	color: #fff;
	font-weight: bold;
}

tr:hover {
	background-color: #f9f9f9;
}

.checkbox-container {
	text-align: center;
}

.button-container {
	position: absolute;
	right: 0;
	margin-top: -40px;
	margin-left: 20px;
	padding-right: 10%;
}

.button-container2 {
	display: flex;
	justify-content: center;
	margin-top: 30px;
}

.action-button, .cancel-button {
	margin: 0 5px;
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
    var checkedAccounts = [];

    // 중복되는 코드를 피하기 위해, 테이블 행에서 계좌 정보를 추출하는 함수를 추가합니다.
    function getAccountFromRow(row) {
        return {
            "accountNumber": row.find("td").eq(2).text(),
            "bankCode": row.find("td").eq(1).text()
        };
    }

    $.ajax({
        type: 'GET',
        url: '/gwanjung/accounts',
        data: { 'personalIdNumber': '<%=dto.getPersonalIdNumber()%>'},
        beforeSend: function() {
            $("#loading").show();
        },
        success: function(response) {
            var transactionList = response;
            var tbody = $("table tbody");
            tbody.empty();

            for(var i = 0; i < transactionList.length; i++) {
                var tr = $("<tr>");
                tr.append("<td>" + transactionList[i].nickname + "</td>")
                  .append("<td>" + transactionList[i].bankCode + "</td>")
                  .append("<td>" + transactionList[i].accountNumber + "</td>")
                  .append("<td>" + parseInt(transactionList[i].balance).toLocaleString()+ "원" + "</td>")
                  

                if (transactionList[i].bankCode === "우리") {
                    tr.append("<td class='checkbox-container'><input type='checkbox' name='accountIds' disabled></td>");
                } else if(transactionList[i].accountStatus === 1) {
                    tr.append("<td class='checkbox-container'><input type='checkbox' name='accountIds' checked></td>");
                } else {
                    tr.append("<td class='checkbox-container'><input type='checkbox' name='accountIds'></td>");
                }
                tbody.append(tr);
            }

            $("#loading").hide();

         	// 체크박스의 이벤트 핸들러를 설정합니다.
            $("table").on("change", "input[type='checkbox']", function(){
                var row = $(this).closest("tr");
                var account = getAccountFromRow(row);
                console.log(account);  // 확인을 위해 계좌 정보를 콘솔에 출력합니다.

                if(this.checked) {
                    checkedAccounts.push(account);
                } else {
                    checkedAccounts = checkedAccounts.filter(function(acc){
                        return acc.accountNumber !== account.accountNumber;
                    });
                }

                console.log(checkedAccounts);  // 확인을 위해 현재 선택된 계좌 정보들을 콘솔에 출력합니다.
            });
        },
        error: function(request, status, error) {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });

    window.sendCheckedAccounts = function() {
        $.ajax({
            type: 'PUT',
            url: '/gwanjung/registrate',
            contentType: 'application/json',
            data: JSON.stringify(checkedAccounts),
            success: function(response) {
                alert("All accounts successfully updated.");
                window.location.href = 'allAccount.jsp'; // 이 부분이 페이지 이동 코드입니다.
            },
            error: function(request, status, error) {
                var errorMsg = JSON.parse(request.responseText).message;
                alert("Error occurred: " + errorMsg);
            }
        });
    };
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

		<div class="select-register-account">

			<div class="title">전체계좌 불러오기</div>
		</div>


		<script>
			function selectAll() {
				var checkboxes = document.getElementsByName('accountIds');
				var selectAllCheckbox = document
						.getElementById('selectAllCheckbox');

				for (var i = 0; i < checkboxes.length; i++) {
					checkboxes[i].checked = selectAllCheckbox.checked;
				}
			}
		</script>
		
		<div class="table-container">
			<div class="button-container">
				<button id="selectAllCheckbox" onclick="selectAll()">전체선택</button>
				<button onclick="deselectAll()">전체해제</button>
			</div>
			<table>
				<thead>
					<tr>
						<th>계좌명</th>
						<th>금융기관</th>
						<th>계좌번호</th>
						<th>잔액</th>
						<th>선택</th>
					</tr>
				</thead>
				
				<tbody>
					<!-- 추가적인 계좌 데이터를 여기에 추가할 수 있습니다 -->
				</tbody>
			</table>
			<div id="loading" class="center-screen" style="display: none;">
				        <img src="../img/Loading.gif" alt="../img/woori.jpg" />
			</div>
		</div>
		<script>
			function selectAll() {
				var checkboxes = document.getElementsByName('accountIds');
				for (var i = 0; i < checkboxes.length; i++) {
					checkboxes[i].checked = true;
				}
			}

			function deselectAll() {
				var checkboxes = document.getElementsByName('accountIds');
				for (var i = 0; i < checkboxes.length; i++) {
					checkboxes[i].checked = false;
				}
			}
		</script>

		<div class="button-container2">
		     <button class="action-button" onclick="sendCheckedAccounts()">계좌연동하기</button>
		     <button class="cancel-button" onclick="location.href='mainHana.jsp'">취소</button>
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
</body>
</html>