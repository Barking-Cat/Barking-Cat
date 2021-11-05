package PetShop.BarkingCat.domain.billing.event.handler;

import PetShop.BarkingCat.common.exception.BarkingCatException;
import PetShop.BarkingCat.common.exception.ErrorCode;
import PetShop.BarkingCat.domain.billing.model.Billing;
import PetShop.BarkingCat.domain.billing.model.BillingLog;
import PetShop.BarkingCat.domain.billing.repository.BillingLogRepository;
import PetShop.BarkingCat.domain.billing.repository.BillingRepository;
import PetShop.BarkingCat.domain.board.model.event.BoardRegisterEvent;
import PetShop.BarkingCat.domain.board.model.objects.Money;
import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class BoardRegisterEventHandler {

    private final MemberRepository memberRepository;
    private final BillingRepository billingRepository;
    private final BillingLogRepository billingLogRepository;

    @Async
    @EventListener
    @Transactional
    public void handle(BoardRegisterEvent boardRegisterEvent) {
        Long writerId = boardRegisterEvent.writerId();
        Member member = memberRepository.findById(writerId)
                .orElseThrow(() -> new BarkingCatException(ErrorCode.MEMBER_NOT_FOUND));

        if (member.isNormalMember()) {
            return;
        }

        Money baseFee = member.baseFee();

        LocalDateTime now = LocalDateTime.now();

        Billing billing = billingRepository.findByMemberIdAndYearAndAndMonth(writerId, now.getYear(), now.getMonth())
                .orElse(Billing.builder()
                        .memberId(writerId)
                        .now(LocalDateTime.now())
                        .build());

        billing.addFee(baseFee);

        BillingLog billingLog = BillingLog.builder()
                .billing(billing)
                .boardId(boardRegisterEvent.boardId())
                .build();

        billingRepository.save(billing);
        billingLogRepository.save(billingLog);
    }
}
