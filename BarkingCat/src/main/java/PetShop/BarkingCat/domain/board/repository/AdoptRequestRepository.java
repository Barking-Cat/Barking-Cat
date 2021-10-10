package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.model.AdoptRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptRequestRepository extends JpaRepository<AdoptRequest, Long> {
}
