package PetShop.BarkingCat.domain.member.repository.query;

import PetShop.BarkingCat.domain.member.dto.MyPageMemberResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static PetShop.BarkingCat.domain.member.model.QMember.member;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory query;

    public MemberQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public MyPageMemberResponse findMyPageById(Long memberId){

        return query.select(Projections.constructor(MyPageMemberResponse.class,
                member.name,
                member.email,
                member.phone))
                .from(member)
                .where(
                        memberEq(memberId),
                        isNotDeleted()
                )
                .fetchFirst();
    }

    private BooleanExpression isNotDeleted() {
        return member.deletedDateTime.isNull();
    }

    private BooleanExpression memberEq(Long memberId) {
        return member.id.eq(memberId);
    }
}
