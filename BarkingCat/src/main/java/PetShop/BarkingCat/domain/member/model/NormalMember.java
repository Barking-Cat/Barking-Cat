package PetShop.BarkingCat.domain.member.model;

import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class NormalMember extends Member {

    private String name;

    protected NormalMember() {
    }

    @Builder
    public NormalMember(Long id, String email, String password, String phone, String name) {
        super(id, email, password, phone);
        this.name = name;
    }
}
