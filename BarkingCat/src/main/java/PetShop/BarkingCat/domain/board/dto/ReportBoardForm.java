package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.domain.board.model.BoardReport;
import PetShop.BarkingCat.common.base.model.constants.ReportCategory;
import lombok.Getter;

@Getter
public class ReportBoardForm {
    private Long boardId;
    private ReportCategory reportCategory;
    private String detail;

    public BoardReport entity() {
        return BoardReport.builder()
                .reportCategory(reportCategory)
                .detail(detail)
                .build();
    }
}
