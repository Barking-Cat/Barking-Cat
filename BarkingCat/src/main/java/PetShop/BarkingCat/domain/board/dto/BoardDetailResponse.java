package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.common.base.infra.Money;
import PetShop.BarkingCat.common.base.model.constants.AnimalType;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.common.base.model.constants.Sex;
import PetShop.BarkingCat.domain.board.model.objects.Tag;
import PetShop.BarkingCat.domain.board.model.objects.Tags;
import PetShop.BarkingCat.domain.board.model.objects.Title;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardDetailResponse {
    private final Long boardId;
    private final Long categoryId;
    private final Long memberId;
    private final Title title;
    private final String content;
    private final Region region;
    private final AnimalType animalType;
    private final Sex sex;
    private final Integer age;
    private final Money price;
    private final LocalDateTime dueDate;
    private final Tags tags;
    private final String memberEmail;
    private final String memberPhone;
    private final String memberName;
    private final Long hits;

    public BoardDetailResponse(Long boardId, Long categoryId, Long memberId, Title title, String content, Region region, AnimalType animalType, Sex sex, Integer age, Money price, LocalDateTime dueDate, Tags tags, Long hits, String memberEmail, String memberPhone, String memberName) {
        this.boardId = boardId;
        this.categoryId = categoryId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.region = region;
        this.animalType = animalType;
        this.sex = sex;
        this.age = age;
        this.price = price;
        this.dueDate = dueDate;
        this.tags = tags;
        this.hits = hits;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberName = memberName;
    }

    public String getTitle() {
        return title.title();
    }

    public BigDecimal getPrice() {
        return price.amount();
    }

    public List<String> getTags() {
        return tags.content()
                .stream()
                .map(Tag::content)
                .collect(Collectors.toList());
    }
}
