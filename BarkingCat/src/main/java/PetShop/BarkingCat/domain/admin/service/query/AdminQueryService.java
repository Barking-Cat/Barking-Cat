package PetShop.BarkingCat.domain.admin.service.query;

import PetShop.BarkingCat.domain.admin.repository.query.AdminQueryRepository;
import PetShop.BarkingCat.domain.admin.dto.ReportBoardDetailResponse;
import PetShop.BarkingCat.domain.admin.dto.ReportBoardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminQueryService {

    private final AdminQueryRepository adminQueryRepository;

    public AdminQueryService(AdminQueryRepository adminQueryRepository) {
        this.adminQueryRepository = adminQueryRepository;
    }

    public Page<ReportBoardResponse> findReportBoard(Pageable pageable){
        return adminQueryRepository.findReportBoard(pageable);
    }

    public ReportBoardDetailResponse findDetailReportBoard(Long boardReportId){
        return adminQueryRepository.findDetailReportBoard(boardReportId);
    }
}
