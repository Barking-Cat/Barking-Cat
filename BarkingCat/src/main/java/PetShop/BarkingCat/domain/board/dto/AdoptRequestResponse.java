package PetShop.BarkingCat.domain.board.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdoptRequestResponse {
    private Long id;
    private LocalDateTime createDateTime;

    public AdoptRequestResponse(Long id, LocalDateTime createDateTime) {
        this.id = id;
        this.createDateTime = createDateTime;
    }
}
