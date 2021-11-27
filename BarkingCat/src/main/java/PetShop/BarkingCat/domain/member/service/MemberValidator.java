package PetShop.BarkingCat.domain.member.service;

import PetShop.BarkingCat.common.exception.BarkingCatException;
import PetShop.BarkingCat.common.exception.ErrorCode;
import PetShop.BarkingCat.domain.member.member_temp.repository.query.MemberTempQueryRepository;
import PetShop.BarkingCat.domain.member.model.objects.Email;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberValidator {

    private final MemberRepository memberRepository;

    private final MemberTempQueryRepository memberTempQueryRepository;

    public MemberValidator(MemberRepository memberRepository, MemberTempQueryRepository memberTempQueryRepository) {
        this.memberRepository = memberRepository;
        this.memberTempQueryRepository = memberTempQueryRepository;
    }

    public void validateEmail(String email) {
        validateDuplicateEmail(email);
    }

    private void validateDuplicateEmail(String email) {
        boolean exists = memberRepository.existsByEmail(new Email(email));

        if (exists) {
            throw new BarkingCatException(ErrorCode.DUPLICATE_EMAIL);
        }

        boolean existsTemp = memberTempQueryRepository.existsByEmailNotDeleted(email);

        if (existsTemp) {
            throw new BarkingCatException(ErrorCode.TEMP_EMAIL_EXIST);
        }
    }
}
