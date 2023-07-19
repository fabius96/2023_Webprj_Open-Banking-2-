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
import vo.AccountTransferInfoDTO;

public class AccountTransferRepositoryImpl implements AccountTransferRepository {

    private static AccountTransferRepositoryImpl instance = null;

     private final String DB_URL =
     "jdbc:oracle:thin:@dinkdb_medium?TNS_ADMIN=/opt/wallet/Wallet_DinkDB"; // 데이터베이스 url
    private final String USER = "DA2319";
    private final String PASS = "Data2319";

    public AccountTransferRepositoryImpl() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AccountTransferRepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (MemberRepositoryImpl.class) {
                if (instance == null) {
                    instance = new AccountTransferRepositoryImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean withdraw(String accountNumber1, int amount) {
        boolean result = true;
        String query = "UPDATE account_info_ibk SET balance = balance - ? WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, amount);
            pstmt.setString(2, accountNumber1);
            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deposit(String accountNumber2, int amount) {
        boolean result = true;
        String query = "UPDATE account_info_ibk SET balance = balance + ? WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, amount);
            pstmt.setString(2, accountNumber2);
            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void insertTransferInfo(String accountNumber1, String bankCode1, String accountNumber2,
            String bankCode2, int amount, String content, String string) {
        String query =
                "INSERT INTO AccountTransferInfo_ibk (transfer_id, account_Number1, account_Number2, tran_Amt, content, inout_Type) "
                        + "VALUES (TRANSFER_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, accountNumber1);
            pstmt.setString(2, accountNumber2);
            pstmt.setInt(3, amount);
            pstmt.setString(4, content);
            pstmt.setString(5, string);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new transfer was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<AccountTransferInfoDTO> findTransferInfoByAccountNumber(String accountNumber) {
        List<AccountTransferInfoDTO> accountTransferInfos = new ArrayList<>();
        String query = "SELECT * FROM AccountTransferInfo_ibk WHERE account_Number1 = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, accountNumber);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    AccountTransferInfoDTO accountTransferInfo = new AccountTransferInfoDTO(
                            rs.getString("transfer_id"), rs.getString("account_Number1"),
                            rs.getString("account_Number2"), rs.getInt("tran_Amt"),
                            rs.getString("content"), rs.getString("inout_Type"),
                            rs.getString("tran_Date"), rs.getString("tran_Time"));
                    accountTransferInfos.add(accountTransferInfo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return accountTransferInfos;
    }




}
