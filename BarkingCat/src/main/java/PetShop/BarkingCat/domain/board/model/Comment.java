package PetShop.BarkingCat.domain.board.model;


import PetShop.BarkingCat.common.base.model.Base;
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
        validate(content);
        this.board = board;
        this.memberId = memberId;
        this.content = content;
    }

    private void validate(String content) {
        if (isEmptyContent(content)) {
            throw new RuntimeException("내용을 입력해주세요.");
        }

        if (contentLengthIsOverTheMax(content)) {
            throw new RuntimeException("500자 이내로 입력해주세요.");
        }
    }

    private boolean contentLengthIsOverTheMax(String content) {
        return content.length() > 500;
    }

    private boolean isEmptyContent(String content) {
        return content.length() == 0;
    }

    public Comment mapWriter(Long memberId) {
        this.memberId = memberId;
        return this;
    }

    public Comment mapBoard(Board board) {
        this.board = board;
        return this;
    }

    public boolean writerIsNotEqual(Long memberId) {
        return !this.memberId.equals(memberId);
    }
}
