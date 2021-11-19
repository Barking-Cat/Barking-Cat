package PetShop.BarkingCat.domain.member.service;

import PetShop.BarkingCat.common.exception.BarkingCatException;
import PetShop.BarkingCat.common.exception.ErrorCode;
import PetShop.BarkingCat.common.sns.Message;
import PetShop.BarkingCat.common.sns.Sns;
import PetShop.BarkingCat.common.utils.AuthUtil;
import PetShop.BarkingCat.domain.member.dto.PasswordReset;
import PetShop.BarkingCat.domain.member.dto.PhoneAuthForPassword;
import PetShop.BarkingCat.domain.member.member_temp.model.PhoneAuth;
import PetShop.BarkingCat.domain.member.member_temp.repository.PhoneAuthRepository;
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

    private final PhoneAuthRepository phoneAuthRepository;

    private final Sns sns;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, MemberValidator memberValidator, PhoneAuthRepository phoneAuthRepository, Sns sns) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberValidator = memberValidator;
        this.phoneAuthRepository = phoneAuthRepository;
        this.sns = sns;
    }

    @Transactional
    public void sendAuthNumber(PhoneAuthForPassword phoneAuthForPassword) {
        Member member = memberRepository.findByEmail(phoneAuthForPassword.getEmail())
                .orElseThrow(() -> new BarkingCatException(ErrorCode.MEMBER_NOT_FOUND));
        member.checkPhoneNumber(phoneAuthForPassword.getPhoneNumber());

        String authCode = AuthUtil.createAuthCode();

        PhoneAuth phoneAuth = PhoneAuth.builder()
                .phoneNumber(phoneAuthForPassword.getPhoneNumber())
                .authCode(authCode)
                .build();

        phoneAuthRepository.save(phoneAuth);

        sns.send(new Message(phoneAuthForPassword.getPhoneNumber(), authCode));
    }

    @Transactional
    public void resetPassword(PasswordReset passwordReset) {
        Member member = memberRepository.findByEmail(passwordReset.getEmail())
                .orElseThrow(() -> new BarkingCatException(ErrorCode.MEMBER_NOT_FOUND));

        member.updatePassword(passwordReset.getPassword(), passwordEncoder);
    }
}
