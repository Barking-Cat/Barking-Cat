package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.model.Board;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static PetShop.BarkingCat.domain.board.model.QBoard.board;

public class BoardRepositoryQuerydslImpl implements BoardRepositoryQuerydsl {

    private final JPAQueryFactory query;

    public BoardRepositoryQuerydslImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public List<Board> findAllNotDeleted() {
        return query.selectFrom(board)
                .where(isNotDeleted())
                .fetch();
    }

    private BooleanExpression isNotDeleted() {
        return board.deletedDateTime.isNull();
    }
}
