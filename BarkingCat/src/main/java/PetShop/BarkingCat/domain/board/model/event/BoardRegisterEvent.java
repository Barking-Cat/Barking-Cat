package PetShop.BarkingCat.domain.board.model.event;

import PetShop.BarkingCat.domain.board.model.Board;

import java.time.LocalDateTime;

public class BoardRegisterEvent {
    private final Board board;

    public BoardRegisterEvent(Board boardId) {
        this.board = boardId;
    }

    public Long boardId() {
        return board.id();
    }

    public Long writerId() {
        return board.writerId();
    }

    public LocalDateTime createDateTime() {
        return board.createDateTime();
    }
}
