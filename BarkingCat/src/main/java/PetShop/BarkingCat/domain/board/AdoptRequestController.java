package PetShop.BarkingCat.domain.board;

import PetShop.BarkingCat.common.security.annotations.Authenticated;
import PetShop.BarkingCat.common.security.annotations.JwtClaim;
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

    @GetMapping
    @Authenticated
    public ResponseEntity<?> findMyRequests(@JwtClaim("info.id") Long memberId, Pageable pageable) {
        return ResponseEntity.ok(adoptRequestQueryService.findByRequests(memberId, pageable));
    }

    @GetMapping("/request/{adoptRequestId}")
    @Authenticated
    public ResponseEntity<?> findMyRequestDetail(@JwtClaim("info.id") Long memberId, @PathVariable Long adoptRequestId) {
        return ResponseEntity.ok(adoptRequestQueryService.findDetail(memberId, adoptRequestId));
    }

    @GetMapping("/board/{boardId}")
    @Authenticated
    public ResponseEntity<?> findByBoardId(@JwtClaim("info.id") Long memberId, @PathVariable Long boardId, Pageable pageable) {
        return ResponseEntity.ok(adoptRequestQueryService.findByBoardId(memberId, boardId, pageable));
    }

    @GetMapping("/board/{boardId}/request/{adoptRequestId}")
    @Authenticated
    public ResponseEntity<?> findDetail(@JwtClaim("info.id") Long memberId, @PathVariable Long boardId, @PathVariable Long adoptRequestId) {
        return ResponseEntity.ok(adoptRequestQueryService.findDetail(memberId, boardId, adoptRequestId));
    }

    @PostMapping
    @Authenticated
    public ResponseEntity<?> registerAdoptRequest(@JwtClaim("info.id") Long memberId, @RequestBody AdoptRequestForm adoptRequestForm) {
        Long adoptRequestId = adoptRequestService.registerAdoptRequest(memberId, adoptRequestForm);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adoptRequestId);
    }

    @PutMapping("/request/{adoptRequestId}/progress")
    @Authenticated
    public ResponseEntity<?> progressAdoptRequest(@JwtClaim("info.id") Long memberId, @PathVariable Long adoptRequestId) {
        adoptRequestService.progressAdoptRequest(memberId, adoptRequestId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

    @PutMapping("/request/{adoptRequestId}/cancel")
    @Authenticated
    public ResponseEntity<?> cancelAdoptRequest(@JwtClaim("info.id") Long memberId, @PathVariable Long adoptRequestId) {
        adoptRequestService.cancelAdoptRequest(memberId, adoptRequestId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }
}
