package PetShop.BarkingCat.domain.member_temp.repository;

import PetShop.BarkingCat.domain.member_temp.model.MemberTemp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberTempRepository extends JpaRepository<MemberTemp, Long> {
}
