package PetShop.BarkingCat;

import PetShop.BarkingCat.common.base.infra.Money;
import PetShop.BarkingCat.common.base.model.Residence;
import PetShop.BarkingCat.common.base.model.constants.AnimalType;
import PetShop.BarkingCat.common.base.model.constants.Earning;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.common.base.model.constants.Sex;
import PetShop.BarkingCat.common.security.Password;
import PetShop.BarkingCat.domain.board.model.AdoptRequest;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.Category;
import PetShop.BarkingCat.domain.board.model.Comment;
import PetShop.BarkingCat.domain.board.model.objects.Tags;
import PetShop.BarkingCat.domain.board.model.objects.Title;
import PetShop.BarkingCat.domain.member.model.Member;
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

        @Transactional
        public void init() {
            Member member = Member.builder()
                    .email("test@naver.com")
                    .password(new Password("1q2w3e4r").content())
                    .phone("010-0000-0000")
                    .memberType(Member.MemberType.NORMAL)
                    .name("Tester")
                    .build();

            em.persist(member);

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
