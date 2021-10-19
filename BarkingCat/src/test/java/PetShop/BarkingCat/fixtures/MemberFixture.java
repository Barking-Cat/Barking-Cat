package PetShop.BarkingCat.fixtures;

import PetShop.BarkingCat.domain.member.member_temp.model.MemberTemp;
import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.model.objects.Email;

public class MemberFixture {
    public static MemberTemp.MemberTempBuilder aMemberTemp() {
        return MemberTemp.builder()
                .id(1L)
                .email(new Email("test@test.com"))
                .password("1q2w3e4r")
                .phone("01000001111")
                .memberType(Member.MemberType.NORMAL)
                .name("TESTER");
    }

    public static Member.MemberBuilder aMember() {
        return Member.builder()
                .id(1L)
                .email(new Email("test@test.com"))
                .password("1q2w3e4r")
                .phone("01000001111")
                .memberType(Member.MemberType.NORMAL)
                .name("TESTER");
    }
}
