package PetShop.BarkingCat.domain.board;

import PetShop.BarkingCat.common.security.annotations.Authenticated;
import PetShop.BarkingCat.common.security.annotations.JwtClaim;
import PetShop.BarkingCat.domain.board.dto.CommentForm;
import PetShop.BarkingCat.domain.board.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Authenticated
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/board/{boardId}")
    public ResponseEntity<?> registerComment(@RequestBody CommentForm commentForm, @JwtClaim("info.id") Long memberId, @PathVariable Long boardId){

        commentService.registerComment(commentForm, memberId, boardId);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
