package PetShop.BarkingCat.domain.member_temp.service;

import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import PetShop.BarkingCat.domain.member.service.MemberValidator;
import PetShop.BarkingCat.domain.member_temp.dto.MemberForm;
import PetShop.BarkingCat.domain.member_temp.model.MemberTemp;
import PetShop.BarkingCat.domain.member_temp.repository.MemberTempRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberTempService {
    private final MemberTempRepository memberTempRepository;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final MemberValidator memberValidator;

    public MemberTempService(MemberTempRepository memberTempRepository, MemberRepository memberRepository, PasswordEncoder passwordEncoder, MemberValidator memberValidator) {
        this.memberTempRepository = memberTempRepository;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberValidator = memberValidator;
    }

    @Transactional
    public void join(MemberForm memberForm) {
        MemberTemp memberTemp = memberForm.entity(memberValidator, passwordEncoder);
        saveMember(memberTemp);
    }

    private void saveMember(MemberTemp memberTemp) {
        if (isNormalMember(memberTemp)) {
            Member member = memberTemp.createMember();
            memberRepository.save(member);
            return;
        }

        memberTempRepository.save(memberTemp);
    }

    private boolean isNormalMember(MemberTemp memberTemp) {
        return memberTemp.type() == Member.MemberType.NORMAL;
    }
}
