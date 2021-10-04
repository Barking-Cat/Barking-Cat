package PetShop.BarkingCat.domain.board;

import PetShop.BarkingCat.common.security.annotations.Authenticated;
import PetShop.BarkingCat.common.security.annotations.JwtClaim;
import PetShop.BarkingCat.domain.board.dto.BoardForm;
import PetShop.BarkingCat.domain.board.dto.FindBoardCondition;
import PetShop.BarkingCat.domain.board.service.BoardService;
import PetShop.BarkingCat.domain.board.service.query.BoardQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final BoardQueryService boardQueryService;

    public BoardController(BoardService boardService, BoardQueryService boardQueryService) {
        this.boardService = boardService;
        this.boardQueryService = boardQueryService;
    }

    @GetMapping
    public ResponseEntity<?> findByCondition(FindBoardCondition findBoardCondition, Pageable pageable) {
        return ResponseEntity.ok(boardQueryService.findByCondition(findBoardCondition, pageable));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> findDetail(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardQueryService.findDetail(boardId));
    }

    @PostMapping
    @Authenticated
    public ResponseEntity<?> registerBoard(@RequestBody BoardForm boardForm, @JwtClaim("info.id") Long memberId) {
        boardService.registerBoard(boardForm, memberId);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
