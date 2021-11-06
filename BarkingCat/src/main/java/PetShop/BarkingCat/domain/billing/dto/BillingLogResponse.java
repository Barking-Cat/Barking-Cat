package PetShop.BarkingCat.domain.billing.dto;

import PetShop.BarkingCat.domain.board.model.objects.Money;
import PetShop.BarkingCat.domain.board.model.objects.Title;
import lombok.Getter;

@Getter
public class BillingLogResponse {
    private final Long billingLogId;
    private final Long boardId;
    private final Title boardTitle;
    private final Money fee;

    public BillingLogResponse(Long billingLogId, Long boardId, Title boardTitle, Money fee) {
        this.billingLogId = billingLogId;
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.fee = fee;
    }

    public String getBoardTitle() {
        return boardTitle.content();
    }

    public Long getFee() {
        return fee.longValue();
    }
}
