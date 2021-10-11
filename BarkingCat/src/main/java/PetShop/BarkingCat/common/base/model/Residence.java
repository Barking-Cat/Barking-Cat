package PetShop.BarkingCat.common.base.model;


import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Residence {

    @Enumerated(EnumType.STRING)
    private ResidenceType residenceType;

    private Integer area;

    public Residence() {

    }

    public Residence(ResidenceType residenceType, Integer area) {
        this.residenceType = residenceType;
        this.area = area;
    }

    public enum ResidenceType {
        LEASE, MONTHLY_RENT, OWNER
    }
}
