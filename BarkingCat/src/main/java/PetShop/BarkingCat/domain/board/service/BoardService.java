package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.domain.board.dto.BoardForm;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.Category;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.board.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    public BoardService(BoardRepository boardRepository, CategoryRepository categoryRepository) {
        this.boardRepository = boardRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void registerBoard(BoardForm boardForm) {
        Category category = categoryRepository.findById(boardForm.getCategoryId())
                .orElseThrow(() -> new RuntimeException("카테고리가 없습니다."));
        Board board = boardForm.entity()
                .mapCategory(category);
        boardRepository.save(board);
    }
}
