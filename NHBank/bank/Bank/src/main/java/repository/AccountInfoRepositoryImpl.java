package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import vo.AccountInfoDTO;

public class AccountInfoRepositoryImpl implements AccountInfoRepository {

    private static AccountInfoRepositoryImpl instance = null;

    private final String DB_URL =
            "jdbc:oracle:thin:@dinkdb_medium?TNS_ADMIN=/opt/wallet/Wallet_DinkDB"; // 데이터베이스 url
    private final String USER = "DA2320";
    private final String PASS = "Data2320";


    public AccountInfoRepositoryImpl() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * private DataSource ds;
     * 
     * public AccountInfoRepositoryImpl() { try { Context ctx = new InitialContext(); ds =
     * (DataSource) ctx.lookup("java:comp/env/jdbc/oracle"); } catch (Exception e) {
     * e.printStackTrace(); } }
     */
    public static AccountInfoRepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (AccountInfoRepositoryImpl.class) {
                if (instance == null) {
                    instance = new AccountInfoRepositoryImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void insertAccount(AccountInfoDTO dto) {
        String query =
                "INSERT INTO account_info_nh (account_number, member_id, bank_code, branch_code, account_password, balance, nickname, product_id, account_status, open_banking_registered_yn)\r\n"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, dto.getAccountNumber());
            pstmt.setString(2, dto.getMemberId());
            pstmt.setString(3, dto.getBankCode());
            pstmt.setString(4, dto.getBranchCode());
            pstmt.setString(5, dto.getAccountPassword());
            pstmt.setInt(6, dto.getBalance());
            pstmt.setString(7, dto.getNickname());
            pstmt.setInt(8, dto.getProductId());
            pstmt.setInt(9, dto.getAccountStatus());
            pstmt.setInt(10, 0);
            int iResult = pstmt.executeUpdate();

            if (iResult >= 1) {
                System.out.println("insert success");
            } else {
                System.out.println("insert fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AccountInfoDTO> findAccountsByMemberId(String memberId) {
        List<AccountInfoDTO> accountInfos = new ArrayList<>();
        String query = "SELECT * FROM account_info_nh WHERE member_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, memberId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountInfoDTO accountInfo = new AccountInfoDTO(rs.getString("account_number"),
                        rs.getString("member_id"), rs.getString("bank_code"),
                        rs.getString("branch_code"), rs.getString("account_password"),
                        rs.getInt("balance"), rs.getString("nickname"), rs.getInt("product_id"),
                        rs.getInt("account_status"), rs.getDate("reg_date"));
                accountInfos.add(accountInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountInfos;
    }

    @Override
    public List<AccountInfoDTO> findMyAccountsByMemberId(String memberId) {
        List<AccountInfoDTO> accountInfos = new ArrayList<>();
        String query = "SELECT * FROM account_info_nh WHERE member_id = ? and account_status = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, memberId);
            ps.setInt(2, 1);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AccountInfoDTO accountInfo = new AccountInfoDTO(rs.getString("account_number"),
                            rs.getString("member_id"), rs.getString("bank_code"),
                            rs.getString("branch_code"), rs.getString("account_password"),
                            rs.getInt("balance"), rs.getString("nickname"), rs.getInt("product_id"),
                            rs.getInt("account_status"), rs.getDate("reg_date"));
                    accountInfos.add(accountInfo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountInfos;
    }


    @Override
    public List<String> findAccountNumbersByMemberId(String memberId) {
        List<String> accountNumbers = new ArrayList<>();
        String query =
                "SELECT account_number FROM account_info_nh WHERE member_id = ? and account_status = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, memberId);
            ps.setInt(2, 1);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    accountNumbers.add(rs.getString("account_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountNumbers;
    }

    public String getMemberName(String accountNumber) {
        String memberName = null;
        String memberIdQuery = "SELECT member_id FROM account_info_nh WHERE account_Number = ?";
        String nameQuery = "SELECT name FROM member_nh WHERE member_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // account_info_hana 테이블에서 accountNumber와 password를 이용해 member_id 가져오기
            try (PreparedStatement memberIdPs = conn.prepareStatement(memberIdQuery)) {
                memberIdPs.setString(1, accountNumber);

                try (ResultSet rs = memberIdPs.executeQuery()) {
                    if (rs.next()) {
                        String memberId = rs.getString("member_id");

                        // member_hana 테이블에서 member_id를 이용해 name 가져오기
                        try (PreparedStatement namePs = conn.prepareStatement(nameQuery)) {
                            namePs.setString(1, memberId);

                            try (ResultSet rs2 = namePs.executeQuery()) {
                                if (rs2.next()) {
                                    memberName = rs2.getString("name");
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberName;
    }

    @Override
    public void updateRegisteredYn(String accountNumber, String bankCode) {
        String query =
                "UPDATE account_info_nh SET account_status = 1 WHERE account_number = ? AND bank_code = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, accountNumber);
            ps.setString(2, bankCode);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getBalance(String accountNumber) {
        int balance = 0;
        String query = "SELECT balance FROM account_info_nh WHERE account_Number = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, accountNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    balance = rs.getInt("balance");
                }
            }

        } catch (SQLException e) {
            // 적절한 예외 처리를 수행합니다.
            e.printStackTrace();
        }

        return balance;
    }

    @Override
    public List<String> findMyBankAccountNumbersByMemberId(String memberId) {
        List<String> accountNumbers = new ArrayList<>();
        String query = "SELECT account_number FROM account_info_nh WHERE member_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, memberId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    accountNumbers.add(rs.getString("account_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountNumbers;
    }

}
