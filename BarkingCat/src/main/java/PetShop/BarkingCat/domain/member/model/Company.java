package PetShop.BarkingCat.domain.member.model;

import lombok.Builder;

import javax.persistence.*;

@Entity
@DiscriminatorValue("C")
public class Company extends Member {

    private String businessNumber;

    private String businessName;

    protected Company() {
    }

    @Builder
    public Company(Long id, String email, String password, String phone, String businessNumber, String businessName) {
        super(id, email, password, phone);
        this.businessNumber = businessNumber;
        this.businessName = businessName;
    }
}
