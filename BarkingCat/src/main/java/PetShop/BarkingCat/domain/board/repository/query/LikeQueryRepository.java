package PetShop.BarkingCat.domain.board.repository.query;

import PetShop.BarkingCat.domain.board.model.Liked;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static PetShop.BarkingCat.domain.board.model.QLiked.liked;


@Repository
public class LikeQueryRepository {

    private final JPAQueryFactory query;

    public LikeQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public boolean existsByBoardAndMember(Long boardId, Long memberId) {
        return query.selectOne()
                .from(liked)
                .where(
                        boardIdEq(boardId),
                        memberIdEq(memberId),
                        isNotDeleted()
                )
                .fetchFirst() != null;
    }

    public Liked findRecentLike(Long boardId, Long memberId) {
        return query.selectFrom(liked)
                .where(
                        boardIdEq(boardId),
                        memberIdEq(memberId),
                        isNotDeleted()
                )
                .orderBy(liked.id.desc())
                .fetchFirst();
    }

    private BooleanExpression isNotDeleted() {
        return liked.deletedDateTime.isNull();
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return liked.memberId.eq(memberId);
    }

    private BooleanExpression boardIdEq(Long boardId) {
        return liked.board.id.eq(boardId);
    }
}
