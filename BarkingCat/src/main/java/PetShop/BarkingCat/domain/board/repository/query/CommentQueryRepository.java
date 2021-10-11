package PetShop.BarkingCat.domain.board.repository.query;

import PetShop.BarkingCat.domain.board.dto.CommentResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static PetShop.BarkingCat.domain.board.model.QComment.comment;
import static PetShop.BarkingCat.domain.member.model.QMember.member;

@Repository
public class CommentQueryRepository {

    private final JPAQueryFactory query;

    public CommentQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public List<CommentResponse> findAllByBoardId(Long boardId) {

        return query.select(Projections.constructor(CommentResponse.class,
                comment.content,
                member.name,
                comment.createdDateTime))
                .from(comment)
                .join(member)
                .on(comment.memberId.eq(member.id))
                .where(
                        boardIdEq(boardId),
                        isNotDeleted()
                )
                .orderBy(comment.createdDateTime.desc())
                .fetch();
    }

    private BooleanExpression isNotDeleted() {
        return comment.deletedDateTime.isNull();
    }

    private BooleanExpression boardIdEq(Long boardId) {
        return comment.board.id.eq(boardId);
    }
}
