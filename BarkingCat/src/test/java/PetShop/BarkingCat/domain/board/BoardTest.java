package PetShop.BarkingCat.domain.board;

import PetShop.BarkingCat.domain.board.model.objects.Title;
import PetShop.BarkingCat.domain.board.service.BoardRegulation;
import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.fixtures.BoardFixture;
import PetShop.BarkingCat.fixtures.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    @DisplayName("제목의 길이가 max를 넘어가면 Board 생성이 실패한다")
    public void createBoardFailByTitleOverTheMax() {
        //given

        //when
        String given = "t".repeat(151);

        //then
        assertThatThrownBy(() -> {
            BoardFixture.aBoard()
                    .title(new Title(given))
                    .build();
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("월별 등록가능한 게시글 갯수를 초과하면 예외를 발생시킨다")
    public void createBoardFailByOverTheMaxCount() {
        //given
        Member member = MemberFixture.aMember()
                .build();

        //when

        //then
        assertThatThrownBy(() -> {
            member.checkBoardLimit(4);
        }).isInstanceOf(RuntimeException.class);
    }
}
