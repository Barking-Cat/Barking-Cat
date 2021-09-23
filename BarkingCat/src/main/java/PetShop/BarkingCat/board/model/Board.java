package PetShop.BarkingCat.board.model;

import PetShop.BarkingCat.base.infra.Money;
import PetShop.BarkingCat.base.model.Base;
import PetShop.BarkingCat.base.model.constants.AnimalType;
import PetShop.BarkingCat.base.model.constants.Region;
import PetShop.BarkingCat.base.model.constants.Sex;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Board extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private Long memberId;

    private Title title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Money price;

    private LocalDateTime dueDate;

    public Board() {

    }

    @Builder
    public Board(Long id, Category category, Long memberId, Title title, String content, Region region, AnimalType animalType, Sex sex, Money price, LocalDateTime dueDate) {
        this.id = id;
        this.category = category;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.region = region;
        this.animalType = animalType;
        this.sex = sex;
        this.price = price;
        this.dueDate = dueDate;
    }
}
