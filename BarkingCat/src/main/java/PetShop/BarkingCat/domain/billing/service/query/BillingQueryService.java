package PetShop.BarkingCat.domain.billing.service.query;

import PetShop.BarkingCat.domain.billing.dto.BillingLogCondition;
import PetShop.BarkingCat.domain.billing.dto.BillingLogResponse;
import PetShop.BarkingCat.domain.billing.dto.BillingResponse;
import PetShop.BarkingCat.domain.billing.repository.query.BillingQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BillingQueryService {
    private final BillingQueryRepository billingQueryRepository;

    public BillingQueryService(BillingQueryRepository billingQueryRepository) {
        this.billingQueryRepository = billingQueryRepository;
    }

    public Page<BillingResponse> findBillingOfMember(Long memberId, Pageable pageable) {
        return billingQueryRepository.findBillingOfMember(memberId, pageable);
    }

    public Page<BillingLogResponse> findBillingLogOfMember(Long memberId, BillingLogCondition billingLogCondition, Pageable pageable) {
        return billingQueryRepository.findBillingLogOfMember(memberId, billingLogCondition, pageable);
    }
}
