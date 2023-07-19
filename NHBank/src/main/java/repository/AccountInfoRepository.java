package repository;

import java.util.List;
import vo.AccountInfoDTO;

public interface AccountInfoRepository {

    // 계좌개설
    public void insertAccount(AccountInfoDTO dto);

    // 멤버 아이디로 전체 본인 계좌 가져오기
    public List<AccountInfoDTO> findAccountsByMemberId(String memberId);

    // 멤버 아이디로 오픈뱅킹에 연동된 내 계좌 찾기
    public List<AccountInfoDTO> findMyAccountsByMemberId(String memberId);

    // 멤버 아이디로 특정 계좌번호들 찾기
    public List<String> findAccountNumbersByMemberId(String memberId);

    // 계좌번호로 회원이름 찾기 (계좌 주 조회)
    public String getMemberName(String accountNumber);

    // 오픈뱅킹 등록
    void updateRegisteredYn(String accountNumber, String bankCode);


    // 잔액 조회
    public int getBalance(String accountNumber);
    // 멤버 아이디로 자행 계좌번호들 찾기
    public List<String> findMyBankAccountNumbersByMemberId(String memberId);
}
