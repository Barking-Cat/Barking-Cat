package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.dto.BoardDetailResponse;
import PetShop.BarkingCat.domain.board.dto.BoardResponse;
import PetShop.BarkingCat.domain.board.dto.FindBoardCondition;
import PetShop.BarkingCat.domain.board.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryQuerydsl {

    List<Board> findAllNotDeleted();

    Page<BoardResponse> findByCondition(FindBoardCondition findBoardCondition, Pageable pageable);

    BoardDetailResponse findDetail(Long boardId);
}
