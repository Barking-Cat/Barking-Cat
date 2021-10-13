package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.domain.board.model.objects.Title;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyAdoptRequestResponse {
    private final Long id;
    private final Long boardId;
    private final Title boardTitle;
    private final LocalDateTime createDateTime;

    public MyAdoptRequestResponse(Long id, Long boardId, Title boardTitle, LocalDateTime createDateTime) {
        this.id = id;
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.createDateTime = createDateTime;
    }

    public String getBoardTitle() {
        return boardTitle.content();
    }
}
