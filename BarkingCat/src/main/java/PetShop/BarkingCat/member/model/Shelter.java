package PetShop.BarkingCat.member.model;

import javax.persistence.Entity;

@Entity
public class Shelter extends Member {

    private String shelterNumber;

    private String businessName;

    public Shelter() {

    }

    public Shelter(String email, String password, String phone, String shelterNumber, String businessName) {
        super(email, password, phone);
        this.shelterNumber = shelterNumber;
        this.businessName = businessName;
    }
}
