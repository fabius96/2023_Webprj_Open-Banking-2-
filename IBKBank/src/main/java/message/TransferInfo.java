package message;

public class TransferInfo {
    private String accountNumber1;
    private String bankCode1;
    private String accountNumber2;
    private String bankCode2;
    private int amount;
    private String content;
    
    public TransferInfo() {
        
    }
    public TransferInfo(String accountNumber1, String bankCode1, String accountNumber2,
            String bankCode2, int amount, String content) {
        super();
        this.accountNumber1 = accountNumber1;
        this.bankCode1 = bankCode1;
        this.accountNumber2 = accountNumber2;
        this.bankCode2 = bankCode2;
        this.amount = amount;
        this.content = content;
    }
    public String getAccountNumber1() {
        return accountNumber1;
    }
    public void setAccountNumber1(String accountNumber1) {
        this.accountNumber1 = accountNumber1;
    }
    public String getBankCode1() {
        return bankCode1;
    }
    public void setBankCode1(String bankCode1) {
        this.bankCode1 = bankCode1;
    }
    public String getAccountNumber2() {
        return accountNumber2;
    }
    public void setAccountNumber2(String accountNumber2) {
        this.accountNumber2 = accountNumber2;
    }
    public String getBankCode2() {
        return bankCode2;
    }
    public void setBankCode2(String bankCode2) {
        this.bankCode2 = bankCode2;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }


}

