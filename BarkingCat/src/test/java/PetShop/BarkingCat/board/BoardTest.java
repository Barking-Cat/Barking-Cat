package PetShop.BarkingCat.board;

import PetShop.BarkingCat.base.infra.Title;
import PetShop.BarkingCat.board.model.Board;
import PetShop.BarkingCat.board.model.Category;
import PetShop.BarkingCat.board.repository.BoardRepository;
import PetShop.BarkingCat.config.JpaConfig;
import PetShop.BarkingCat.fixtures.BoardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(JpaConfig.class)
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("제목을 입력하지 않으면 Board 생성이 실패한다")
    public void createBoardFail() {
        assertThatThrownBy(() -> {
            BoardFixture.aBoard()
                    .title(new Title(""))
                    .build();
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void test() {
        Category category = new Category(null, "TestCategory");
        em.persist(category);

        Board board = BoardFixture.aBoard()
                .id(null)
                .category(category)
                .build();

        em.persist(board);

        em.flush();
        em.clear();

        List<Board> boards = boardRepository.findByTitle_TitleContains("TES");

        assertThat(boards.size()).isEqualTo(1);
    }
}
