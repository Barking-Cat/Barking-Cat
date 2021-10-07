package PetShop.BarkingCat.domain.member_temp.service;

import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import PetShop.BarkingCat.domain.member.service.MemberValidator;
import PetShop.BarkingCat.domain.member_temp.dto.MemberForm;
import PetShop.BarkingCat.domain.member_temp.model.MemberTemp;
import PetShop.BarkingCat.domain.member_temp.repository.MemberTempRepository;
import PetShop.BarkingCat.domain.member_temp.repository.query.MemberTempQueryRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberTempService {
    private final MemberTempRepository memberTempRepository;

    private final MemberTempQueryRepository memberTempQueryRepository;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final MemberValidator memberValidator;

    public MemberTempService(MemberTempRepository memberTempRepository, MemberTempQueryRepository memberTempQueryRepository, MemberRepository memberRepository, PasswordEncoder passwordEncoder, MemberValidator memberValidator) {
        this.memberTempRepository = memberTempRepository;
        this.memberTempQueryRepository = memberTempQueryRepository;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberValidator = memberValidator;
    }

    @Transactional
    public void join(MemberForm memberForm) {
        MemberTemp memberTemp = memberForm.entity(memberValidator, passwordEncoder);
        saveMemberTemp(memberTemp);
    }

    private void saveMemberTemp(MemberTemp memberTemp) {
        if (isNormalMember(memberTemp)) {
            saveMember(memberTemp);
            return;
        }

        memberTempRepository.save(memberTemp);
    }

    private void saveMember(MemberTemp memberTemp) {
        Member member = memberTemp.createMember();
        memberRepository.save(member);
    }

    private boolean isNormalMember(MemberTemp memberTemp) {
        return memberTemp.type() == Member.MemberType.NORMAL;
    }

    @Transactional
    public void auth(Long memberTempId) {
        MemberTemp memberTemp = memberTempQueryRepository.findByIdNotDeleted(memberTempId)
                .orElseThrow(() -> new RuntimeException("존재하지 않은 사용자입니다."));

        saveMember(memberTemp);
        memberTemp.delete();
    }
}
