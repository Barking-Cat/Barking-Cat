package PetShop.BarkingCat.domain.board.model;

import PetShop.BarkingCat.common.base.model.Base;
import PetShop.BarkingCat.common.base.model.Residence;
import PetShop.BarkingCat.common.base.model.constants.Earning;
import PetShop.BarkingCat.common.base.model.constants.Region;
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

    private Residence residence;

    private Integer roommateNumber;

    private Integer petCount;

    private String adoptReason;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Enumerated(EnumType.STRING)
    private Status status = Status.RECEIVED;

    public AdoptRequest() {
    }

    @Builder
    public AdoptRequest(Long id, Board board, Long writerId, Earning earning, Residence residence, Integer roommateNumber, Integer petCount, String adoptReason, Region region) {
        this.id = id;
        this.board = board;
        this.writerId = writerId;
        this.earning = earning;
        this.residence = residence;
        this.roommateNumber = roommateNumber;
        this.petCount = petCount;
        this.adoptReason = adoptReason;
        this.region = region;
    }

    public AdoptRequest mapBoard(Board board) {
        this.board = board;
        return this;
    }

    public AdoptRequest mapWriter(Long memberId) {
        this.writerId = memberId;
        return this;
    }

    public void progress() {
        if (statusIsNotReceived()) {
            throw new RuntimeException("접수중인 요청만 진행할 수 있습니다");
        }

        this.status = Status.PROGRESS;
    }

    private boolean statusIsNotReceived() {
        return this.status != Status.RECEIVED;
    }

    public void cancel() {
        this.status = Status.CANCELED;
    }

    public boolean writerIsNotEqual(Long writerId) {
        return !this.writerId.equals(writerId);
    }

    public boolean boardWriterIsNotEqual(Long writerId) {
        return this.board.writerIsNotEqual(writerId);
    }

    public Long id() {
        return id;
    }

    public enum Status {
        RECEIVED, PROGRESS, CANCELED, TERMINATED
    }
}
