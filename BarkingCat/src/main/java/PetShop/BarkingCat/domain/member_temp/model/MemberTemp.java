package PetShop.BarkingCat.domain.member_temp.model;

import PetShop.BarkingCat.common.base.model.Base;
import PetShop.BarkingCat.domain.member.model.NormalMember;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MemberTemp extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String phone;

    public MemberTemp() {
    }

    public MemberTemp(Long id, String email, String password, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public NormalMember.NormalMemberBuilder buildNormalMember() {
        return NormalMember.builder()
                .email(email)
                .password(password)
                .phone(phone);
    }
}
