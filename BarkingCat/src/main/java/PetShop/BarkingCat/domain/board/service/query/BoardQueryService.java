package PetShop.BarkingCat.domain.board.service.query;

import PetShop.BarkingCat.domain.board.dto.BoardDetailResponse;
import PetShop.BarkingCat.domain.board.dto.BoardResponse;
import PetShop.BarkingCat.domain.board.dto.FindBoardCondition;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BoardQueryService {
    private final BoardRepository boardRepository;

    public BoardQueryService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Page<BoardResponse> findByCondition(FindBoardCondition findBoardCondition, Pageable pageable) {
        return boardRepository.findByCondition(findBoardCondition, pageable);
    }

    public BoardDetailResponse findDetail(Long boardId) {
        return boardRepository.findDetail(boardId);
    }
}
