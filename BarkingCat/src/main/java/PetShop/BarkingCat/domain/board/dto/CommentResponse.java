package PetShop.BarkingCat.domain.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private final String content;

    private final String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDateTime createdDate;

    public CommentResponse(String content, String name, LocalDateTime createdDate) {
        this.content = content;
        this.name = name;
        this.createdDate = createdDate;
    }
}
