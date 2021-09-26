package PetShop.BarkingCat.domain.board;

import PetShop.BarkingCat.domain.board.model.objects.Title;
import PetShop.BarkingCat.fixtures.BoardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    @Test
    @DisplayName("제목을 입력하지 않으면 Board 생성이 실패한다")
    public void createBoardFail() {
        //given

        //when

        //then
        assertThatThrownBy(() -> {
            BoardFixture.aBoard()
                    .title(new Title(""))
                    .build();
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("제목을 입력하지 않으면 Board 생성이 실패한다")
    public void createBoardFailByOverTheMax() {
        //given

        //when
        String given = "t".repeat(151);

        //then
        assertThatThrownBy(() -> {
            BoardFixture.aBoard()
                    .title(new Title(given.toString()))
                    .build();
        }).isInstanceOf(RuntimeException.class);
    }
}
