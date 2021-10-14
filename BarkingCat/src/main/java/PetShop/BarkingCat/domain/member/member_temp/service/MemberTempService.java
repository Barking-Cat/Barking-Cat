package PetShop.BarkingCat.domain.member.member_temp.service;

import PetShop.BarkingCat.common.exception.BarkingCatException;
import PetShop.BarkingCat.common.exception.ErrorCode;
import PetShop.BarkingCat.domain.member.member_temp.dto.MemberForm;
import PetShop.BarkingCat.domain.member.member_temp.repository.query.MemberTempQueryRepository;
import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import PetShop.BarkingCat.domain.member.service.MemberValidator;
import PetShop.BarkingCat.domain.member.member_temp.model.MemberTemp;
import PetShop.BarkingCat.domain.member.member_temp.repository.MemberTempRepository;
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
        System.out.println(memberForm.getEmail());
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
                .orElseThrow(() -> new BarkingCatException(ErrorCode.MEMBER_NOT_FOUND));

        saveMember(memberTemp);
        memberTemp.delete();
    }
}
