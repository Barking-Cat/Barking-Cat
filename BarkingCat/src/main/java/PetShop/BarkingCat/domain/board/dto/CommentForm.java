package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.domain.board.model.Comment;
import lombok.Data;

@Data
public class CommentForm {

    private String content;

    public Comment entity(){
        return Comment.builder()
                .content(content)
                .build();
    }





}
