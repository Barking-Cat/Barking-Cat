package PetShop.BarkingCat.domain.member.service;

import PetShop.BarkingCat.common.security.JwtService;
import PetShop.BarkingCat.domain.member.dto.LoginForm;
import PetShop.BarkingCat.domain.member.member_temp.repository.query.MemberTempQueryRepository;
import PetShop.BarkingCat.domain.member.model.objects.Email;
import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class SessionService {
    private final MemberRepository memberRepository;

    private final MemberTempQueryRepository memberTempQueryRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public SessionService(MemberRepository memberRepository, MemberTempQueryRepository memberTempQueryRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.memberTempQueryRepository = memberTempQueryRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(LoginForm loginForm, int duration) {
        Optional<Member> memberOptional = memberRepository.findByEmail(new Email(loginForm.getEmail()));

        if (memberOptional.isEmpty()) {
            findMemberTemp(loginForm.getEmail());
        }

        Member member = memberOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다"));
        member.checkPassword(loginForm.getPassword(), passwordEncoder);

        return jwtService.createToken(member.createPayload(), ZonedDateTime.now()
                .plusSeconds(duration));
    }

    private void findMemberTemp(String email) {
        boolean exists = memberTempQueryRepository.existsByEmailNotDeleted(email);

        if (exists) {
            throw new RuntimeException("아직 승인되지 않은 계정입니다");
        }
    }
}
