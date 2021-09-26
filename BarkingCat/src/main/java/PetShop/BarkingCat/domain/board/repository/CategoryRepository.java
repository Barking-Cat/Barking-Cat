package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
