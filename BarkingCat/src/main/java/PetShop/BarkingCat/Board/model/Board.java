package PetShop.BarkingCat.Board.model;

import PetShop.BarkingCat.base.AnimalType;
import PetShop.BarkingCat.base.Base;
import PetShop.BarkingCat.base.Region;
import PetShop.BarkingCat.base.Sex;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Board extends Base {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Category category;

    private Long memberId;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private BigDecimal price;

    private LocalDateTime dueDate;

    @Embedded
    private Photos photos;

    public Board(Category category, Long memberId, String title, String content, Region region, AnimalType animalType, Sex sex, BigDecimal price) {
        this.category = category;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.region = region;
        this.animalType = animalType;
        this.sex = sex;
        this.price = price;
    }
}
