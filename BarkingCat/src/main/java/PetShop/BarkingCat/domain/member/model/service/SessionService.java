package PetShop.BarkingCat.domain.member.model.service;

import PetShop.BarkingCat.common.security.JwtService;
import PetShop.BarkingCat.common.security.Password;
import PetShop.BarkingCat.domain.member.model.LoginForm;
import PetShop.BarkingCat.domain.member.model.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class SessionService {
    private final MemberRepository memberRepository;

    private final JwtService jwtService;


    public SessionService(MemberRepository memberRepository, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.jwtService = jwtService;
    }

    public String login(LoginForm loginForm, int duration) {
        Long count = memberRepository.countByEmailAndPassword(loginForm.getEmail(), new Password(loginForm.getPassword()).content());

        if (noMemberInfo(count)) return "";

        return jwtService.createToken(loginForm, ZonedDateTime.now()
                .plusSeconds(duration));
    }

    private boolean noMemberInfo(Long count) {
        return count == 0;
    }
}
