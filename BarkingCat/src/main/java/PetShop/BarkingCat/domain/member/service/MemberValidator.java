package PetShop.BarkingCat.domain.member.service;

import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberValidator {

    private final MemberRepository memberRepository;

    public MemberValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void validateDuplicateMember(String email) {
        Long count = memberRepository.countByEmail(email);

        if (memberIsPresent(count)) {
            throw new RuntimeException("이미 등록된 회원이메일 입니다.");
        }
    }

    private boolean memberIsPresent(Long count) {
        return count == 1;
    }
}
