package PetShop.BarkingCat.domain.billing.model;

import PetShop.BarkingCat.common.base.model.Base;
import PetShop.BarkingCat.domain.board.model.objects.Money;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class BillingLog extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Billing billing;

    private Long boardId;

    private Money fee;

    @Builder
    public BillingLog(Long id, Billing billing, Long boardId, Money fee) {
        this.id = id;
        this.billing = billing;
        this.boardId = boardId;
        this.fee = fee;
    }
}
