package PetShop.BarkingCat.base.model;


import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Residence {

    @Enumerated(EnumType.STRING)
    private ResidenceType residenceType;

    private int area;

    public Residence() {

    }

    public Residence(ResidenceType residenceType, int area) {
        this.residenceType = residenceType;
        this.area = area;
    }

    enum ResidenceType {
        LEASE, MONTHLY_RENT, OWNER
    }
}
