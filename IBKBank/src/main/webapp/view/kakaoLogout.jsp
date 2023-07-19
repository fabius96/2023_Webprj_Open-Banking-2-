<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<script type="text/javascript">
Kakao.init("a833cca1f83f9b38586b88f445bd462f"); // API 키값

function kakaoLogout() {
     if (Kakao.Auth.getAccessToken()) {
          Kakao.API.request({
            url: '/v1/user/unlink',
            success: function (response) {
                console.log(response)
                location.href="mainHana.jsp"
                
            },
            fail: function (error) {
              console.log(error)
            },
          })
          Kakao.Auth.setAccessToken(undefined)
        }
    }
</script>
</head>
<body>
    <script>
        kakaoLogout();
    </script>
</body>
</html>
