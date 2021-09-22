package PetShop.BarkingCat.member_temp.model;

import lombok.Builder;

import javax.persistence.Entity;

@Entity
public class ShelterTemp extends MemberTemp {

    private String shelterNumber;

    private String businessName;

    public ShelterTemp() {

    }

    @Builder
    public ShelterTemp(Long id, String email, String password, String phone, String shelterNumber, String businessName) {
        super(id, email, password, phone);
        this.shelterNumber = shelterNumber;
        this.businessName = businessName;
    }
}
