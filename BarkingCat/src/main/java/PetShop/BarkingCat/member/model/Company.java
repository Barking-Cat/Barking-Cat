package PetShop.BarkingCat.member.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("C")
public class Company extends Member {

    private String businessNumber;

    private String businessName;

    protected Company() {
    }

    public Company(String businessNumber, String businessName) {
        this.businessNumber = businessNumber;
        this.businessName = businessName;
    }
}
