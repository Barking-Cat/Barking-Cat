package PetShop.BarkingCat.domain.board;

import PetShop.BarkingCat.domain.board.dto.AdoptRequestForm;
import PetShop.BarkingCat.domain.board.service.AdoptRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adopt")
public class AdoptRequestController {

    private final AdoptRequestService adoptRequestService;

    public AdoptRequestController(AdoptRequestService adoptRequestService) {
        this.adoptRequestService = adoptRequestService;
    }

    @PostMapping
    public ResponseEntity<?> registerAdoptRequest(@RequestBody AdoptRequestForm adoptRequestForm) {
        Long adoptRequestId = adoptRequestService.registerAdoptRequest(adoptRequestForm);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adoptRequestId);
    }
}
