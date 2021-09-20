package PetShop.BarkingCat.member_temp;

import PetShop.BarkingCat.config.JpaConfig;
import PetShop.BarkingCat.member.model.NormalMember;
import PetShop.BarkingCat.member.model.QNormalMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(JpaConfig.class)
public class BaseTest {

    @Autowired
    private JPAQueryFactory query;

    @Test
    public void queryTest() {
        List<NormalMember> members = query.selectFrom(QNormalMember.normalMember)
                .fetch();
    }
}
