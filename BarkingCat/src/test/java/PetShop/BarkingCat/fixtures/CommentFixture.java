package PetShop.BarkingCat.fixtures;


import PetShop.BarkingCat.domain.board.model.Comment;

public class CommentFixture {

    public static Comment.CommentBuilder aComment(){
        return Comment.builder()
                .memberId(1L)
                .board(BoardFixture.aBoard().build())
                .content("test content");
    }
}
