package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
