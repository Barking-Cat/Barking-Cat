package PetShop.BarkingCat.member.model;

import PetShop.BarkingCat.base.model.Base;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Member extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String phone;

    public Member() {
    }

    public Member(Long id, String email, String password, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
