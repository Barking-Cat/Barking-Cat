package PetShop.BarkingCat.domain.board;

import PetShop.BarkingCat.common.security.annotations.Authenticated;
import PetShop.BarkingCat.common.security.annotations.JwtClaim;
import PetShop.BarkingCat.domain.board.dto.CommentForm;
import PetShop.BarkingCat.domain.board.service.CommentService;
import PetShop.BarkingCat.domain.board.service.query.CommentQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final CommentQueryService commentQueryService;

    public CommentController(CommentService commentService, CommentQueryService commentQueryService) {
        this.commentService = commentService;
        this.commentQueryService = commentQueryService;
    }

    @PostMapping("/board/{boardId}")
    @Authenticated
    public ResponseEntity<?> registerComment(@RequestBody CommentForm commentForm, @JwtClaim("info.id") Long memberId, @PathVariable Long boardId) {

        commentService.registerComment(commentForm, memberId, boardId);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<?> findComment(@PathVariable Long boardId) {
        return ResponseEntity.ok(commentQueryService.findAllByBoardId(boardId));
    }

    @PutMapping("/board/{commentId}/delete")
    @Authenticated
    public ResponseEntity<?> deleteComment(@JwtClaim("info.id") Long memberId, @PathVariable Long commentId){

        commentService.deleteComment(memberId, commentId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }

    @PutMapping("/board/{commentId}")
    public ResponseEntity<?> updateComment(@RequestBody CommentForm commentForm, @JwtClaim("info.id") Long memberId, @PathVariable Long commentId){

        commentService.updateComment(commentForm, memberId, commentId);

        return ResponseEntity.ok(HttpStatus.CREATED);

    }



}
