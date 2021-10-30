package PetShop.BarkingCat.domain.board.model;

import PetShop.BarkingCat.common.base.model.Base;
import lombok.Builder;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Liked extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private Long memberId;

    public Liked() {
    }

    @Builder
    public Liked(Long id, Board board, Long memberId) {
        this.id = id;
        this.board = board;
        this.memberId = memberId;
    }

    public void delete(Long memberId) {
        checkMember(memberId);
        super.delete();
    }

    private void checkMember(Long memberId) {
        if (memberIdIsNotEqual(memberId)) {
           throw new RuntimeException("멤버가 일치하지 않습니다.");
        }
    }

    private boolean memberIdIsNotEqual(Long memberId) {
        return !Objects.equals(memberId, this.memberId);
    }
}
