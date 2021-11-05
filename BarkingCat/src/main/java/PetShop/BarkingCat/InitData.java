package PetShop.BarkingCat;

import PetShop.BarkingCat.common.base.model.Residence;
import PetShop.BarkingCat.common.base.model.constants.*;
import PetShop.BarkingCat.domain.board.model.*;
import PetShop.BarkingCat.domain.board.model.objects.Money;
import PetShop.BarkingCat.domain.board.model.objects.TagContent;
import PetShop.BarkingCat.domain.board.model.objects.Title;
import PetShop.BarkingCat.domain.member.model.Member;
import PetShop.BarkingCat.domain.member.model.objects.Email;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        private final PasswordEncoder passwordEncoder;

        InitDataService(PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
        }

        @Transactional
        public void init() {
            Member member = Member.builder()
                    .email(new Email("test@naver.com"))
                    .password(passwordEncoder.encode("1q2w3e4r"))
                    .name("Tester")
                    .phone("010-0000-1111")
                    .memberType(Member.MemberType.NORMAL)
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
                    .build();

            em.persist(board);

            Tag tagContents = Tag.builder()
                    .tagContent(new TagContent("tag1"))
                    .board(board)
                    .build();

            em.persist(tagContents);

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
                    .petCount(3)
                    .adoptReason("Test Reason")
                    .region(Region.SEOUL)
                    .build();

            em.persist(adoptRequest);

            BoardReport boardReport = BoardReport.builder()
                    .board(board)
                    .reporterId(1L)
                    .reportCategory(ReportCategory.ETC)
                    .detail("test BoardReport")
                    .build();

            em.persist(boardReport);
        }
    }
}
