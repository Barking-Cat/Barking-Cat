package PetShop.BarkingCat.domain.board.repository;

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

import java.math.BigDecimal;
import java.util.List;

import static PetShop.BarkingCat.domain.board.model.QBoard.board;
import static PetShop.BarkingCat.domain.member.model.QMember.member;

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

    @Override
    public Page<BoardResponse> findByCondition(FindBoardCondition findBoardCondition, Pageable pageable) {
        List<BoardResponse> responses = query.select(Projections.constructor(BoardResponse.class,
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
                                board.tags
                        )
                )
                .from(board)
                .where(
                        isNotDeleted(),
                        animalTypeEq(findBoardCondition.getAnimalType()),
                        regionEq(findBoardCondition.getRegion()),
                        ageLoe(findBoardCondition.getAge()),
                        sexEq(findBoardCondition.getSex()),
                        priceLoe(findBoardCondition.getPrice())
                )
                .fetch();

        return new PageImpl<>(responses, pageable, responses.size());
    }

    @Override
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
                                member.email,
                                member.phone,
                                member.name
                        )
                )
                .from(board)
                .join(member)
                .on(board.memberId.eq(member.id))
                .where(
                        isNotDeleted(),
                        boardIdEq(boardId)
                )
                .fetchFirst();
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
