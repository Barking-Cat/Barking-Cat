package PetShop.BarkingCat.domain.member.dto;

import PetShop.BarkingCat.domain.member.model.objects.Email;
import lombok.Data;

@Data
public class PhoneAuthForPassword {
    private String email;
    private String phoneNumber;

    public Email getEmail() {
        return new Email(email);
    }
}
