package PetShop.BarkingCat.member.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("C")
public class Company extends Member {

    private String businessNumber;

    private String businessName;

    protected Company() {
    }

    public Company(String email, String password, String phone, String businessNumber, String businessName) {
        super(email, password, phone);
        this.businessNumber = businessNumber;
        this.businessName = businessName;
    }
}
