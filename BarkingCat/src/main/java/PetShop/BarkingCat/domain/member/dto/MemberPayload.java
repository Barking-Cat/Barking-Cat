package PetShop.BarkingCat.domain.member.dto;

import lombok.Data;

@Data
public class MemberPayload {
    private Long id;
    private String email;

    public MemberPayload() {
    }

    public MemberPayload(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
