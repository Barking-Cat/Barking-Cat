package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.domain.board.model.objects.Money;
import PetShop.BarkingCat.common.base.model.constants.AnimalType;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.common.base.model.constants.Sex;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.objects.Title;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardForm {
    private Long categoryId;

    private String title;

    private String content;

    private Region region;

    private AnimalType animalType;

    private Sex sex;

    private Integer age;

    private Integer price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

    public Board entity() {
        return Board.builder()
                .title(new Title(title))
                .content(content)
                .region(region)
                .animalType(animalType)
                .sex(sex)
                .age(age)
                .price(Money.wons(price))
                .dueDate(dueDate)
                .build();
    }
}
