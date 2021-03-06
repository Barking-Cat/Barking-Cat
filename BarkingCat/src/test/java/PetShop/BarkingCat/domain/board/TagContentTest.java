package PetShop.BarkingCat.domain.board;

import PetShop.BarkingCat.domain.board.model.objects.TagContent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TagContentTest {

    @Test
    @DisplayName("태그명이 비어있으면 Tag 생성이 실패한다")
    public void createTagFailByEmpty() {
        //given

        //when

        //then
        assertThatThrownBy(() -> {
            new TagContent("");
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("태그명이 max를 넘어가면 Tag 생성이 실패한다")
    public void createTagFailByOverTheMax() {
        //given

        //when

        //then
        assertThatThrownBy(() -> {
            new TagContent("this is over the max");
        }).isInstanceOf(RuntimeException.class);
    }
}
