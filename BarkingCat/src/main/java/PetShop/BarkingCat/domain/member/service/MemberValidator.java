package PetShop.BarkingCat.domain.member.service;

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
        Long count = memberRepository.countByEmail(new Email(email));

        if (memberIsPresent(count)) {
            throw new RuntimeException("이미 등록된 회원이메일 입니다");
        }

        boolean exists = memberTempQueryRepository.existsByEmailNotDeleted(email);

        if (exists) {
            throw new RuntimeException("승인 대기중인 이메일입니다");
        }
    }

    private boolean memberIsPresent(Long count) {
        return count == 1;
    }
}
