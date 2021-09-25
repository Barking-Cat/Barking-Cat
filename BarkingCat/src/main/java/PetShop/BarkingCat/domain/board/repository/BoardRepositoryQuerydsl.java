package PetShop.BarkingCat.domain.board.repository;

import PetShop.BarkingCat.domain.board.model.Board;

import java.util.List;

public interface BoardRepositoryQuerydsl {

    List<Board> findAllNotDeleted();

}
