package PetShop.BarkingCat.domain.board;

import PetShop.BarkingCat.common.security.annotations.Authenticated;
import PetShop.BarkingCat.common.security.annotations.JwtClaim;
import PetShop.BarkingCat.domain.board.service.LikeService;
import PetShop.BarkingCat.domain.board.service.query.LikeQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@Authenticated
public class LikeController {

    private final LikeService likeService;
    private final LikeQueryService likeQueryService;

    public LikeController(LikeService likeService, LikeQueryService likeQueryService) {
        this.likeService = likeService;
        this.likeQueryService = likeQueryService;
    }

    @GetMapping("/board")
    public ResponseEntity<?> isMemberLikedTheBoard(@RequestParam Long boardId, @JwtClaim("info.id") Long memberId) {
        boolean memberLikedTheBoard = likeQueryService.isMemberLikedTheBoard(boardId, memberId);
        return ResponseEntity.ok(memberLikedTheBoard);
    }

    @PostMapping("/board")
    public ResponseEntity<?> like(@RequestParam Long boardId, @JwtClaim("info.id") Long memberId) {
        likeService.like(boardId, memberId);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/board")
    public ResponseEntity<?> unlike(@RequestParam Long boardId, @JwtClaim("info.id") Long memberId) {
        likeService.unlike(boardId, memberId);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
