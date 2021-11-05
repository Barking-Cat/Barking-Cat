package PetShop.BarkingCat.domain.billing.model;

import PetShop.BarkingCat.common.base.model.Base;
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

    @Builder
    public BillingLog(Long id, Billing billing, Long boardId) {
        this.id = id;
        this.billing = billing;
        this.boardId = boardId;
    }
}
