package PetShop.BarkingCat.domain.board.repository.query;

import PetShop.BarkingCat.domain.board.dto.AdoptRequestDetailResponse;
import PetShop.BarkingCat.domain.board.dto.AdoptRequestResponse;
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

    public Page<AdoptRequestResponse> findByBoardId(Long boardId, Pageable pageable) {
        List<AdoptRequestResponse> responses = query.select(Projections.constructor(AdoptRequestResponse.class,
                        adoptRequest.id,
                        adoptRequest.createdDateTime,
                        member.name
                ))
                .from(adoptRequest)
                .join(member)
                .on(adoptRequest.writerId.eq(member.id))
                .where(boardIdEq(boardId))
                .groupBy(adoptRequest.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(responses, pageable, responses.size());
    }

    public AdoptRequestDetailResponse findDetail(Long adoptRequestId) {
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
                .where(adoptRequest.id.eq(adoptRequestId))
                .fetchFirst();
    }

    private BooleanExpression boardIdEq(Long boardId) {
        return adoptRequest.board.id.eq(boardId);
    }
}
