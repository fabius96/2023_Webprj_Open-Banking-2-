package service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import repository.AccountInfoRepository;
import repository.AccountInfoRepositoryImpl;
import vo.AccountInfoDTO;

public class AccountInfoServiceImpl implements AccountInfoService {


    @Override
    public void insertAccount(String memberId, String productName, String bankCode, String nickname,
            String accountPassword) {

        String accountNumber = "001" + "-" + getRandomNumber() + "-" + getRandomNumber();
        bankCode = "하나"; // 하나은행
        String branchCode = "001";
        int balance = 0;
        int productId = 0;
        int accountStatus = 0;
        int openBankingStatus = 0;
        Date regDate = null;

        if (productName.equals("입출금상품A")) {
            productId = 100;
        } else if (productName.equals("입출금상품B")) {
            productId = 101;
        } else if (productName.equals("예금상품A")) {
            productId = 200;
        } else if (productName.equals("예금상품B")) {
            productId = 201;
        } else if (productName.equals("적금상품A")) {
            productId = 300;
        } else if (productName.equals("적금상품B")) {
            productId = 301;
        }



        AccountInfoDTO dto = new AccountInfoDTO(accountNumber, memberId, bankCode, branchCode,
                accountPassword, balance, nickname, productId, accountStatus, regDate);
        AccountInfoRepository repository = AccountInfoRepositoryImpl.getInstance();
        repository.insertAccount(dto);

    }

    private String getRandomNumber() {
        Random random = new Random();
        return Integer.toString(random.nextInt(9000));
    }

    @Override
    public List<AccountInfoDTO> findAccountsByMemberId(String memberId) {
        AccountInfoRepository repository = AccountInfoRepositoryImpl.getInstance();
        return repository.findAccountsByMemberId(memberId);
    }


    @Override
    public List<AccountInfoDTO> findMyAccountsByMemberId(String memberId) {
        AccountInfoRepository repository = AccountInfoRepositoryImpl.getInstance();
        return repository.findMyAccountsByMemberId(memberId);
    }

    @Override
    public List<String> findAccountNumbersByMemberId(String memberId) {
        AccountInfoRepository repository = AccountInfoRepositoryImpl.getInstance();
        return repository.findAccountNumbersByMemberId(memberId);
    }

    @Override
    public String getMemberName(String accountNumber) {
        AccountInfoRepository repository = AccountInfoRepositoryImpl.getInstance();
        return repository.getMemberName(accountNumber);
    }


    @Override
    public void registrateOpenbanking(String accountNumber, String bankCode) {
        AccountInfoRepository repository = AccountInfoRepositoryImpl.getInstance();
        repository.updateRegisteredYn(accountNumber, bankCode);
    }

    @Override
    public int checkBalance(String accountNumber) {
        AccountInfoRepository repository = AccountInfoRepositoryImpl.getInstance();
        return repository.getBalance(accountNumber);
    }

    @Override
    public List<String> findMyBankAccountNumbersByMemberId(String memberId) {
        AccountInfoRepository repository = AccountInfoRepositoryImpl.getInstance();
        return repository.findMyBankAccountNumbersByMemberId(memberId);
    }
}
