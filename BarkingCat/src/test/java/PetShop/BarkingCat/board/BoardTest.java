package PetShop.BarkingCat.board;

import PetShop.BarkingCat.board.model.Title;
import PetShop.BarkingCat.fixtures.BoardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    @Test
    @DisplayName("제목을 입력하지 않으면 Board 생성이 실패한다")
    public void createBoardFail() {
        assertThatThrownBy(() -> {
            BoardFixture.aBoard()
                    .title(new Title(""))
                    .build();
        }).isInstanceOf(RuntimeException.class);
    }
}
