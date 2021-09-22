package PetShop.BarkingCat.member_temp.model;

import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class CompanyTemp extends MemberTemp {

    private String businessNumber;

    private String businessName;

    protected CompanyTemp() {
    }

    @Builder
    public CompanyTemp(Long id, String email, String password, String phone, String businessNumber, String businessName) {
        super(id, email, password, phone);
        this.businessNumber = businessNumber;
        this.businessName = businessName;
    }
}
