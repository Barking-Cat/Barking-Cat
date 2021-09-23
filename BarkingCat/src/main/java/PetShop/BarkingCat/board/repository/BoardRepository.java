package PetShop.BarkingCat.board.repository;

import PetShop.BarkingCat.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryQuerydsl {
}