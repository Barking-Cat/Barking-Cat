package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitle_TitleContains(String title);
}
