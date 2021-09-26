package PetShop.BarkingCat.domain.board.model;

import PetShop.BarkingCat.common.base.model.Base;
import PetShop.BarkingCat.common.base.model.constants.Earning;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.common.base.model.Residence;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class AdoptRequest extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adopt_request_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private Long writerId;

    @Enumerated(EnumType.STRING)
    private Earning earning;

    @Embedded
    private Residence residence;

    private int roommateNumber;

    private boolean petExist;

    private String adoptReason;

    private Region region;

    public AdoptRequest() {
    }

    @Builder
    public AdoptRequest(Long id, Board board, Long writerId, Earning earning, Residence residence, int roommateNumber, boolean petExist, String adoptReason, Region region) {
        this.id = id;
        this.board = board;
        this.writerId = writerId;
        this.earning = earning;
        this.residence = residence;
        this.roommateNumber = roommateNumber;
        this.petExist = petExist;
        this.adoptReason = adoptReason;
        this.region = region;
    }
}
