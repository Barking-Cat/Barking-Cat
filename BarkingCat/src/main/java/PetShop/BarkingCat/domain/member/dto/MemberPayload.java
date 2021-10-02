package PetShop.BarkingCat.domain.member.dto;

import lombok.Data;

@Data
public class MemberPayload {
    private final Long id;
    private final String email;

    public MemberPayload(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
