package PetShop.BarkingCat.common.sns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Message {
    private String phoneNumber;
    private String message;

    public String getPhoneNumber() {
        return "+82" + phoneNumber;
    }
}
