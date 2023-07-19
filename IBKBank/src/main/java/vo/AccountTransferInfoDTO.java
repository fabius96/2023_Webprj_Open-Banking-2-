package vo;

public class AccountTransferInfoDTO {
    private String transferId;
    private String accountNumber1;
    private String accountNumber2;
    private int tranAmt;
    private String content;
    private String inoutType;
    private String tranDate;
    private String tranTime;


    public AccountTransferInfoDTO(String transferId, String accountNumber1, String accountNumber2,
            int tranAmt, String content, String inoutType, String tranDate, String tranTime) {
        super();
        this.transferId = transferId;
        this.accountNumber1 = accountNumber1;
        this.accountNumber2 = accountNumber2;
        this.tranAmt = tranAmt;
        this.content = content;
        this.inoutType = inoutType;
        this.tranDate = tranDate;
        this.tranTime = tranTime;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getAccountNumber1() {
        return accountNumber1;
    }

    public void setAccountNumber1(String accountNumber1) {
        this.accountNumber1 = accountNumber1;
    }

    public String getAccountNumber2() {
        return accountNumber2;
    }

    public void setAccountNumber2(String accountNumber2) {
        this.accountNumber2 = accountNumber2;
    }

    public int getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(int tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInoutType() {
        return inoutType;
    }

    public void setInoutType(String inoutType) {
        this.inoutType = inoutType;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

}
