package PetShop.BarkingCat.domain.member_temp.model;

import PetShop.BarkingCat.common.base.model.Base;
import PetShop.BarkingCat.domain.member.model.Member;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class MemberTemp extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String phone;

    private Member.MemberType memberType;

    private String name;

    private String businessNumber;

    public MemberTemp() {
    }

    @Builder
    public MemberTemp(Long id, String email, String password, String phone, Member.MemberType memberType, String name, String businessNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.memberType = memberType;
        this.name = name;
        this.businessNumber = businessNumber;
    }

    public Member createMember() {
        return Member.builder()
                .email(email)
                .password(password)
                .phone(phone)
                .memberType(memberType)
                .name(name)
                .businessNumber(businessNumber)
                .build();
    }
}
