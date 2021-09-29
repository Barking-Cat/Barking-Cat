package PetShop.BarkingCat.domain.member.service;

import PetShop.BarkingCat.domain.member.dto.MemberForm;
import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원등록
    @Transactional
    public void joinMember(MemberForm memberForm){

        validateDuplicateMember(memberForm.getEmail());
        validateAuthPhoneNumber(memberForm.getAuthStatus());

        memberForm.setPassword(passwordEncoder.encode(memberForm.getPassword()));

        Member member = memberForm.entity();
        memberRepository.save(member);
    }

    // validation check: 휴대폰인증 처리 여부 확인
    private void validateAuthPhoneNumber(Member.AuthStatus authStatus) {
        if(authStatus == Member.AuthStatus.N){
            throw new RuntimeException("휴대폰인증을 진행해주세요.");
        }
    }

    // validation check: 중복회원가입
    private void validateDuplicateMember(String email) {

        Optional<Member> findByEmail = memberRepository.findByEmail(email);

        if(findByEmail.isPresent()){
            throw new RuntimeException("이미 등록된 회원이메일 입니다.");
        }
    }
}
