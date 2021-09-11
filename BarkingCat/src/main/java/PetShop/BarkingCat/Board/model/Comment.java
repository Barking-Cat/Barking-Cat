package PetShop.BarkingCat.Board.model;


import PetShop.BarkingCat.base.Base;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Comment extends Base {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Board board;

    private Long memberId;

    private String content;

    protected Comment() {
    }

    public Comment(Board board, Long memberId, String content) {
        this.board = board;
        this.memberId = memberId;
        this.content = content;
    }
}
