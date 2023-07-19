package vo;


public class MemberDTO {
    private String memberId;
    private String name;
    private String userPassword;
    private String easyPassword;
    private String email;
    private String phone;
    private String personalIdNumber;
    private String gender;
    private String birth;
    private String zipcode;
    private String address;
    private String detailAddress;
    private String regDate;
    private int memberStatus;
    private String withdrawalDate;

    


    public MemberDTO(String memberId, String name, String userPassword, String easyPassword,
            String email, String phone, String personalIdNumber, String gender, String birth,
            String zipcode, String address, String detailAddress, String regDate, int memberStatus,
            String withdrawalDate) {
        super();
        this.memberId = memberId;
        this.name = name;
        this.userPassword = userPassword;
        this.easyPassword = easyPassword;
        this.email = email;
        this.phone = phone;
        this.personalIdNumber = personalIdNumber;
        this.gender = gender;
        this.birth = birth;
        this.zipcode = zipcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.regDate = regDate;
        this.memberStatus = memberStatus;
        this.withdrawalDate = withdrawalDate;
    }
    
    public MemberDTO(String memberId, String name, String userPassword, String easyPassword, String email,
            String phone, String personalIdNumber, String zipcode,
            String address, String detailAddress) {
        super();
        this.memberId = memberId;
        this.name = name;
        this.userPassword = userPassword;
        this.easyPassword = easyPassword;
        this.email = email;
        this.phone = phone;
        this.personalIdNumber = personalIdNumber;
        this.gender = null;
        this.birth = null;
        this.zipcode = zipcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.regDate = null;
        this.memberStatus = 2;
        this.withdrawalDate = null;
    }
    
    

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEasyPassword() {
        return easyPassword;
    }

    public void setEasyPassword(String easyPassword) {
        this.easyPassword = easyPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonalIdNumber() {
        return personalIdNumber;
    }

    public void setPersonalIdNumber(String personalIdNumber) {
        this.personalIdNumber = personalIdNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }


    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(int memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getWithdrawalDate() {
        return withdrawalDate; 
    }

    public void setWithdrawalDate(String withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }


   
    @Override
    public String toString() {
        return "MemberDto [memberId=" + memberId + ", name=" + name + ", userPassword="
                + userPassword + ", easyPassword=" + easyPassword + ", email=" + email + ", phone="
                + phone + ", personalIdNumber=" + personalIdNumber + ", gender=" + gender
                + ", birth=" + birth + ", zipcode=" + zipcode + ", address=" + address
                + ", detailAddress=" + detailAddress + ", regDate=" + regDate + ", memberStatus="
                + memberStatus + ", withdrawalDate=" + withdrawalDate + "]";
    }
}
