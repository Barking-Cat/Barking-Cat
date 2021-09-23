package PetShop.BarkingCat.domain.board.model;

import PetShop.BarkingCat.common.base.model.Base;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class Attachment extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String url;

    public Attachment() {
    }

    @Builder
    public Attachment(Board board, String url) {
        this.board = board;
        this.url = url;
    }
}
