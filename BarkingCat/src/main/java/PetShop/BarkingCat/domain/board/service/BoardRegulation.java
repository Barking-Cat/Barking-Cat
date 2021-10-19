package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.domain.board.repository.query.BoardQueryRepository;
import PetShop.BarkingCat.domain.member.model.Member;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;

@Component
public class BoardRegulation {

    private final BoardQueryRepository boardQueryRepository;

    public BoardRegulation(BoardQueryRepository boardQueryRepository) {
        this.boardQueryRepository = boardQueryRepository;
    }

    public void checkMonthlyBoardCount(Member member) {
        Month month = LocalDateTime.now()
                .getMonth();

        Integer count = boardQueryRepository.countMyMonthlyBoard(member.id(), month);
        member.checkBoardLimit(count);
    }
}
