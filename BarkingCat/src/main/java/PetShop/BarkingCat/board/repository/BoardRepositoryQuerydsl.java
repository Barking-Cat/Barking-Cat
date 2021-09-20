package PetShop.BarkingCat.board.repository;

import PetShop.BarkingCat.board.model.Board;

import java.util.List;

public interface BoardRepositoryQuerydsl {

    List<Board> findAllNotDeleted();

}
