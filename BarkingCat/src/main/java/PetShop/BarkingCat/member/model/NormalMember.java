package PetShop.BarkingCat.member.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class NormalMember extends Member {

    private String name;

    protected NormalMember() {
    }

    public NormalMember(String email, String password, String phone, String name) {
        super(email, password, phone);
        this.name = name;
    }
}
