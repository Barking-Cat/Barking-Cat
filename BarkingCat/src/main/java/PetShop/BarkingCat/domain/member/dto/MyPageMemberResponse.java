package PetShop.BarkingCat.domain.member.dto;
import PetShop.BarkingCat.domain.member.model.objects.Email;
import lombok.Getter;

@Getter
public class MyPageMemberResponse {

    private String name;
    private Email email;
    private String phone;

    public MyPageMemberResponse(String name, Email email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getEmail(){
        return email.content();
    }
}
