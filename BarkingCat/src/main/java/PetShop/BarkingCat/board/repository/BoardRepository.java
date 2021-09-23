package PetShop.BarkingCat.board.repository;

import PetShop.BarkingCat.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryQuerydsl {
    List<Board> findByTitle_TitleContains(String title);
}
