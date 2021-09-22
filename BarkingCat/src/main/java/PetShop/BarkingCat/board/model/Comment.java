package PetShop.BarkingCat.board.model;


import PetShop.BarkingCat.base.model.Base;
import lombok.Builder;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Comment extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private Long memberId;

    private String content;

    protected Comment() {
    }

    @Builder
    public Comment(Board board, Long memberId, String content) {
        this.board = board;
        this.memberId = memberId;
        this.content = content;
    }
}
