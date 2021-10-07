package PetShop.BarkingCat.domain.member_temp.repository.query;

import PetShop.BarkingCat.domain.member_temp.model.MemberTemp;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static PetShop.BarkingCat.domain.member_temp.model.QMemberTemp.memberTemp;

@Repository
public class MemberTempQueryRepository {
    private final JPAQueryFactory query;

    public MemberTempQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public Optional<MemberTemp> findByIdNotDeleted(Long memberTempId) {
        return Optional.ofNullable(
                query.selectFrom(memberTemp)
                        .where(
                                memberTempIdEq(memberTempId),
                                notDeleted()
                        )
                        .fetchFirst()
        );
    }

    public Optional<MemberTemp> findByEmailNotDeleted(String email) {
        return Optional.ofNullable(
                query.selectFrom(memberTemp)
                        .where(
                                emailEq(email),
                                notDeleted()
                        )
                        .fetchOne()
        );
    }

    private BooleanExpression emailEq(String email) {
        return memberTemp.email.eq(email);
    }

    private BooleanExpression notDeleted() {
        return memberTemp.deletedDateTime.isNull();
    }

    private BooleanExpression memberTempIdEq(Long memberTempId) {
        return memberTemp.id.eq(memberTempId);
    }
}
