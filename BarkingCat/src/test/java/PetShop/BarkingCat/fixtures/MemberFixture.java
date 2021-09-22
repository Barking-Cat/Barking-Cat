package PetShop.BarkingCat.fixtures;

import PetShop.BarkingCat.member_temp.model.NormalMemberTemp;

public class MemberFixture {
    public static NormalMemberTemp aNormalMemberTemp() {
        return new NormalMemberTemp("test@naver.com", "1q2w3e4r", "010-0000-1111", "tester");
    }
}
