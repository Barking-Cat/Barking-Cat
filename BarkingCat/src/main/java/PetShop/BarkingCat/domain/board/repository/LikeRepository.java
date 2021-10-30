package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.Liked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Liked, Long> {
    Long countByBoard(Board board);

    Optional<Liked> findFirstByBoardAndMemberIdOrderByIdDesc(Board board, Long memberId);
}
