package PetShop.BarkingCat.domain.member.repository;

import PetShop.BarkingCat.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Long countByEmail(String email);

    Optional<Member> findByEmail(String email);
}
