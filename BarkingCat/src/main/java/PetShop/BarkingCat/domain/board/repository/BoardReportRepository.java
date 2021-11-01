package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.model.BoardReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardReportRepository extends JpaRepository<BoardReport, Long> {
}
