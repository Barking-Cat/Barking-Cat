package PetShop.BarkingCat.domain.member.member_temp.service;

import PetShop.BarkingCat.common.exception.BarkingCatException;
import PetShop.BarkingCat.common.exception.ErrorCode;
import PetShop.BarkingCat.common.sns.Message;
import PetShop.BarkingCat.common.sns.Sns;
import PetShop.BarkingCat.common.utils.AuthUtil;
import PetShop.BarkingCat.domain.member.member_temp.dto.Certify;
import PetShop.BarkingCat.domain.member.member_temp.dto.MemberForm;
import PetShop.BarkingCat.domain.member.member_temp.dto.Phone;
import PetShop.BarkingCat.domain.member.member_temp.model.MemberTemp;
import PetShop.BarkingCat.domain.member.member_temp.model.PhoneAuth;
import PetShop.BarkingCat.domain.member.member_temp.repository.MemberTempRepository;
import PetShop.BarkingCat.domain.member.member_temp.repository.PhoneAuthRepository;
import PetShop.BarkingCat.domain.member.member_temp.repository.query.MemberTempQueryRepository;
import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import PetShop.BarkingCat.domain.member.service.MemberValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MemberTempService {
    private final MemberTempRepository memberTempRepository;

    private final MemberTempQueryRepository memberTempQueryRepository;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final MemberValidator memberValidator;

    private final PhoneAuthRepository phoneAuthRepository;

    private final Sns sns;

    public MemberTempService(MemberTempRepository memberTempRepository, MemberTempQueryRepository memberTempQueryRepository, MemberRepository memberRepository, PasswordEncoder passwordEncoder, MemberValidator memberValidator, PhoneAuthRepository phoneAuthRepository, Sns sns) {
        this.memberTempRepository = memberTempRepository;
        this.memberTempQueryRepository = memberTempQueryRepository;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberValidator = memberValidator;
        this.phoneAuthRepository = phoneAuthRepository;
        this.sns = sns;
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
                .orElseThrow(() -> new BarkingCatException(ErrorCode.MEMBER_NOT_FOUND));

        saveMember(memberTemp);
        memberTemp.delete();
    }

    @Transactional
    public void sendAuthNumber(Phone phone) {
        String authCode = AuthUtil.createAuthCode();

        PhoneAuth phoneAuth = PhoneAuth.builder()
                .phoneNumber(phone.getPhoneNumber())
                .authCode(authCode)
                .build();

        phoneAuthRepository.save(phoneAuth);

        sns.send(new Message(phone.getPhoneNumber(), authCode));
    }

    @Transactional
    public void certifyAuthNumber(Certify certify) {
        PhoneAuth phoneAuth = memberTempQueryRepository.findPhoneAuthByPhone(certify.getPhoneNumber())
                .orElseThrow(() -> new BarkingCatException(ErrorCode.CERTIFICATION_NOT_FOUND));

        phoneAuth.certify(certify.getAuthCode());
    }
}
