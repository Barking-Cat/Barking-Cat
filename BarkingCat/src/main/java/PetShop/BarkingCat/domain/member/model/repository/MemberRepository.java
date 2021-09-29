package PetShop.BarkingCat.domain.member.model.repository;

import PetShop.BarkingCat.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Long countByEmailAndPassword(String email, String password);
}
