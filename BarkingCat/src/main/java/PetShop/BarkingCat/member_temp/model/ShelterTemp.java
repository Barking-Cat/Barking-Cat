package PetShop.BarkingCat.member_temp.model;

import javax.persistence.Entity;

@Entity
public class ShelterTemp extends MemberTemp {

    private String shelterNumber;

    private String businessName;

    public ShelterTemp() {

    }

    public ShelterTemp(String email, String password, String phone, String shelterNumber, String businessName) {
        super(email, password, phone);
        this.shelterNumber = shelterNumber;
        this.businessName = businessName;
    }
}
