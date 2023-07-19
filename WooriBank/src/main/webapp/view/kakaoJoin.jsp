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
   min-height: 800px;
   padding-top: 150px; </-
   -position: relative;
   -->
}

.title {
   text-align: center;
   color: black;
   font-size: 30px;
   font-family: Noto Sans KR;
   left: 50%;
   top: 200px;
   margin-bottom: 20px;
   position: absolute;
   transform: translate(-50%, -50%);
}

.container {
   width: 80%;
   margin: 5 auto;
   display: flex;
   justify-content: center;
   align-items: center;
   height: 80%;
   margin-top: 0px;
   margin-bottom: 50px;
}

.form-container {
   background-color: #f8f8f8;
   box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
   padding: 30px;
   border-radius: 10px;
   max-heigt: 300px;
   max-width: 800px;
   width: 100%;
   max-heigt: 300px;
}

.form-container h2 {
   text-align: center;
   font-size: 24px;
   margin-bottom: 30px;
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

.form-group input[type="text"], .form-group input[type="password"], .form-group input[type="checkbox"]
   {
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

/* 아이디 입력창 css 추가 */
.form-group-duplicate {
   position: relative;
   margin-bottom: 20px;
}

.form-group-duplicate label {
   display: block;
   font-weight: bold;
   margin-bottom: 8px;
   color: #666666;
}

.form-group-duplicate .input-container {
   position: relative;
   display: flex;
   width: 100%;
}

.form-group-duplicate input[type="text"] {
   width: 100%;
   padding: 12px;
   border: 1px solid #cccccc;
   border-radius: 5px;
   transition: border-color 0.3s ease;
   font-size: 16px;
}

.form-group-duplicate .input-group-btn {
   position: absolute;
   right: 2px;
   top: 42%;
   transform: translateY(-50%);
   padding: 10px;
   border: 1px solid #cccccc;
   border-radius: 5px !important;
   background-color: #0082C9; /* Button color */
   color: white; /* Text color */
   transition: border-color 0.3s ease;
   font-weight: bold;
   font-size: 14px;
}

.form-group-duplicate input[type="text"]:focus, .form-group-duplicate .input-group-btn:focus
   {
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

/* 버튼 크기 및 둥글기 수정*/
.button-container button {
   background-color: #0082C9;
   color: #ffffff;
   padding: 12px 40px;
   border: none;
   border-radius: 30px;
   font-size: 15px;
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

/* 회원 가입 페이지*/
<
style>body {
   font-family: Arial, sans-serif;
}

h1 {
   text-align: center;
}

form {
   max-width: 400px;
   margin: 0 auto;
}

label {
   display: block;
   margin-bottom: 5px;
}

input[type="text"], input[type="password"], input[type="email"] {
   width: 100%;
   padding: 10px;
   margin-bottom: 10px;
   border-radius: 5px;
   border: 1px solid #ccc;
   font-size: 14px;
}

input[type="button"], input[type="submit"] {
   width: 100%;
   padding: 10px;
   border-radius: 5px;
   background-color: #4CAF50;
   color: white;
   font-size: 14px;
   border: none;
   cursor: pointer;
}

input[type="button"]:hover, input[type="submit"]:hover {
   background-color: #45a049;
}

/* 주민등록번호 입력란 스타일 */
.personal-id-input {
   display: flex;
   justify-content: space-between;
   align-items: center;
}

.personal-id-input input[type="text"] {
   flex: 1;
   padding: 10px;
   margin-right: 5px;
   border-radius: 5px;
   border: 1px solid #ccc;
   font-size: 14px;
   text-align: center;
}

.personal-id-input span {
   font-size: 18px; /* 조정된 사이즈로 변경 */
   margin-right: 5px; /* 간격 조정 */
}
</style>





<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">

<script>
        function limitPhoneNumber() {
            var phoneNumberInput = document.getElementById('phone');
            var phoneNumber = phoneNumberInput.value;
            // 010으로 시작하지 않으면 010을 추가
            if (!phoneNumber.startsWith('010')) {
                phoneNumberInput.value = '010' + phoneNumber;
            }
            // 11자리가 넘으면 자르기
            if (phoneNumber.length > 11) {
                phoneNumberInput.value = phoneNumber.slice(0, 11);
            }
        }
        
        function limitSimplePw(input) {
            // 입력 값에서 숫자만 추출
            var digits = input.value.replace(/\D/g, '');

            // 6자리 숫자로 제한
            digits = digits.substr(0, 6);

            // 입력 필드에 결과 값 반영
            input.value = digits;
        }

        function limitPersonalId(input) {
            // 입력 값에서 숫자만 추출
            var digits = input.value.replace(/\D/g, '');

            // 13자리 숫자로 제한
            if (digits.length > 13) {
                digits = digits.substr(0, 13);
            }
            // 입력 필드에 결과 값 반영
            input.value = digits;
        }
    </script>
<script
   src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }

    
    document.addEventListener("DOMContentLoaded", function () {
        // 패스워드 체크
        let passwd = document.querySelector("#pw");
        let passwdck = document.querySelector("#pwck");
        let message = document.createElement("p");
        message.style.color = "green";
        message.style.fontWeight = "bold";
        // 메시지 엘리먼트를 추가
        passwdck.insertAdjacentElement('afterend', message);
        passwdck.addEventListener("input", function () {
            if (passwd.value === passwdck.value) {
                message.textContent = "비밀번호가 일치합니다.";
                document.querySelector('input[type="submit"]').disabled = false; // 비밀번호 일치 시 회원가입 버튼 활성화
            } else {
                message.textContent = "비밀번호가 일치하지 않습니다.";
                
            }
        });
        // 회원가입 요청(submit) 시 비밀번호 일치 여부 확인
        document.querySelector('form').addEventListener("submit", function (event) {
            if (passwd.value !== passwdck.value) {
                event.preventDefault(); // submit 동작 중지
                alert("비밀번호가 일치하지 않습니다."); // 비밀번호 불일치 시 알림창 표시
            }
        });
    });
    
    
    function validatePersonalId() {
        var personalId1 = document.getElementById('personal_id_1').value;
        var personalId2 = document.getElementById('personal_id_2').value;

        // 각 입력 필드의 길이를 확인
        if (personalId1.length !== 6 || personalId2.length !== 7) {
            alert("주민등록번호를 다시 입력해주세요.");
            return false;
        }

        // 숫자로 이루어진지 확인
        var numericRegex = /^[0-9]+$/;
        if (!personalId1.match(numericRegex) || !personalId2.match(numericRegex)) {
            alert("주민등록번호는 숫자로만 입력해야 합니다.");
            return false;
        }

        // 여기에 추가적인 유효성 검사 규칙을 추가할 수 있습니다.

        return true;
    }
    

     
     function validateSimplePw(){
        var simplePwInput = document.getElementById('simplePw');
        if(simplePwInput.value.length !== 6){
           alert("간편 비밀번호를 다시 입력해주세요."); // 간편 비밀번호가 6자리가 아닐 경우 알림 메시지 표시
           return false;
        }
        
        return true;
     }
    
    function validateAddress() {
        var postcodeInput = document.getElementById('sample6_postcode');
        var addressInput = document.getElementById('sample6_address');
        var detailAddressInput = document.getElementById('sample6_detailAddress');
        if (postcodeInput.value.trim() === '' || addressInput.value.trim() === '' || detailAddressInput.value.trim() === '') {
            alert('주소를 모두 입력해주세요.');
            return false;
        }
        return true;
    }
    

  var idCk = false;
  function checkDuplicateId() {
    var id = document.getElementById("id").value; // 입력된 아이디 가져오기
    
    // AJAX 요청 수행
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "checkId.member", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    console.log("readyState:", xhr.readyState, "status:", xhr.status);
    xhr.onreadystatechange = function() {
         console.log("readyState:", xhr.readyState, "status:", xhr.status);
         if (xhr.readyState === 4) {
           if (xhr.status === 200) {
             var response = xhr.responseText; // AJAX 응답 받기
            
             var ckResult = response === "true"; // 중복 결과에 따라 ckResult 설정

        // ckResult 값에 따른 로직 처리
        if (ckResult) {
          idCk = true;

          
          alert("아이디 사용이 가능합니다.")
        } else {
          alert("중복된 아이디입니다. 다른 아이디를 입력해주세요.");
        }
      }
    } else {
        console.log('Error:', xhr.status, xhr.statusText); // 추가된 에러 메시지
      }
    }
  
    xhr.send("id=" + encodeURIComponent(id)); // 요청 전달 데이터 설정
  }
  
  
  
  function validateId() {

       
       if (idCk) {
         return true;
       } else {
          alert("아이디 중복체크가 필요합니다.")
         return false;
       }
     }

  
    var verifyCode = ""; // 인증 코드를 저장할 변수

    function sendMail() {
      alert("인증번호가 발송되었습니다.")     
      var email = document.querySelector("input[name='email']").value; // 이메일 값 가져오기
      console.log(email);
      // 인증 메일 발송 동작 수행
      var xhr = new XMLHttpRequest();
      xhr.open("POST", "mail.member", true);
      xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
          verifyCode = xhr.responseText; // AJAX 응답 받기
          console.log(xhr.responseText);
          // 인증 코드를 받은 후에 다른 처리를 수행
          // 예: 화면에 인증 코드 입력 필드를 표시하거나, 인증 코드를 이용한 추가 동작 수행 등
        }
      };
      xhr.send("email=" + encodeURIComponent(email));

      // 인증 코드를 받은 후의 동작을 여기에 추가
    }
    var codeCk = false;
    function checkCode() {
        var inputCode = document.querySelector('input[name="verifyCode"]').value;
        if (verifyCode === inputCode) {
            alert("인증 성공");
            codeCk = true; // codeCk를 지역 변수로 변경
        } else {
            alert("인증번호가 일치하지 않습니다.");
            codeCk = false; // codeCk를 지역 변수로 변경
        }
        return codeCk; // codeCk 값을 반환
    }
    
    function validateEmail() {
       if (codeCk) {
          return true;
        } else {
            alert("메일 인증이 필요합니다.")
          return false;
        }
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

      <!--  중간페이지  -->

      <div class="section1">
         <div class="title">서비스 사용을 위한 추가 정보</div>
         <div class="container">
            <% 
            String message = (String) session.getAttribute("message");
            String kakaoId = (String) session.getAttribute("kakaoId");
            session.invalidate();
            %>
            <script>
                var message = '<%=message%>';
                if (message !== 'null' && message !== '') {
                    alert(message);
                }
            </script>
            <div class="form-container">
                <form action="kakaoJoin.member" method="post" onsubmit="return validatePersonalId() && validateSimplePw() && validateAddress()">
                    <!--  <h2>회원가입</h2> -->
                    <div class="form-group">
                        <label for="simplePw">간편 비밀번호:</label> <input type="password"
                        name="simplePw" id="simplePw" placeholder="간편 비밀번호 6자리를 입력해주세요."
                        required value="${param.simplePw}" oninput="limitSimplePw(this)"
                        maxlength="6"> <label for="personal_id">주민등록번호:</label>
                    </div>
                    <div class="form-group">

                    </div>      
                    <div class="personal-id-input">
                        <input type="text" name="personal_id_1" id="personal_id_1"
                            placeholder="6자리" required maxlength="6"
                            oninput="limitPersonalId(this)"> <span>-</span> <input
                            type="text" name="personal_id_2" id="personal_id_2"
                            placeholder="7자리" required maxlength="7"
                            oninput="limitPersonalId(this)">
                    </div>
                    <div class="form-group">
                        <label for="phone">전화번호:</label> <input type="text" id="phone"
                        name="phone" oninput="limitPhoneNumber()" pattern="[0-9]{11}"
                        placeholder="전화번호를 입력해주세요." required value="${param.phone}" oninput="limitPhoneNumber()">
                    </div>
                    <div class="form-group">
                        <label for="address">주소:</label> <input type="text"
                        id="sample6_postcode" name="zipcode" placeholder="우편번호"><br>
                        <div class="button-container">

                            <button type="button" class="input-group-btn"
                            onclick="sample6_execDaumPostcode()">우편번호 찾기</button>

                            <!--  <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>  -->
                        </div>
                        <br> <input type="text" id="sample6_address" name="address"
                        placeholder="주소"><br> <input type="text"
                        id="sample6_detailAddress" name="detailAddress"
                        placeholder="상세주소">
                    </div>
                    <input type="hidden" name="kakaoId" value="<%= kakaoId %>"> <!-- 카카오 아이디 가져오기 -->

                    <div class="button-container">
                        <input type="submit" value="회원가입 요청" class="btn btn-primary"
                        style="background-color: #8298A6 !important; color: #ffffff !important; font-size: 15px !important; font-weight: bold !important; border-radius: 30px !important; padding: 12px 80px; cursor: pointer;">
                    </div>
                </form>
                <!-- 부트스트랩 JavaScript 연결 -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>
            </div>
         </div>
      </div>
      <!--  footer -->

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