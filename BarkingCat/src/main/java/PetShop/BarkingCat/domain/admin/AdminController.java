package PetShop.BarkingCat.domain.admin;

import PetShop.BarkingCat.domain.admin.service.AdminService;
import PetShop.BarkingCat.domain.admin.service.query.AdminQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminQueryService adminQueryService;
    private final AdminService adminService;

    public AdminController(AdminQueryService adminQueryService, AdminService adminService) {
        this.adminQueryService = adminQueryService;
        this.adminService = adminService;
    }

    @GetMapping("/report")
    public ResponseEntity<?> findReportBoard(Pageable pageable) {
        return ResponseEntity.ok(adminQueryService.findReportBoard(pageable));
    }

    @GetMapping("/report/{boardReportId}")
    public ResponseEntity<?> findDetailReportBoard(@PathVariable Long boardReportId) {
        return ResponseEntity.ok(adminQueryService.findDetailReportBoard(boardReportId));
    }

    @PutMapping("/report/{boardId}/delete")
    public ResponseEntity<?> deleteReportedBoard(@PathVariable Long boardId) {
        adminService.deleteReportedBoard(boardId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }
}
