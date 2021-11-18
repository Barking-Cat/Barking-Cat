package PetShop.BarkingCat.domain.member.member_temp.model;

import PetShop.BarkingCat.common.base.model.Base;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class PhoneAuth extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_auth_id")
    private Long id;

    private String phoneNumber;

    private String authCode;

    private boolean certified;

    @Builder
    public PhoneAuth(Long id, String phoneNumber, String authCode, boolean certified) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.authCode = authCode;
        this.certified = certified;
    }

    public void certified() {
        this.certified = true;
    }
}
