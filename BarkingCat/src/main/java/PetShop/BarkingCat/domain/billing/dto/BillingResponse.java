package PetShop.BarkingCat.domain.billing.dto;

import PetShop.BarkingCat.domain.board.model.objects.Money;
import lombok.Getter;

import java.time.Month;

@Getter
public class BillingResponse {
    private final Long id;
    private final Long memberId;
    private final String memberName;
    private final int year;
    private final Month month;
    private final Money feeAmount;

    public BillingResponse(Long id, Long memberId, String memberName, int year, Month month, Money feeAmount) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.year = year;
        this.month = month;
        this.feeAmount = feeAmount;
    }

    public Long getFeeAmount() {
        return feeAmount.longValue();
    }
}
