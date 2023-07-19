package message;

public class AccountUpdateInfoDTO {
    private String accountNumber;
    private String bankCode;

    public AccountUpdateInfoDTO() {
    }

    public AccountUpdateInfoDTO(String accountNumber, String bankCode) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}