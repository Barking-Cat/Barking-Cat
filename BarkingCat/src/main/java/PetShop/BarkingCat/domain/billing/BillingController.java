package PetShop.BarkingCat.domain.billing;

import PetShop.BarkingCat.common.security.annotations.Authenticated;
import PetShop.BarkingCat.common.security.annotations.JwtClaim;
import PetShop.BarkingCat.domain.billing.dto.BillingLogCondition;
import PetShop.BarkingCat.domain.billing.service.query.BillingQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
@Authenticated
public class BillingController {
    private final BillingQueryService billingQueryService;

    public BillingController(BillingQueryService billingQueryService) {
        this.billingQueryService = billingQueryService;
    }

    @GetMapping
    public ResponseEntity<?> findBillingOfMember(@JwtClaim("info.id") Long memberId, Pageable pageable) {
        return ResponseEntity.ok(billingQueryService.findBillingOfMember(memberId, pageable));
    }

    @GetMapping("/log")
    public ResponseEntity<?> findBillingLogOfMember(@JwtClaim("info.id") Long memberId, BillingLogCondition billingLogCondition, Pageable pageable) {
        return ResponseEntity.ok(billingQueryService.findBillingLogOfMember(memberId, billingLogCondition, pageable));
    }
}
