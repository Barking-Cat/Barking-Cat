package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.domain.board.dto.BoardForm;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.Category;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.board.repository.CategoryRepository;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    public BoardService(BoardRepository boardRepository, CategoryRepository categoryRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.categoryRepository = categoryRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void registerBoard(BoardForm boardForm, Long memberId) {
        Category category = categoryRepository.findById(boardForm.getCategoryId())
                .orElseThrow(() -> new RuntimeException("카테고리가 없습니다."));

        memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("등록된 사용자가 없습니다."));

        Board board = boardForm.entity()
                .mapCategory(category)
                .mapWriter(memberId);

        boardRepository.save(board);
    }
}
