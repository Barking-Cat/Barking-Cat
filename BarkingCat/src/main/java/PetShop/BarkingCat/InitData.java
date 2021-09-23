package PetShop.BarkingCat;

import PetShop.BarkingCat.base.infra.Money;
import PetShop.BarkingCat.base.infra.Title;
import PetShop.BarkingCat.base.model.Residence;
import PetShop.BarkingCat.base.model.constants.AnimalType;
import PetShop.BarkingCat.base.model.constants.Earning;
import PetShop.BarkingCat.base.model.constants.Region;
import PetShop.BarkingCat.base.model.constants.Sex;
import PetShop.BarkingCat.board.model.Board;
import PetShop.BarkingCat.board.model.Category;
import PetShop.BarkingCat.board.model.Comment;
import PetShop.BarkingCat.board.model.RequestForm;
import PetShop.BarkingCat.member.model.Company;
import PetShop.BarkingCat.member.model.NormalMember;
import PetShop.BarkingCat.member.model.Shelter;
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
            NormalMember normalMember = NormalMember.builder()
                    .email("test@naver.com")
                    .password("1q2w3e4r")
                    .phone("010-0000-0000")
                    .name("Tester")
                    .build();

            em.persist(normalMember);

            Company company = Company.builder()
                    .email("company@gmail.com")
                    .password("1q2w3e4r")
                    .phone("010-1111-1111")
                    .businessName("Company Tester")
                    .businessNumber("139WE9HJD0-E13")
                    .build();

            em.persist(company);

            Shelter shelter = Shelter.builder()
                    .email("shelter@nate.com")
                    .password("1q2w3e4r")
                    .phone("010-2222-2222")
                    .businessName("Shelter Tester")
                    .shelterNumber("1R93USAF-11F3913T")
                    .build();

            em.persist(shelter);

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
                    .price(Money.wons(50_000))
                    .dueDate(LocalDateTime.of(LocalDate.of(2021, 10, 1), LocalTime.of(0, 0, 0)))
                    .build();

            em.persist(board);

            Comment comment = Comment.builder()
                    .memberId(1L)
                    .board(board)
                    .content("Test Comment")
                    .build();

            em.persist(comment);

            RequestForm requestForm = RequestForm.builder()
                    .board(board)
                    .earning(Earning.FOUR_HUNDRED)
                    .residence(new Residence(Residence.ResidenceType.OWNER, 40))
                    .roommateNumber(3)
                    .petExist(true)
                    .adoptReason("Test Reason")
                    .region(Region.SEOUL)
                    .build();

            em.persist(requestForm);
        }
    }
}
