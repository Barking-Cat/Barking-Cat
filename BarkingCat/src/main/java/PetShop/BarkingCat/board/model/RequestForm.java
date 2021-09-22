package PetShop.BarkingCat.board.model;

import PetShop.BarkingCat.base.model.constants.Earning;
import PetShop.BarkingCat.base.model.constants.Region;
import PetShop.BarkingCat.base.model.Residence;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class RequestForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_form_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private Long writer_id;

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

    @Builder
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
