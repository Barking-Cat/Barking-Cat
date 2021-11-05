package PetShop.BarkingCat.domain.billing.repository;

import PetShop.BarkingCat.domain.billing.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.util.Optional;

public interface BillingRepository extends JpaRepository<Billing, Long> {
    Optional<Billing> findByMemberIdAndYearAndMonth(Long memberId, int year, Month month);
}
