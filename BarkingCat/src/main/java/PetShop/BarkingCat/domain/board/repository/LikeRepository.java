package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
}
