package PetShop.BarkingCat.Board.model;

import PetShop.BarkingCat.base.model.enums.Earning;
import PetShop.BarkingCat.base.model.enums.Region;
import PetShop.BarkingCat.base.model.Residence;

import javax.persistence.*;

@Entity
public class RequestForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_form_id")
    private Long id;

    @ManyToOne
    private Board board;

    @Enumerated(EnumType.STRING)
    private Earning earning;

    @Embedded
    private Residence residence;

    private int roommateNumber;

    private boolean petExist;

    private String adoptReason;

    private Region region;

    public RequestForm() {
    }

    public RequestForm(Board board, Earning earning, Residence residence, int roommateNumber, boolean petExist, String adoptReason, Region region) {
        this.board = board;
        this.earning = earning;
        this.residence = residence;
        this.roommateNumber = roommateNumber;
        this.petExist = petExist;
        this.adoptReason = adoptReason;
        this.region = region;
    }
}
