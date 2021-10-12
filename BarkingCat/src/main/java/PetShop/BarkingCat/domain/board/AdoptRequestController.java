package PetShop.BarkingCat.domain.board;

import PetShop.BarkingCat.domain.board.dto.AdoptRequestForm;
import PetShop.BarkingCat.domain.board.service.AdoptRequestService;
import PetShop.BarkingCat.domain.board.service.query.AdoptRequestQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adopt")
public class AdoptRequestController {

    private final AdoptRequestService adoptRequestService;
    private final AdoptRequestQueryService adoptRequestQueryService;

    public AdoptRequestController(AdoptRequestService adoptRequestService, AdoptRequestQueryService adoptRequestQueryService) {
        this.adoptRequestService = adoptRequestService;
        this.adoptRequestQueryService = adoptRequestQueryService;
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<?> findByBoardId(@PathVariable Long boardId, Pageable pageable) {
        return ResponseEntity.ok(adoptRequestQueryService.findByBoardId(boardId, pageable));
    }

    @PostMapping
    public ResponseEntity<?> registerAdoptRequest(@RequestBody AdoptRequestForm adoptRequestForm) {
        Long adoptRequestId = adoptRequestService.registerAdoptRequest(adoptRequestForm);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adoptRequestId);
    }
}
