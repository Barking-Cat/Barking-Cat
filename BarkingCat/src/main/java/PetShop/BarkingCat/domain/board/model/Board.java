package PetShop.BarkingCat.domain.board.model;

import PetShop.BarkingCat.domain.board.model.event.BoardRegisterEvent;
import PetShop.BarkingCat.domain.board.model.objects.Money;
import PetShop.BarkingCat.common.base.model.Base;
import PetShop.BarkingCat.common.base.model.constants.AnimalType;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.common.base.model.constants.Sex;
import PetShop.BarkingCat.domain.board.model.objects.Title;
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

    private Integer age;

    private Money price;

    private LocalDateTime dueDate;

    private Long hits;

    public Board() {

    }

    @Builder
    public Board(Long id, Category category, Long memberId, Title title, String content, Region region, AnimalType animalType, Sex sex, Integer age, Money price, LocalDateTime dueDate) {
        this.id = id;
        this.category = category;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.region = region;
        this.animalType = animalType;
        this.sex = sex;
        this.age = age;
        this.price = price;
        this.dueDate = dueDate;
        this.hits = 0L;
    }

    public Board mapCategory(Category category) {
        this.category = category;
        return this;
    }

    public Board mapWriter(Long memberId) {
        this.memberId = memberId;
        return this;
    }

    public Long id() {
        return this.id;
    }

    public Long writerId() {
        return this.memberId;
    }

    public void hit() {
        hits += 1;
    }

    public boolean writerIsNotEqual(Long memberId) {
        return !this.memberId.equals(memberId);
    }

    public void registerEvent() {
        registerEvent(new BoardRegisterEvent(this));
    }
}
