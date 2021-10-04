package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.model.Likes;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static PetShop.BarkingCat.domain.board.model.QLikes.likes;

@Repository
public class LikeQueryRepository {

    private final JPAQueryFactory query;

    public LikeQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public boolean existsByBoardAndMember(Long boardId, Long memberId) {
        return query.selectOne()
                .from(likes)
                .where(
                        boardIdEq(boardId),
                        memberIdEq(memberId),
                        isDeleted()
                )
                .fetchFirst() != null;
    }

    private BooleanExpression isDeleted() {
        return likes.deletedDateTime.isNull();
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return likes.memberId.eq(memberId);
    }

    private BooleanExpression boardIdEq(Long boardId) {
        return likes.board.id.eq(boardId);
    }

    public Likes findRecentLike(Long boardId, Long memberId) {
        return query.selectFrom(likes)
                .where(
                        boardIdEq(boardId),
                        memberIdEq(memberId),
                        isDeleted()
                )
                .orderBy(likes.id.desc())
                .fetchFirst();
    }
}
