package PetShop.BarkingCat.domain.member.dto;

import PetShop.BarkingCat.domain.member.model.Member;
import lombok.Data;

@Data
public class MemberForm {

    private String email;

    private String password;

    private String phone;

    private Member.MemberType memberType;

    private String name;

    private String businessNumber;

    private Member.AuthStatus authStatus;

    public Member entity(){
        return Member
                .builder()
                .email(email)
                .password(password)
                .phone(phone)
                .memberType(memberType)
                .name(name)
                .businessNumber(businessNumber)
                .authStatus(authStatus)
                .build();

    }

}
