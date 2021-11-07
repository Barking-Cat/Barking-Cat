package PetShop.BarkingCat.domain.admin.service;

import PetShop.BarkingCat.common.exception.BarkingCatException;
import PetShop.BarkingCat.common.exception.ErrorCode;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    private final BoardRepository boardRepository;

    public AdminService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void deleteReportedBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BarkingCatException(ErrorCode.BOARD_NOT_FOUND));
        board.delete();
    }

}
