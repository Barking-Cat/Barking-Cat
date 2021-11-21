package PetShop.BarkingCat.domain.member.member_temp.repository;

import PetShop.BarkingCat.domain.member.member_temp.model.PhoneAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneAuthRepository extends JpaRepository<PhoneAuth, Long> {
    Optional<PhoneAuth> findByPhoneNumberAndAuthCodeAndCertifiedFalse(String phoneNumber, String authCode);
}
