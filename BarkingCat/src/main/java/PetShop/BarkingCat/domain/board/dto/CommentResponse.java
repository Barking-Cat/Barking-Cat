package PetShop.BarkingCat.domain.board.dto;

import lombok.Data;
import lombok.Getter;


@Getter
public class CommentResponse {

    private final String content;

    private final String name;

    public CommentResponse(String content, String name) {
        this.content = content;
        this.name = name;
    }
}
