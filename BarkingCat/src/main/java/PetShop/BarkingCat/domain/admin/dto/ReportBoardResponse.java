package PetShop.BarkingCat.domain.admin.dto;

import PetShop.BarkingCat.common.base.model.constants.ReportCategory;
import PetShop.BarkingCat.domain.board.model.objects.Title;
import lombok.Getter;

@Getter
public class ReportBoardResponse {

    private ReportCategory reportCategory;
    private Title title;

    public ReportBoardResponse(ReportCategory reportCategory, Title title) {
        this.reportCategory = reportCategory;
        this.title = title;
    }

    public String getReportCategory(){return reportCategory.label();}
    public String getTitle(){return title.content();}
}
