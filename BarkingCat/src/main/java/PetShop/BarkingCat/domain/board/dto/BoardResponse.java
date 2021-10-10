package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.common.base.model.constants.AnimalType;
import PetShop.BarkingCat.common.base.model.constants.Region;
import PetShop.BarkingCat.domain.board.model.objects.Tag;
import PetShop.BarkingCat.domain.board.model.objects.Tags;
import PetShop.BarkingCat.domain.board.model.objects.Title;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResponse {
    private final Long boardId;
    private final Long categoryId;
    private final Long memberId;
    private final Title title;
    private final Region region;
    private final AnimalType animalType;
    private final Tags tags;
    private final Long hits;
    private final Long likes;
    private final LocalDateTime createDateTime;
    private final String memberName;

    public BoardResponse(Long boardId, Long categoryId, Long memberId, Title title, Region region, AnimalType animalType, Tags tags, Long hits, Long likes, LocalDateTime createDateTime, String memberName) {
        this.boardId = boardId;
        this.categoryId = categoryId;
        this.memberId = memberId;
        this.title = title;
        this.region = region;
        this.animalType = animalType;
        this.tags = tags;
        this.hits = hits;
        this.likes = likes;
        this.createDateTime = createDateTime;
        this.memberName = memberName;
    }

    public String getTitle() {
        return title.content();
    }

    public List<String> getTags() {
        return tags.content()
                .stream()
                .map(Tag::content)
                .collect(Collectors.toList());
    }
}
