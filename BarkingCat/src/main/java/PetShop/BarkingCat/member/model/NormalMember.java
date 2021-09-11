package PetShop.BarkingCat.member.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class NormalMember extends Member {
    private String name;

    protected NormalMember() {
    }

    public NormalMember(String name) {
        this.name = name;
    }
}
