package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.common.base.model.Residence;
import PetShop.BarkingCat.common.base.model.constants.Earning;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.domain.member.model.objects.Email;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyAdoptRequestResponseDetail {
    private final Long id;
    private final Long boardId;
    private final Earning earning;
    private final Residence.ResidenceType residenceType;
    private final Integer area;
    private final Integer roommateNumber;
    private final Integer petCount;
    private final String adoptReason;
    private final Region region;
    private final LocalDateTime createDateTime;
    private final String writerName;
    private final Email writerEmail;
    private final String writerPhone;

    public MyAdoptRequestResponseDetail(Long id, Long boardId, Earning earning, Residence.ResidenceType residenceType, Integer area, Integer roommateNumber, Integer petCount, String adoptReason, Region region, LocalDateTime createDateTime, String writerName, Email writerEmail, String writerPhone) {
        this.id = id;
        this.boardId = boardId;
        this.earning = earning;
        this.residenceType = residenceType;
        this.area = area;
        this.roommateNumber = roommateNumber;
        this.petCount = petCount;
        this.adoptReason = adoptReason;
        this.region = region;
        this.createDateTime = createDateTime;
        this.writerName = writerName;
        this.writerEmail = writerEmail;
        this.writerPhone = writerPhone;
    }

    public String getWriterEmail() {
        return writerEmail.content();
    }
}
