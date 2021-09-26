package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.common.base.model.constants.AnimalType;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.common.base.model.constants.Sex;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FindBoardCondition {
    private final AnimalType animalType;
    private final Region region;
    private final Integer age;
    private final Sex sex;
    private final BigDecimal price;
}
