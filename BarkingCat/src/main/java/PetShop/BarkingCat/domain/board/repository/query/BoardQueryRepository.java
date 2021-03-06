package PetShop.BarkingCat.domain.board.repository.query;

import PetShop.BarkingCat.common.base.model.constants.AnimalType;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.common.base.model.constants.Sex;
import PetShop.BarkingCat.domain.board.dto.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static PetShop.BarkingCat.domain.board.model.QBoard.board;
import static PetShop.BarkingCat.domain.board.model.QLiked.liked;
import static PetShop.BarkingCat.domain.board.model.QTag.tag;
import static PetShop.BarkingCat.domain.member.model.QMember.member;

@Repository
public class BoardQueryRepository {

    private final JPAQueryFactory query;

    public BoardQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public Page<BoardResponse> findByCondition(FindBoardCondition findBoardCondition, Pageable pageable) {
        List<BoardResponse> responses = findBoardsWithPagination(findBoardCondition, pageable);

        Map<Long, List<TagResponse>> tagMap = findTagMap(toBoardId(responses));

        responses.forEach(boardResponse -> boardResponse.setTagContents(tagMap.get(boardResponse.getBoardId())));

        return new PageImpl<>(responses, pageable, responses.size());
    }

    private List<BoardResponse> findBoardsWithPagination(FindBoardCondition findBoardCondition, Pageable pageable) {
        return query.select(Projections.constructor(BoardResponse.class,
                                board.id,
                                board.category.id,
                                board.memberId,
                                board.title,
                                board.region,
                                board.animalType,
                                board.hits,
                                liked.count(),
                                board.createdDateTime,
                                member.name
                        )
                )
                .from(board)
                .join(member)
                .on(board.memberId.eq(member.id))
                .leftJoin(liked)
                .on(liked.board.id.eq(board.id), liked.deletedDateTime.isNull())
                .leftJoin(tag)
                .on(tag.board.id.eq(board.id))
                .where(
                        isNotDeleted(),
                        titleContains(findBoardCondition.getTitle()),
                        animalTypeEq(findBoardCondition.getAnimalType()),
                        regionEq(findBoardCondition.getRegion()),
                        ageLoe(findBoardCondition.getAge()),
                        sexEq(findBoardCondition.getSex()),
                        priceLoe(findBoardCondition.getPrice()),
                        tagContains(findBoardCondition.getTag())
                )
                .groupBy(board.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public BoardDetailResponse findDetail(Long boardId) {
        BoardDetailResponse board = findBoard(boardId);

        Map<Long, List<TagResponse>> tagMap = findTagMap(Collections.singletonList(board.getBoardId()));

        board.setTagContents(tagMap.get(board.getBoardId()));

        return board;
    }

    private Map<Long, List<TagResponse>> findTagMap(List<Long> boardIds) {
        List<TagResponse> tagResponses = query.select(Projections.constructor(TagResponse.class,
                                tag.id,
                                tag.board.id,
                                tag.tagContent.tag
                        )
                )
                .from(tag)
                .where(tag.board.id.in(boardIds))
                .fetch();

        return tagResponses.stream()
                .collect(Collectors.groupingBy(TagResponse::getBoardId));
    }

    private BoardDetailResponse findBoard(Long boardId) {
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
                                board.hits,
                                liked.count(),
                                board.createdDateTime,
                                member.email,
                                member.phone,
                                member.name
                        )
                )
                .from(board)
                .join(member)
                .on(board.memberId.eq(member.id))
                .leftJoin(liked)
                .on(liked.board.id.eq(board.id))
                .on(liked.deletedDateTime.isNull())
                .join(tag)
                .on(boardIdEq())
                .where(
                        isNotDeleted(),
                        boardIdEq(boardId)
                )
                .fetchFirst();
    }

    private List<Long> toBoardId(List<BoardResponse> result) {
        return result.stream()
                .map(BoardResponse::getBoardId)
                .collect(Collectors.toList());
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

    public Page<MyPageBoardResponse> findMyPageBoardList(Long memberId, Pageable pageable) {
        List<MyPageBoardResponse> responses = query.select(Projections.constructor(MyPageBoardResponse.class,
                                board.title,
                                board.createdDateTime
                        )
                )
                .from(board)
                .where(
                        writerEq(memberId),
                        isNotDeleted()
                )
                .orderBy(board.createdDateTime.desc())
                .fetch();

        return new PageImpl<>(responses, pageable, responses.size());
    }

    private BooleanExpression boardIdEq() {
        return tag.board.id.eq(board.id);
    }

    private BooleanExpression titleContains(String title) {
        if (title == null || title.isBlank()) {
            return null;
        }

        return board.title.title.contains(title);
    }

    private BooleanExpression tagContains(String tagContent) {
        if (tagContent == null || tagContent.isBlank()) {
            return null;
        }

        return tag.tagContent.tag.contains(tagContent);
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
