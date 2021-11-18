package PetShop.BarkingCat.domain.member_temp;

import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.member_temp.model.MemberTemp;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static PetShop.BarkingCat.fixtures.MemberFixture.aMemberTemp;

class MemberTempTest {

    @Test
    @DisplayName("임시저장된 NormalMember로 진짜 NormalMember 만들기 성공")
    void createNormalMemberWithNormalMemberTemp() {
        //given
        MemberTemp memberTemp = aMemberTemp().build();

        //when
        Member member = memberTemp.createMember();

        //then
    }
}
