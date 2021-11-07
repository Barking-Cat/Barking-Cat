package PetShop.BarkingCat.domain.admin.dto;

import PetShop.BarkingCat.common.base.model.constants.ReportCategory;
import lombok.Getter;

@Getter
public class ReportBoardDetailResponse {

    private final Long id;
    private final Long boardId;
    private final Long reporterId;
    private final ReportCategory reportCategory;
    private final String detail;

    public ReportBoardDetailResponse(Long id, Long boardId, Long reporterId, ReportCategory reportCategory, String detail) {
        this.id = id;
        this.boardId = boardId;
        this.reporterId = reporterId;
        this.reportCategory = reportCategory;
        this.detail = detail;
    }

    public String getReportCategory(){return reportCategory.label();}
}
