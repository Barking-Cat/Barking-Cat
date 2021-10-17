package PetShop.BarkingCat.domain.board.dto;

import lombok.Getter;

@Getter
public class TagResponse {
    private final Long tagId;
    private final Long boardId;
    private final String tag;

    public TagResponse(Long tagId, Long boardId, String tag) {
        this.tagId = tagId;
        this.boardId = boardId;
        this.tag = tag;
    }
}