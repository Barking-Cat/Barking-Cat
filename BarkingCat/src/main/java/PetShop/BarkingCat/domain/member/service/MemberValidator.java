package PetShop.BarkingCat.domain.member.service;

import PetShop.BarkingCat.domain.member.member_temp.repository.query.MemberTempQueryRepository;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class MemberValidator {

    private final MemberRepository memberRepository;

    private final MemberTempQueryRepository memberTempQueryRepository;

    public MemberValidator(MemberRepository memberRepository, MemberTempQueryRepository memberTempQueryRepository) {
        this.memberRepository = memberRepository;
        this.memberTempQueryRepository = memberTempQueryRepository;
    }

    private static final String REGEX = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public void validateEmail(String email) {
        validateDuplicateEmail(email);
        validateEmailPatter(email);
    }

    private void validateDuplicateEmail(String email) {
        Long count = memberRepository.countByEmail(email);

        if (memberIsPresent(count)) {
            throw new RuntimeException("이미 등록된 회원이메일 입니다");
        }

        boolean exists = memberTempQueryRepository.existsByEmailNotDeleted(email);

        if (exists) {
            throw new RuntimeException("승인 대기중인 이메일입니다");
        }
    }

    private void validateEmailPatter(String email) {
        if (emailNotMatchWithPattern(email)) {
            throw new RuntimeException("이메일 형식이 맞지 않습니다");
        }
    }

    private boolean emailNotMatchWithPattern(String email) {
        return !PATTERN.matcher(email)
                .matches();
    }

    private boolean memberIsPresent(Long count) {
        return count == 1;
    }
}
