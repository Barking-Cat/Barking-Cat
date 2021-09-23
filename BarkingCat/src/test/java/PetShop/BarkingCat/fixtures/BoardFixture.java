package PetShop.BarkingCat.fixtures;

import PetShop.BarkingCat.base.infra.Money;
import PetShop.BarkingCat.board.model.Title;
import PetShop.BarkingCat.base.model.constants.AnimalType;
import PetShop.BarkingCat.base.model.constants.Region;
import PetShop.BarkingCat.base.model.constants.Sex;
import PetShop.BarkingCat.board.model.Board;
import PetShop.BarkingCat.board.model.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BoardFixture {
    public static Board.BoardBuilder aBoard() {
        return Board.builder()
                .id(1L)
                .memberId(1L)
                .category(new Category(1L, "TEST CATEGORY"))
                .title(new Title("TEST TITLE"))
                .content("TEST CONTENT")
                .region(Region.SEOUL)
                .animalType(AnimalType.DOG)
                .sex(Sex.MALE)
                .price(Money.wons(50_000))
                .dueDate(LocalDateTime.of(LocalDate.of(2021, 10, 1), LocalTime.of(0, 0, 0)));
    }
}
