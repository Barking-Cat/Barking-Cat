package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.domain.board.repository.query.BoardQueryRepository;
import PetShop.BarkingCat.domain.member.model.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;

@Service
public class BoardRegulationService {

    private final BoardQueryRepository boardQueryRepository;

    public BoardRegulationService(BoardQueryRepository boardQueryRepository) {
        this.boardQueryRepository = boardQueryRepository;
    }

    public void checkMonthlyBoardCount(Member member) {
        Month month = LocalDateTime.now()
                .getMonth();

        Integer count = boardQueryRepository.countMyMonthlyBoard(member.id(), month);
        member.checkBoardLimit(count);
    }
}
