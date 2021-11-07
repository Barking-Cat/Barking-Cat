package PetShop.BarkingCat.domain.admin;

import PetShop.BarkingCat.domain.admin.service.query.AdminQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminQueryService adminQueryService;

    public AdminController(AdminQueryService adminQueryService) {
        this.adminQueryService = adminQueryService;
    }

    @GetMapping("/report")
    public ResponseEntity<?> findReportBoard(Pageable pageable){
        return ResponseEntity.ok(adminQueryService.findReportBoard(pageable));
    }

    @GetMapping("/report/{boardReportId}")
    public ResponseEntity<?> findDetailReportBoard(@PathVariable Long boardReportId){
        return ResponseEntity.ok(adminQueryService.findDetailReportBoard(boardReportId));
    }
}
