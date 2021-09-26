package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.common.base.infra.Money;
import PetShop.BarkingCat.common.base.model.constants.AnimalType;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.common.base.model.constants.Sex;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.Title;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardForm {
    private Long categoryId;

    private Long memberId;

    private String title;

    private String content;

    private Region region;

    private AnimalType animalType;

    private Sex sex;

    private int price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

    public Board entity() {
        return Board.builder()
                .memberId(memberId)
                .title(new Title(title))
                .content(content)
                .region(region)
                .animalType(animalType)
                .sex(sex)
                .price(Money.wons(price))
                .dueDate(dueDate)
                .build();
    }
}
