package service;

import java.util.List;
import vo.AccountTransferInfoDTO;

public interface AccountTransferService {
    //인출
    public boolean withdraw(String accountNumber1, int amount);
    
    //입금
    public boolean deposit(String accountNumber2, int amount);
    
    //거래내역 등록
    public void insertTransferInfo(String accountNumber1, String bankCode1, String accountNumber2,
            String bankCode2, int amount, String content, String string);
    
    //계좌번호로 거래내역 조회
    public List<AccountTransferInfoDTO> findTransferInfoByAccountNumber(String accountNumber);
}
