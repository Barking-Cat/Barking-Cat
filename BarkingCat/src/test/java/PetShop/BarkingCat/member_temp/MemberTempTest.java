package PetShop.BarkingCat.member_temp;

import PetShop.BarkingCat.member.model.NormalMember;
import PetShop.BarkingCat.member_temp.model.NormalMemberTemp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static PetShop.BarkingCat.fixtures.MemberFixture.aNormalMemberTemp;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTempTest {

    @Test
    @DisplayName("임시저장된 NormalMember로 진짜 NormalMember 만들기 성공")
    void createNormalMemberWithNormalMemberTemp() {
        //given
        NormalMemberTemp normalMemberTemp = aNormalMemberTemp().build();

        //when
        NormalMember normalMember = normalMemberTemp.createNormalMember();

        //then
    }

}
