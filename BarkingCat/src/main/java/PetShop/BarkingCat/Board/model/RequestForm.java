package PetShop.BarkingCat.Board.model;


import PetShop.BarkingCat.base.Earn;
import PetShop.BarkingCat.base.Region;
import PetShop.BarkingCat.base.ResidenceType;

import javax.persistence.*;

@Entity
public class RequestForm {

    @Id @GeneratedValue
    @Column(name = "request_form_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Earn earning;

    @Embedded
    private ResidenceType residenceType;

    private int roommateNumber;

    private boolean petExist;

    private String adoptReason;

    private Region region;

}
