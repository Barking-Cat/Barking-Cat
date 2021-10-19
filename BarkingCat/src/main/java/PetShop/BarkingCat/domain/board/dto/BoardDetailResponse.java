package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.common.base.model.constants.AnimalType;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.common.base.model.constants.Sex;
import PetShop.BarkingCat.domain.board.model.objects.Money;
import PetShop.BarkingCat.domain.board.model.objects.Title;
import PetShop.BarkingCat.domain.member.model.objects.Email;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    private final Long hits;
    private final Long likes;
    private final LocalDateTime createDateTime;
    private final Email memberEmail;
    private final String memberPhone;
    private final String memberName;
    private final List<TagResponse> tagContents;

    public BoardDetailResponse(Long boardId, Long categoryId, Long memberId, Title title, String content, Region region, AnimalType animalType, Sex sex, Integer age, Money price, LocalDateTime dueDate, Long hits, Long likes, LocalDateTime createDateTime, Email memberEmail, String memberPhone, String memberName, List<TagResponse> tagContents) {
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
        this.hits = hits;
        this.likes = likes;
        this.createDateTime = createDateTime;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberName = memberName;
        this.tagContents = tagContents;
    }

    public String getTitle() {
        return title.content();
    }

    public BigDecimal getPrice() {
        return price.amount();
    }

    public String getMemberEmail() {
        return memberEmail.content();
    }
}
