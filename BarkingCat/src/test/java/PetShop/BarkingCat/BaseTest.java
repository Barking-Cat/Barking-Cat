package PetShop.BarkingCat;

import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.common.config.JpaConfig;
import PetShop.BarkingCat.domain.member.model.NormalMember;
import PetShop.BarkingCat.domain.member.model.QNormalMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(JpaConfig.class)
public class BaseTest {

    @Autowired
    private JPAQueryFactory query;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void queryTest() {
        List<NormalMember> members = query.selectFrom(QNormalMember.normalMember)
                .fetch();
    }

    @Test
    public void repositoryTest() {
        List<Board> boards = boardRepository.findAllNotDeleted();
    }
}
