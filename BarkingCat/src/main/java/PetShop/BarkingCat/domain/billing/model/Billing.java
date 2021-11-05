package PetShop.BarkingCat.domain.billing.model;

import PetShop.BarkingCat.common.base.model.Base;
import PetShop.BarkingCat.domain.board.model.objects.Money;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Month;

@Entity
@NoArgsConstructor
public class Billing extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private int year;

    @Enumerated(EnumType.STRING)
    private Month month;

    private Money feeAmount;

    @Builder
    public Billing(Long id, Long memberId, LocalDateTime now) {
        this.id = id;
        this.memberId = memberId;
        this.year = now.getYear();
        this.month = now.getMonth();
        this.feeAmount = Money.ZERO;
    }

    public void addFee(Money baseFee) {
        this.feeAmount = this.feeAmount.plus(baseFee);
    }
}
