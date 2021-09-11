package PetShop.BarkingCat.member.model;

import javax.persistence.Entity;

@Entity
public class Shelter extends Member {

    private String shelterNumber;

    private String businessName;

    public Shelter(String shelterNumber, String businessName) {
        this.shelterNumber = shelterNumber;
        this.businessName = businessName;
    }
}
