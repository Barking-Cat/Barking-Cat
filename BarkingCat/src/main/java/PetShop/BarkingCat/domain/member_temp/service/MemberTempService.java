package PetShop.BarkingCat.domain.member_temp.service;

import PetShop.BarkingCat.domain.member_temp.dto.MemberForm;
import PetShop.BarkingCat.domain.member.service.MemberValidator;
import PetShop.BarkingCat.domain.member_temp.model.MemberTemp;
import PetShop.BarkingCat.domain.member_temp.repository.MemberTempRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberTempService {
    private final MemberTempRepository memberTempRepository;

    private final PasswordEncoder passwordEncoder;

    private final MemberValidator memberValidator;

    public MemberTempService(MemberTempRepository memberTempRepository, PasswordEncoder passwordEncoder, MemberValidator memberValidator) {
        this.memberTempRepository = memberTempRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberValidator = memberValidator;
    }

    @Transactional
    public void join(MemberForm memberForm) {
        memberForm.validate(memberValidator);
        memberForm.encryptPassword(passwordEncoder);

        MemberTemp memberTemp = memberForm.entity();
        memberTempRepository.save(memberTemp);
    }
}
