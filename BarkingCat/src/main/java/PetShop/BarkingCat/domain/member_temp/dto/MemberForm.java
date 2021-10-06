package PetShop.BarkingCat.domain.member_temp.dto;

import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.service.MemberValidator;
import PetShop.BarkingCat.domain.member_temp.model.MemberTemp;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class MemberForm {

    private String email;

    private String password;

    private String phone;

    private Member.MemberType memberType;

    private String name;

    private String businessNumber;

    private Member.AuthStatus authStatus;

    public MemberTemp entity(MemberValidator memberValidator, PasswordEncoder passwordEncoder) {
        validate(memberValidator);
        encryptPassword(passwordEncoder);
        return MemberTemp
                .builder()
                .email(email)
                .password(password)
                .phone(phone)
                .memberType(memberType)
                .name(name)
                .businessNumber(businessNumber)
                .build();
    }

    private void validate(MemberValidator memberValidator) {
        memberValidator.validateEmail(email);
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
