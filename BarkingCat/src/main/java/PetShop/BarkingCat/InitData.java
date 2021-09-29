package PetShop.BarkingCat;

import PetShop.BarkingCat.common.base.infra.Money;
import PetShop.BarkingCat.domain.board.model.*;
import PetShop.BarkingCat.common.base.model.Residence;
import PetShop.BarkingCat.common.base.model.constants.AnimalType;
import PetShop.BarkingCat.common.base.model.constants.Earning;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.common.base.model.constants.Sex;
import PetShop.BarkingCat.domain.board.model.objects.Tags;
import PetShop.BarkingCat.domain.board.model.objects.Title;
import PetShop.BarkingCat.domain.member.dto.MemberForm;
import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class InitData {
    private final InitDataService initDataService;

    public InitData(InitDataService initDataService) {
        this.initDataService = initDataService;
    }

    @PostConstruct
    public void init() {
        initDataService.init();
    }

    @Component
    static class InitDataService {
        @PersistenceContext
        EntityManager em;

        @Autowired private final MemberService memberService;

        public InitDataService(MemberService memberService) {
            this.memberService = memberService;
        }

        @Transactional
        public void init() {

            MemberForm memberForm = new MemberForm();
            memberForm.setEmail("test@naver.com");
            memberForm.setPassword("1q2w3e4r");
            memberForm.setPhone("010-0000-0000");
            memberForm.setMemberType(Member.MemberType.NORMAL);
            memberForm.setName("Tester");

            memberService.joinMember(memberForm);

            Category category = new Category(null, "Test Category");
            em.persist(category);

            Board board = Board.builder()
                    .memberId(1L)
                    .category(category)
                    .title(new Title("TEST TITLE"))
                    .content("TEST CONTENT")
                    .region(Region.SEOUL)
                    .animalType(AnimalType.DOG)
                    .sex(Sex.MALE)
                    .age(3)
                    .price(Money.wons(50_000))
                    .dueDate(LocalDateTime.of(LocalDate.of(2021, 10, 1), LocalTime.of(0, 0, 0)))
                    .tags(new Tags("tag1 | tag2 | tag3"))
                    .build();

            em.persist(board);

            Comment comment = Comment.builder()
                    .memberId(1L)
                    .board(board)
                    .content("Test Comment")
                    .build();

            em.persist(comment);

            AdoptRequest adoptRequest = AdoptRequest.builder()
                    .board(board)
                    .writerId(1L)
                    .earning(Earning.FOUR_HUNDRED)
                    .residence(new Residence(Residence.ResidenceType.OWNER, 40))
                    .roommateNumber(3)
                    .petExist(true)
                    .adoptReason("Test Reason")
                    .region(Region.SEOUL)
                    .build();

            em.persist(adoptRequest);
        }
    }
}
