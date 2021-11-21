package PetShop.BarkingCat.domain.member.member_temp.repository.query;

import PetShop.BarkingCat.domain.member.member_temp.model.MemberTemp;
import PetShop.BarkingCat.domain.member.member_temp.model.PhoneAuth;
import PetShop.BarkingCat.domain.member.model.objects.Email;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static PetShop.BarkingCat.domain.member.member_temp.model.QMemberTemp.memberTemp;
import static PetShop.BarkingCat.domain.member.member_temp.model.QPhoneAuth.phoneAuth;

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
                        .fetchOne()
        );
    }

    public boolean existsByEmailNotDeleted(String email) {
        return query.selectOne()
                .from(memberTemp)
                .where(
                        emailEq(email),
                        notDeleted()
                )
                .fetchFirst() != null;
    }

    public Optional<PhoneAuth> findPhoneAuthByPhone(String phoneNumber) {
        return Optional.ofNullable(
                query.selectFrom(phoneAuth)
                        .where(phoneAuth.phoneNumber.eq(phoneNumber))
                        .where(phoneAuth.certified.isFalse())
                        .orderBy(phoneAuth.id.desc())
                        .fetchFirst()
        );
    }

    private BooleanExpression emailEq(String email) {
        return memberTemp.email.eq(new Email(email));
    }

    private BooleanExpression notDeleted() {
        return memberTemp.deletedDateTime.isNull();
    }

    private BooleanExpression memberTempIdEq(Long memberTempId) {
        return memberTemp.id.eq(memberTempId);
    }
}
