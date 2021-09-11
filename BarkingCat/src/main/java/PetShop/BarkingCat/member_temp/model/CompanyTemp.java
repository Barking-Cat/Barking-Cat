package PetShop.BarkingCat.member_temp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class CompanyTemp extends MemberTemp {

    private String businessNumber;

    private String businessName;

    protected CompanyTemp() {
    }

    public CompanyTemp(String email, String password, String phone, String businessNumber, String businessName) {
        super(email, password, phone);
        this.businessNumber = businessNumber;
        this.businessName = businessName;
    }
}
