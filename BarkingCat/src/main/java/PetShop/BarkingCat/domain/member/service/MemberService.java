package PetShop.BarkingCat.domain.member.service;

import PetShop.BarkingCat.domain.member_temp.dto.MemberForm;
import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final MemberValidator memberValidator;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, MemberValidator memberValidator) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberValidator = memberValidator;
    }

    @Transactional
    public void joinMember(MemberForm memberForm) {
    }
}
