package PetShop.BarkingCat.domain.board.repository.query;

import PetShop.BarkingCat.common.base.model.constants.AnimalType;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.common.base.model.constants.Sex;
import PetShop.BarkingCat.domain.board.dto.BoardDetailResponse;
import PetShop.BarkingCat.domain.board.dto.BoardResponse;
import PetShop.BarkingCat.domain.board.dto.FindBoardCondition;
import PetShop.BarkingCat.domain.board.model.Board;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

import static PetShop.BarkingCat.domain.board.model.QBoard.board;
import static PetShop.BarkingCat.domain.board.model.QLikes.likes;
import static PetShop.BarkingCat.domain.member.model.QMember.member;

@Repository
public class BoardQueryRepository {

    private final JPAQueryFactory query;

    public BoardQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public List<Board> findAllNotDeleted() {
        return query.selectFrom(board)
                .where(isNotDeleted())
                .fetch();
    }

    public Page<BoardResponse> findByCondition(FindBoardCondition findBoardCondition, Pageable pageable) {
        List<BoardResponse> responses = query.select(Projections.constructor(BoardResponse.class,
                                board.id,
                                board.category.id,
                                board.memberId,
                                board.title,
                                board.region,
                                board.animalType,
                                board.tags,
                                board.hits,
                                likes.count(),
                                board.createdDateTime,
                                member.name
                        )
                )
                .from(board)
                .join(member)
                .on(board.memberId.eq(member.id))
                .leftJoin(likes)
                .on(likes.board.id.eq(board.id), likes.deletedDateTime.isNull())
                .where(
                        isNotDeleted(),
                        animalTypeEq(findBoardCondition.getAnimalType()),
                        regionEq(findBoardCondition.getRegion()),
                        ageLoe(findBoardCondition.getAge()),
                        sexEq(findBoardCondition.getSex()),
                        priceLoe(findBoardCondition.getPrice())
                )
                .groupBy(board.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(responses, pageable, responses.size());
    }

    public BoardDetailResponse findDetail(Long boardId) {
        return query.select(Projections.constructor(BoardDetailResponse.class,
                                board.id,
                                board.category.id,
                                board.memberId,
                                board.title,
                                board.content,
                                board.region,
                                board.animalType,
                                board.sex,
                                board.age,
                                board.price,
                                board.dueDate,
                                board.tags,
                                board.hits,
                                likes.count(),
                                board.createdDateTime,
                                member.email,
                                member.phone,
                                member.name
                        )
                )
                .from(board)
                .join(member)
                .on(board.memberId.eq(member.id))
                .leftJoin(likes)
                .on(likes.board.id.eq(board.id))
                .on(likes.deletedDateTime.isNull())
                .where(
                        isNotDeleted(),
                        boardIdEq(boardId)
                )
                .fetchFirst();
    }

    public int countMyMonthlyBoard(Long memberId, Month month) {
        return query.selectOne()
                .from(board)
                .where(
                        writerEq(memberId),
                        createdMonthEq(month)
                )
                .fetch()
                .size();
    }

    private BooleanExpression createdMonthEq(Month month) {
        return board.createdDateTime.month()
                .eq(month.getValue());
    }

    private BooleanExpression writerEq(Long memberId) {
        return board.memberId.eq(memberId);
    }

    private BooleanExpression boardIdEq(Long boardId) {
        if (boardId == null) {
            return null;
        }

        return board.id.eq(boardId);
    }

    private BooleanExpression animalTypeEq(AnimalType animalType) {
        if (animalType == null) {
            return null;
        }

        return board.animalType.eq(animalType);
    }

    private BooleanExpression regionEq(Region region) {
        if (region == null) {
            return null;
        }

        return board.region.eq(region);
    }

    private BooleanExpression ageLoe(Integer age) {
        if (age == null) {
            return null;
        }

        return board.age.loe(age);
    }

    private BooleanExpression sexEq(Sex sex) {
        if (sex == null) {
            return null;
        }

        return board.sex.eq(sex);
    }

    private BooleanExpression priceLoe(BigDecimal price) {
        if (price == null) {
            return null;
        }

        return board.price.amount.loe(price);
    }

    private BooleanExpression isNotDeleted() {
        return board.deletedDateTime.isNull();
    }
}
