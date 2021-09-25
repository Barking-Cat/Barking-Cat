package PetShop.BarkingCat.domain.member_temp.model;

import PetShop.BarkingCat.domain.member.model.NormalMember;
import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class NormalMemberTemp extends MemberTemp {

    private String name;

    protected NormalMemberTemp() {
    }

    @Builder
    public NormalMemberTemp(Long id, String email, String password, String phone, String name) {
        super(id, email, password, phone);
        this.name = name;
    }

    public NormalMember createNormalMember() {
        return super.buildNormalMember()
                .name(name)
                .build();
    }
}
