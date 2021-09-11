package PetShop.BarkingCat.member_temp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class NormalMemberTemp extends MemberTemp {

    private String name;

    protected NormalMemberTemp() {
    }

    public NormalMemberTemp(String email, String password, String phone, String name) {
        super(email, password, phone);
        this.name = name;
    }
}
