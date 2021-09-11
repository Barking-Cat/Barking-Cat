package PetShop.BarkingCat.base;


import javax.persistence.Embeddable;

@Embeddable
public class ResidenceType {

    private String residenceType;
    private int area;

    public ResidenceType(String residenceType, int area) {
        this.residenceType = residenceType;
        this.area = area;
    }
}
