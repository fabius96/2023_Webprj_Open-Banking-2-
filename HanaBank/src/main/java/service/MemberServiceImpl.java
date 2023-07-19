package service;

import java.util.ArrayList;
import repository.MemberRepository;
import repository.MemberRepositoryImpl;
import vo.MemberDTO;

public class MemberServiceImpl implements MemberService {

    @Override
    public boolean checkId(String uId) {
        MemberRepository repository = MemberRepositoryImpl.getInstance();

        return repository.checkId(uId);

    }

    @Override
    public void insertKaKaoMember(String id, String nickName, String email) {
        MemberRepository repository = MemberRepositoryImpl.getInstance();

        repository.insertKaKaoMember(id, nickName, email);

    }

    @Override
    public MemberDTO memberLoginCheck(String kakaoId) {
        MemberRepository repository = MemberRepositoryImpl.getInstance();

        return repository.memberLoginCheck(kakaoId);
    }

    @Override
    public void kakaoJoin(String id, String simplePw, String phone, String personID, String gender,
            String zipcode, String address, String detailAddress) {

        MemberRepository repository = MemberRepositoryImpl.getInstance();

        // 성별
        char genderDigit = personID.charAt(6);
        if (genderDigit == '1' || genderDigit == '3') {
            gender = "M";
        } else {
            gender = "F";
        }
        // 주민등록번호 6자리 추출
        String birth = personID.substring(0, 6);

        repository.kakaoJoin(id, simplePw, phone, personID, gender, birth, zipcode, address,
                detailAddress);

    }

    @Override
    public String mailSend(String email) {

        MemberRepository repository = MemberRepositoryImpl.getInstance();

        return repository.mailSend(email);

    }

    @Override
    public boolean personIdCheck(MemberDTO dto) {

        MemberRepository repository = MemberRepositoryImpl.getInstance();

        return repository.personIdCheck(dto);

    }

    @Override
    public boolean joinMember(MemberDTO dto) {
        MemberRepository repository = MemberRepositoryImpl.getInstance();
        // 주민등록번호에서 7번째 자리 값 확인
        String personID = dto.getPersonalIdNumber();

        char genderDigit = personID.charAt(6);
        if (genderDigit == '1' || genderDigit == '3') {
            dto.setGender("M");
        } else {
            dto.setGender("F");
        }

        // 주민등록번호 6자리 추출
        String birth = personID.substring(0, 6);
        dto.setBirth(birth);


        // 주민등록번호 중복체크
        if (repository.personIdCheck(dto)) {
            System.out.println("주민등록번호 중복 아님");
            repository.joinMember(dto);
            return true;


        } else {
            System.out.println("주민등록번호 중복!");
            return false;

        }
    }

    @Override
    public MemberDTO memberLoginCheck(String ckId, String ckPw) {

        MemberRepository repository = MemberRepositoryImpl.getInstance();
        return repository.memberLoginCheck(ckId, ckPw);

    }

    @Override
    public String findMemberIdByPersonalIdNumber(String personalIdNumber) {

        MemberRepository repository = MemberRepositoryImpl.getInstance();
        return repository.findMemberIdByPersonalIdNumber(personalIdNumber);
    }

    @Override
    public ArrayList<MemberDTO> memberBeforeSelect() {
        System.out.println("회원조회 service 시작");
        MemberRepository repository = MemberRepositoryImpl.getInstance();
        return repository.memberBeforeSelect();
    }

    @Override
    public boolean joinOkfunction(String id) {
        MemberRepository repository = MemberRepositoryImpl.getInstance();
        return repository.joinOkfunction(id);
    }

}
