package PetShop.BarkingCat.domain.board;

import PetShop.BarkingCat.common.security.annotations.Authenticated;
import PetShop.BarkingCat.common.security.annotations.JwtClaim;
import PetShop.BarkingCat.domain.board.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@Authenticated
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<?> like(@RequestBody Long boardId, @JwtClaim("info.id") Long memberId) {
        likeService.like(boardId, memberId);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> unlike(@RequestBody Long likeId, @JwtClaim("info.id") Long memberId) {
        likeService.unlike(likeId, memberId);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
