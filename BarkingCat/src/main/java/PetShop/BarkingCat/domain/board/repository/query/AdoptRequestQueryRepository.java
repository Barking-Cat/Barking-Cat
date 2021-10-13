package PetShop.BarkingCat.domain.board.repository.query;

import PetShop.BarkingCat.domain.board.dto.AdoptRequestDetailResponse;
import PetShop.BarkingCat.domain.board.dto.AdoptRequestResponse;
import PetShop.BarkingCat.domain.board.dto.MyAdoptRequestResponse;
import PetShop.BarkingCat.domain.board.dto.MyAdoptRequestResponseDetail;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static PetShop.BarkingCat.domain.board.model.QAdoptRequest.adoptRequest;
import static PetShop.BarkingCat.domain.member.model.QMember.member;

@Repository
public class AdoptRequestQueryRepository {
    private final JPAQueryFactory query;

    public AdoptRequestQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public Page<MyAdoptRequestResponse> findByMemberId(Long memberId, Pageable pageable) {
        List<MyAdoptRequestResponse> responses = query.select(Projections.constructor(MyAdoptRequestResponse.class,
                        adoptRequest.id,
                        adoptRequest.board.id,
                        adoptRequest.board.title,
                        adoptRequest.createdDateTime
                ))
                .from(adoptRequest)
                .where(
                        memberIdEq(memberId)
                )
                .groupBy(adoptRequest.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(responses, pageable, responses.size());
    }

    public MyAdoptRequestResponseDetail findDetailByMemberId(Long memberId, Long adoptRequestId) {
        return query.select(Projections.constructor(MyAdoptRequestResponseDetail.class,
                        adoptRequest.id,
                        adoptRequest.board.id,
                        adoptRequest.earning,
                        adoptRequest.residence.residenceType,
                        adoptRequest.residence.area,
                        adoptRequest.roommateNumber,
                        adoptRequest.petCount,
                        adoptRequest.adoptReason,
                        adoptRequest.region,
                        adoptRequest.createdDateTime,
                        member.name,
                        member.email,
                        member.phone
                ))
                .from(adoptRequest)
                .join(member)
                .on(adoptRequest.writerId.eq(member.id))
                .where(
                        adoptRequestIdEq(adoptRequestId),
                        memberIdEq(memberId)
                )
                .fetchFirst();
    }

    public Page<AdoptRequestResponse> findByBoardId(Long boardWriterId, Long boardId, Pageable pageable) {
        List<AdoptRequestResponse> responses = query.select(Projections.constructor(AdoptRequestResponse.class,
                        adoptRequest.id,
                        adoptRequest.createdDateTime,
                        member.name
                ))
                .from(adoptRequest)
                .join(member)
                .on(adoptRequest.writerId.eq(member.id))
                .where(
                        boardIdEq(boardId),
                        boardWriterIdEq(boardWriterId)
                )
                .groupBy(adoptRequest.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(responses, pageable, responses.size());
    }

    public AdoptRequestDetailResponse findDetail(Long boardWriterId, Long boardId, Long adoptRequestId) {
        return query.select(Projections.constructor(AdoptRequestDetailResponse.class,
                        adoptRequest.id,
                        adoptRequest.board.id,
                        adoptRequest.earning,
                        adoptRequest.residence.residenceType,
                        adoptRequest.residence.area,
                        adoptRequest.roommateNumber,
                        adoptRequest.petCount,
                        adoptRequest.adoptReason,
                        adoptRequest.region,
                        adoptRequest.createdDateTime,
                        member.name,
                        member.email,
                        member.phone
                ))
                .from(adoptRequest)
                .join(member)
                .on(adoptRequest.writerId.eq(member.id))
                .where(
                        boardWriterIdEq(boardWriterId),
                        boardIdEq(boardId),
                        adoptRequestIdEq(adoptRequestId)
                )
                .fetchFirst();
    }

    private BooleanExpression boardWriterIdEq(Long memberId) {
        return adoptRequest.board.memberId.eq(memberId);
    }

    private BooleanExpression adoptRequestIdEq(Long adoptRequestId) {
        return adoptRequest.id.eq(adoptRequestId);
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return adoptRequest.writerId.eq(memberId);
    }

    private BooleanExpression boardIdEq(Long boardId) {
        return adoptRequest.board.id.eq(boardId);
    }
}
