package service;

import java.util.List;
import repository.AccountTransferRepository;
import repository.AccountTransferRepositoryImpl;
import vo.AccountTransferInfoDTO;

public class AccountTransferServiceImpl implements AccountTransferService {

    @Override
    public boolean withdraw(String accountNumber1, int amount) {

        AccountTransferRepository repository = AccountTransferRepositoryImpl.getInstance();
        return repository.withdraw(accountNumber1, amount);
    }

    @Override
    public boolean deposit(String accountNumber2, int amount) {

        AccountTransferRepository repository = AccountTransferRepositoryImpl.getInstance();
        return repository.deposit(accountNumber2, amount);
    }

    @Override
    public void insertTransferInfo(String accountNumber1, String bankCode1, String accountNumber2,
            String bankCode2, int amount, String content, String string) {

        AccountTransferRepository repository = AccountTransferRepositoryImpl.getInstance();
        repository.insertTransferInfo(accountNumber1, bankCode1, accountNumber2, bankCode2, amount,
                content, string);
    }

    @Override
    public List<AccountTransferInfoDTO> findTransferInfoByAccountNumber(String accountNumber) {

        AccountTransferRepository repository = AccountTransferRepositoryImpl.getInstance();
        return repository.findTransferInfoByAccountNumber(accountNumber);
    }

}
