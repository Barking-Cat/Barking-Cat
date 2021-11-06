package PetShop.BarkingCat.domain.billing.repository.query;

import PetShop.BarkingCat.domain.billing.dto.BillingLogCondition;
import PetShop.BarkingCat.domain.billing.dto.BillingLogResponse;
import PetShop.BarkingCat.domain.billing.dto.BillingResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;

import static PetShop.BarkingCat.domain.billing.model.QBilling.billing;
import static PetShop.BarkingCat.domain.billing.model.QBillingLog.billingLog;
import static PetShop.BarkingCat.domain.board.model.QBoard.board;
import static PetShop.BarkingCat.domain.member.model.QMember.member;

@Repository
public class BillingQueryRepository {

    private final JPAQueryFactory query;

    public BillingQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public Page<BillingResponse> findBillingOfMember(Long memberId, Pageable pageable) {
        List<BillingResponse> responses = query.select(Projections.constructor(BillingResponse.class,
                                billing.id,
                                billing.memberId,
                                member.name,
                                billing.year,
                                billing.month,
                                billing.feeAmount
                        )
                )
                .from(billing)
                .join(member)
                .on(billing.memberId.eq(member.id))
                .where(
                        memberIdEq(memberId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(responses, pageable, responses.size());
    }

    public Page<BillingLogResponse> findBillingLogOfMember(Long memberId, BillingLogCondition billingLogCondition, Pageable pageable) {
        List<BillingLogResponse> responses = query.select(Projections.constructor(BillingLogResponse.class,
                                billingLog.id,
                                board.id,
                                board.title,
                                billingLog.fee
                        )
                )
                .from(billingLog)
                .join(billing)
                .on(billingLog.billing.eq(billing))
                .join(member)
                .on(billing.memberId.eq(member.id))
                .join(board)
                .on(billingLog.boardId.eq(board.id))
                .where(
                        memberIdEq(memberId),
                        yearEq(billingLogCondition.getYear()),
                        monthEq(billingLogCondition.getMonth())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(responses, pageable, responses.size());
    }

    private BooleanExpression yearEq(int year) {
        return billing.year.eq(year);
    }

    private BooleanExpression monthEq(Month month) {
        return billing.month.eq(month);
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return member.id.eq(memberId);
    }
}
