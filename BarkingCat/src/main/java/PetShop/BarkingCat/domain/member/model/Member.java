package PetShop.BarkingCat.domain.member.model;

import PetShop.BarkingCat.common.base.model.Base;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class Member extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private String name;

    private String businessNumber;

    @Enumerated(EnumType.STRING)
    private AuthStatus authStatus;

    protected Member() {
    }

    @Builder
    public Member(Long id, String email, String password, String phone, MemberType memberType, String name, String businessNumber, AuthStatus authStatus) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.memberType = memberType;
        this.name = name;
        this.businessNumber = businessNumber;
        this.authStatus = authStatus;
    }

    public enum MemberType {
        NORMAL, COMPANY, SHELTER
    }

    public enum AuthStatus{
        Y, N
    }
}
