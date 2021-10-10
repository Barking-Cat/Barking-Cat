package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.common.base.model.Residence;
import PetShop.BarkingCat.common.base.model.constants.Earning;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.domain.board.model.AdoptRequest;
import lombok.Data;

@Data
public class AdoptRequestForm {
    private Long boardId;

    private Long writerId;

    private Earning earning;

    private Residence.ResidenceType residenceType;

    private int area;

    private int roommateNumber;

    private boolean petExist;

    private String adoptReason;

    private Region region;

    public AdoptRequest entity() {
        return AdoptRequest.builder()
                .writerId(writerId)
                .earning(earning)
                .residence(new Residence(residenceType, area))
                .roommateNumber(roommateNumber)
                .petExist(petExist)
                .adoptReason(adoptReason)
                .region(region)
                .build();
    }
}
