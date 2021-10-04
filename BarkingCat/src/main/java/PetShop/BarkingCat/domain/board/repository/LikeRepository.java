package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    Long countByBoard(Board board);

    Optional<Likes> findFirstByBoardAndMemberIdOrderByIdDesc(Board board, Long memberId);
}
