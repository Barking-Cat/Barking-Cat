package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.common.exception.BarkingCatException;
import PetShop.BarkingCat.common.exception.ErrorCode;
import PetShop.BarkingCat.domain.board.dto.BoardForm;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.Category;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.board.repository.CategoryRepository;
import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final BoardRegulationService boardRegulationService;

    public BoardService(BoardRepository boardRepository, CategoryRepository categoryRepository, MemberRepository memberRepository, BoardRegulationService boardRegulationService) {
        this.boardRepository = boardRepository;
        this.categoryRepository = categoryRepository;
        this.memberRepository = memberRepository;
        this.boardRegulationService = boardRegulationService;
    }

    @Transactional
    public Long registerBoard(BoardForm boardForm, Long memberId) {
        Category category = categoryRepository.findById(boardForm.getCategoryId())
                .orElseThrow(() -> new BarkingCatException(ErrorCode.CATEGORY_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BarkingCatException(ErrorCode.MEMBER_NOT_FOUND));

        boardRegulationService.checkMonthlyBoardCount(member);

        Board board = boardForm.entity()
                .mapCategory(category)
                .mapWriter(memberId);

        return boardRepository.save(board)
                .id();
    }

    @Transactional
    public void hit(Long boardId) {
        boardRepository.findById(boardId)
                .orElseThrow(() -> new BarkingCatException(ErrorCode.BOARD_NOT_FOUND))
                .hit();
    }
}
