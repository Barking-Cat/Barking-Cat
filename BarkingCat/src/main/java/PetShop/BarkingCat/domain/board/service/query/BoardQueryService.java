package PetShop.BarkingCat.domain.board.service.query;

import PetShop.BarkingCat.domain.board.dto.*;
import PetShop.BarkingCat.domain.board.repository.query.BoardQueryRepository;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BoardQueryService {
    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;

    public BoardQueryService(BoardRepository boardRepository, BoardQueryRepository boardQueryRepository) {
        this.boardRepository = boardRepository;
        this.boardQueryRepository = boardQueryRepository;
    }

    public Page<BoardResponse> findByCondition(FindBoardCondition findBoardCondition, Pageable pageable) {
        return boardQueryRepository.findByCondition(findBoardCondition, pageable);
    }

    public BoardDetailResponse findDetail(Long boardId) {
        return boardQueryRepository.findDetail(boardId);
    }

    public Page<MyPageBoardResponse> findMyPageBoardList(Long memberId, Pageable pageable){
        return boardQueryRepository.findMyPageBoardList(memberId, pageable);
    }
}
