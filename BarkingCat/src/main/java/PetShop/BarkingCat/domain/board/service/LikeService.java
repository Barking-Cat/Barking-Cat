package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.Likes;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.board.repository.LikeQueryRepository;
import PetShop.BarkingCat.domain.board.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;
    private final LikeQueryRepository likeQueryRepository;

    public LikeService(BoardRepository boardRepository, LikeRepository likeRepository, LikeQueryRepository likeQueryRepository) {
        this.boardRepository = boardRepository;
        this.likeRepository = likeRepository;
        this.likeQueryRepository = likeQueryRepository;
    }

    @Transactional
    public void like(Long boardId, Long memberId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시물이 존재하지 않습니다"));

        Likes likes = Likes.builder()
                .board(board)
                .memberId(memberId)
                .build();

        likeRepository.save(likes);
    }

    @Transactional
    public void unlike(Long boardId, Long memberId) {
        likeQueryRepository.findRecentLike(boardId, memberId)
                .delete();
    }
}
