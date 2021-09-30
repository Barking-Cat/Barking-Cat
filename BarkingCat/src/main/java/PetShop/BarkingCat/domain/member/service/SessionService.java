package PetShop.BarkingCat.domain.member.service;

import PetShop.BarkingCat.common.security.JwtService;
import PetShop.BarkingCat.domain.member.dto.LoginForm;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class SessionService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public SessionService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(LoginForm loginForm, int duration) {
        memberRepository.findByEmail(loginForm.getEmail())
                .orElseThrow()
                .checkPassword(loginForm.getPassword(), passwordEncoder);

        return jwtService.createToken(loginForm, ZonedDateTime.now()
                .plusSeconds(duration));
    }
}
