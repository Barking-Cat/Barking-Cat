package PetShop.BarkingCat.domain.board;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static PetShop.BarkingCat.fixtures.CommentFixture.aComment;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommentTest {

    @Test
    @DisplayName("내용이 없으면 댓글 등록 실패")
    public void registerCommentFail(){

        assertThatThrownBy(()
                -> {
            aComment().content("").build();
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("내용이 500자 이상이면 등록 실패")
    public void registerCommentFailByContentLength(){

        String given = "t".repeat(501);

        assertThatThrownBy(() -> {
            aComment().content(given).build();
        }).isInstanceOf(RuntimeException.class);
    }

}
