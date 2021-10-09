package PetShop.BarkingCat.domain.member.model;

import PetShop.BarkingCat.common.base.model.Base;
import PetShop.BarkingCat.domain.member.dto.MemberPayload;
import PetShop.BarkingCat.domain.member.model.objects.Email;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
public class Member extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private Email email;

    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private String name;

    private String businessNumber;

    protected Member() {
    }

    @Builder
    public Member(Long id, Email email, String password, String phone, MemberType memberType, String name, String businessNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.memberType = memberType;
        this.name = name;
        this.businessNumber = businessNumber;
        validateBusinessNumber();
    }

    public void checkPassword(String password, PasswordEncoder passwordEncoder) {
        if (passwordIsNotEqual(password, passwordEncoder)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }
    }

    private boolean passwordIsNotEqual(String password, PasswordEncoder passwordEncoder) {
        return !passwordEncoder.matches(password, this.password);
    }

    private void validateBusinessNumber() {
        if (isNormalMember()) {
            return;
        }

        if (businessNumber.isBlank()) {
            throw new RuntimeException("사업자번호를 입력하여야 합니다");
        }
    }

    private boolean isNormalMember() {
        return memberType == Member.MemberType.NORMAL;
    }

    public enum MemberType {
        NORMAL, COMPANY, SHELTER
    }

    public enum AuthStatus {
        Y, N
    }

    public MemberPayload createPayload() {
        return new MemberPayload(id, email.content());
    }
}
