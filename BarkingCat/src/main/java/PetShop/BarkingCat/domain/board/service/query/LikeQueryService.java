package PetShop.BarkingCat.domain.board.service.query;

import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.board.repository.query.LikeQueryRepository;
import PetShop.BarkingCat.domain.board.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LikeQueryService {
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;
    private final LikeQueryRepository likeQueryRepository;

    public LikeQueryService(BoardRepository boardRepository, LikeRepository likeRepository, LikeQueryRepository likeQueryRepository) {
        this.boardRepository = boardRepository;
        this.likeRepository = likeRepository;
        this.likeQueryRepository = likeQueryRepository;
    }

    public Long findLikeCountOfBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다"));

        return likeRepository.countByBoard(board);
    }

    public boolean isMemberLikedTheBoard(Long boardId, Long memberId) {
        return likeQueryRepository.existsByBoardAndMember(boardId, memberId);
    }
}
