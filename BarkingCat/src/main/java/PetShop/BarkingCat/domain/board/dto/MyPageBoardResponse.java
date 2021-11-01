package PetShop.BarkingCat.domain.board.dto;

import PetShop.BarkingCat.domain.board.model.objects.Title;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyPageBoardResponse {
    private Title title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createDateTime;

    public MyPageBoardResponse(Title title, LocalDateTime createDateTime) {
        this.title = title;
        this.createDateTime = createDateTime;
    }

    public String getTitle(){return title.content();}
}
