package PetShop.BarkingCat.domain.billing.repository;

import PetShop.BarkingCat.domain.billing.model.BillingLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingLogRepository extends JpaRepository<BillingLog, Long> {
}
