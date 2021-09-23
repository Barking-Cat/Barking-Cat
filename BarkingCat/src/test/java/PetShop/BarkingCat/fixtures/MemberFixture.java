package PetShop.BarkingCat.fixtures;

import PetShop.BarkingCat.member_temp.model.NormalMemberTemp;

public class MemberFixture {
    public static NormalMemberTemp.NormalMemberTempBuilder aNormalMemberTemp() {
        return NormalMemberTemp.builder()
                .id(1L)
                .email("test@test.com")
                .password("1q2w3e4r")
                .phone("01000001111")
                .name("TESTER");
    }
}
