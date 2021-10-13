package PetShop.BarkingCat.domain.board.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdoptRequestResponse {
    private final Long id;
    private final LocalDateTime createDateTime;
    private final String writerName;

    public AdoptRequestResponse(Long id, LocalDateTime createDateTime, String writerName) {
        this.id = id;
        this.createDateTime = createDateTime;
        this.writerName = writerName;
    }
}
