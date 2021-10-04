package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.Likes;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.board.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;

    public LikeService(BoardRepository boardRepository, LikeRepository likeRepository) {
        this.boardRepository = boardRepository;
        this.likeRepository = likeRepository;
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
    public void unlike(Long likeId, Long memberId) {
        Likes likes = likeRepository.findById(likeId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 좋아요입니다"));

        likes.delete(memberId);
    }
}
