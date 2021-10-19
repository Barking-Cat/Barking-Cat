package PetShop.BarkingCat.domain.board.model;

import PetShop.BarkingCat.common.base.model.Base;
import PetShop.BarkingCat.domain.board.model.objects.TagContent;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class Tag extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private TagContent tagContent;

    protected Tag() {
    }

    @Builder
    public Tag(Long id, Board board, TagContent tagContent) {
        this.id = id;
        this.board = board;
        this.tagContent = tagContent;
    }

    public String content() {
        return tagContent.content();
    }
}
