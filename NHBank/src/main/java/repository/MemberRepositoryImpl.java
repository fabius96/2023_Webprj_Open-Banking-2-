package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import vo.MemberDTO;

public class MemberRepositoryImpl implements MemberRepository {

    private static MemberRepositoryImpl instance = null;

    private final String DB_URL =
            "jdbc:oracle:thin:@dinkdb_medium?TNS_ADMIN=/opt/wallet/Wallet_DinkDB"; // 데이터베이스 url
    private final String USER = "DA2304";
    private final String PASS = "Data2304";

    public MemberRepositoryImpl() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MemberRepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (MemberRepositoryImpl.class) {
                if (instance == null) {
                    instance = new MemberRepositoryImpl();
                }
            }
        }
        return instance;
    }

    public boolean checkId(String uId) {
        boolean ck = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM member_nh WHERE member_id = '" + uId + "'")) {

            System.out.println("id 중복 체크 시작");

            if (rs.next()) {
                // 이미 해당 아이디가 존재하는 경우
                System.out.println("join fail");
            } else {
                // 해당 아이디가 존재하지 않는 경우
                ck = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(ck);
        return ck;
    }

    @Override
    public void insertKaKaoMember(String id, String nickName, String email) {
        System.out.println("insert 메소드 시작");

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO member_nh (member_id, name, email, member_status) VALUES (?, ?, ?, ?)")) {

            pstmt.setString(1, id);
            pstmt.setString(2, nickName);
            pstmt.setString(3, email);
            pstmt.setInt(4, 1); // 최초 로그인 시 상태값 정상

            int iResult = pstmt.executeUpdate();

            if (iResult >= 1) {
                System.out.println("insert success");
            } else {
                System.out.println("insert fail");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MemberDTO memberLoginCheck(String kakaoId) {
        System.out.println("카카오 login 체크");

        MemberDTO dto = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM member_nh WHERE member_id = '" + kakaoId + "'")) {

            while (rs.next()) {
                String memberId = rs.getString("member_id");
                if (kakaoId.equals(memberId)) {
                    String name = rs.getString("name");
                    String userPassword = rs.getString("user_password");
                    String easyPassword = rs.getString("easy_password");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String personalIdNumber = rs.getString("personal_id_number");
                    String gender = rs.getString("gender");
                    String birth = rs.getString("birth");
                    String zipcode = rs.getString("zipcode");
                    String address = rs.getString("address");
                    String detailAddress = rs.getString("detail_address");
                    String regDate = rs.getString("reg_date");
                    int memberStatus = rs.getInt("member_status");
                    String withdrawalDate = rs.getString("withdrawal_date");

                    dto = new MemberDTO(memberId, name, userPassword, easyPassword, email, phone,
                            personalIdNumber, gender, birth, zipcode, address, detailAddress,
                            regDate, memberStatus, withdrawalDate);
                } else {
                    System.out.println("login fail");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dto;
    }

    @Override
    public void kakaoJoin(String id, String simplePw, String phone, String personID, String gender,
            String birth, String zipcode, String address, String detailAddress) {
        System.out.println("update 시작 (카카오)");

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE member_nh SET easy_password = ?, phone = ?, personal_id_number = ?, gender = ?, birth = ?, zipcode = ?, address = ?, detail_address = ? WHERE member_id = ?")) {

            pstmt.setString(1, simplePw);
            pstmt.setString(2, phone);
            pstmt.setString(3, personID);
            pstmt.setString(4, gender);
            pstmt.setString(5, birth);
            pstmt.setString(6, zipcode);
            pstmt.setString(7, address);
            pstmt.setString(8, detailAddress);
            pstmt.setString(9, id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("update 성공");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String mailSend(String email) {
        String verificationCode = "";
        String host = "smtp.naver.com"; // 네이버 SMTP 서버
        String user = "ckm45@naver.com"; // 네이버 계정
        String password = "chlrudals4545?"; // 네이버 계정 비밀번호

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.naver.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            message.setSubject("SMTP TEST");

            verificationCode = generateVerificationCode();
            String mailContent = "TEST MAIL \n인증 번호는 " + verificationCode + "입니다.";
            message.setText(mailContent);

            Transport.send(message);
            System.out.println("Success Message Sent");

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return verificationCode;
    }

    public String generateVerificationCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }

    public boolean personIdCheck(MemberDTO dto) {
        boolean ck = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM member_nh WHERE personal_id_number = '" + dto.getPersonalIdNumber() + "'")) {

            System.out.println("if문 시작");

            if (rs.next()) {
                // 이미 해당 아이디가 존재하는 경우
                System.out.println("join fail");
            } else {
                // 해당 아이디가 존재하지 않는 경우
                ck = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(ck);
        return ck;
    }

    @Override
    public void joinMember(MemberDTO dto) {
        System.out.println("insert 메소드 시작");

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO member_nh (member_id, name, user_password, easy_password, email, phone, personal_id_number, gender, birth, zipcode, address, detail_address, member_status, withdrawal_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pstmt.setString(1, dto.getMemberId());
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getUserPassword());
            pstmt.setString(4, dto.getEasyPassword());
            pstmt.setString(5, dto.getEmail());
            pstmt.setString(6, dto.getPhone());
            pstmt.setString(7, dto.getPersonalIdNumber());
            pstmt.setString(8, dto.getGender());
            pstmt.setString(9, dto.getBirth());
            pstmt.setString(10, dto.getZipcode());
            pstmt.setString(11, dto.getAddress());
            pstmt.setString(12, dto.getDetailAddress());
            pstmt.setInt(13, dto.getMemberStatus());
            pstmt.setString(14, dto.getWithdrawalDate());

            int iResult = pstmt.executeUpdate();

            if (iResult >= 1) {
                System.out.println("insert success");
            } else {
                System.out.println("insert fail");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MemberDTO memberLoginCheck(String ckId, String ckPw) {
        boolean ck = false;
        System.out.println("일반 회원 login 체크");
        MemberDTO dto = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM member_nh WHERE member_id = '" + ckId + "' AND member_status = '1'")) {

            while (rs.next()) {
                String memberId = rs.getString("member_id");
                String userPassword = rs.getString("user_password");
                System.out.println("id: " + memberId + "|pw : " + userPassword);
                if (ckId.equals(memberId) && ckPw.equals(userPassword)) {
                    String name = rs.getString("name");
                    String easyPassword = rs.getString("easy_password");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String personalIdNumber = rs.getString("personal_id_number");
                    String gender = rs.getString("gender");
                    String birth = rs.getString("birth");
                    String zipcode = rs.getString("zipcode");
                    String address = rs.getString("address");
                    String detailAddress = rs.getString("detail_address");
                    String regDate = rs.getString("reg_date");
                    int memberStatus = rs.getInt("member_status");
                    String withdrawalDate = rs.getString("withdrawal_date");

                    ck = true;
                    dto = new MemberDTO(memberId, name, userPassword, easyPassword, email, phone,
                            personalIdNumber, gender, birth, zipcode, address, detailAddress,
                            regDate, memberStatus, withdrawalDate);

                } else {
                    System.out.println("login fail");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(dto);
        return dto;
    }

    @Override
    public String findMemberIdByPersonalIdNumber(String personalIdNumber) {
        String result = "";
        String query = "SELECT member_id FROM member_nh WHERE personal_id_number = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, personalIdNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getString("member_id");
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving member_id");
            e.printStackTrace();
        }

        return result;
    }
    
    
    @Override
    // 회원가입 승인 이전 메소드
    public ArrayList<MemberDTO> memberBeforeSelect() {
        System.out.println("회원조회 repository 시작");
        ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // conn = DriverManager.getConnection(url, uid, upw);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from member_nh where member_status = '2' ");

            while (rs.next()) {
                String memberId = rs.getString("member_id");
                String name = rs.getString("name");
                String userPassword = rs.getString("user_password");
                String easyPassword = rs.getString("easy_password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String personalIdNumber= rs.getString("personal_id_number");
                String gender = rs.getString("gender");
                String birth = rs.getString("birth");
                String zipcode = rs.getString("zipcode");
                String address = rs.getString("address");
                String detailAddress = rs.getString("detail_address");
                String regDate = rs.getString("reg_date");
                int memberStatus = rs.getInt("member_status");
                String withdrawalDate = rs.getString("withdrawal_date");
                MemberDTO dto = new MemberDTO(memberId, name, userPassword, easyPassword, email,
                        phone, personalIdNumber, gender, birth, zipcode, address, detailAddress,
                        regDate, memberStatus, withdrawalDate);
                dtos.add(dto);
            }
        } catch (Exception e) {

        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(dtos);
        return dtos;
    }
    
    @Override
    // 회원가입 승인처리 메소드
    public boolean joinOkfunction(String id) {
        boolean result = true;
        Connection conn = null;
        ResultSet rs = null;
        MemberDTO dto = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // SQL 쿼리 작성
            String sql = "UPDATE member_nh SET member_status = '1' WHERE member_status = '2' AND member_id = ?";

            // PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);

            // 매개변수 값 설정
            pstmt.setString(1, id);

            // 쿼리 실행
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
