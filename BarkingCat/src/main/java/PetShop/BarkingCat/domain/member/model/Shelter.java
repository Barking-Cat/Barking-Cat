package PetShop.BarkingCat.domain.member.model;

import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
public class Shelter extends Member {

    private String shelterNumber;

    private String businessName;

    public Shelter() {

    }

    @Builder
    public Shelter(Long id, String email, String password, String phone, String shelterNumber, String businessName) {
        super(id, email, password, phone);
        this.shelterNumber = shelterNumber;
        this.businessName = businessName;
    }
}
