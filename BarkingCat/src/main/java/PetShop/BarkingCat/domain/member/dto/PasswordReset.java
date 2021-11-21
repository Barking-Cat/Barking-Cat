package PetShop.BarkingCat.domain.member.dto;

import PetShop.BarkingCat.domain.member.model.objects.Email;
import lombok.Data;

@Data
public class PasswordReset {
    private String email;
    private String password;

    public Email getEmail() {
        return new Email(email);
    }
}
