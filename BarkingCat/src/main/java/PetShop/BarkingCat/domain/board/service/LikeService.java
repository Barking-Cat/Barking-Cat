package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.common.exception.BarkingCatException;
import PetShop.BarkingCat.common.exception.ErrorCode;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.Liked;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.board.repository.LikeRepository;
import PetShop.BarkingCat.domain.board.repository.query.LikeQueryRepository;
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
                .orElseThrow(() -> new BarkingCatException(ErrorCode.BOARD_NOT_FOUND));

        checkDuplicatedLike(boardId, memberId);

        Liked liked = Liked.builder()
                .board(board)
                .memberId(memberId)
                .build();

        likeRepository.save(liked);
    }

    private void checkDuplicatedLike(Long boardId, Long memberId) {
        Liked recentLiked = likeQueryRepository.findRecentLike(boardId, memberId);

        if (alreadyLikedTheBoard(recentLiked)) {
            throw new BarkingCatException(ErrorCode.ALREADY_LIKED);
        }
    }

    private boolean alreadyLikedTheBoard(Liked recentLiked) {
        return recentLiked != null;
    }

    @Transactional
    public void unlike(Long boardId, Long memberId) {
        likeQueryRepository.findRecentLike(boardId, memberId)
                .delete();
    }
}
