//package PetShop.BarkingCat.common;
//
//import PetShop.BarkingCat.common.base.converter.TagConverter;
//import PetShop.BarkingCat.domain.board.model.objects.TagContent;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class ConverterTest {
//
//    @Nested
//    @DisplayName("TagConverter는")
//    class TagContentConverter_test {
//
//        private final TagConverter tagConverter = new TagConverter();
//
//        @Nested
//        @DisplayName("List<Tag>를 String으로 변환할 때")
//        class Convert__to_String {
//
//            @Test
//            @DisplayName("값이 null이면 빈 문자열을 리턴한다.")
//            public void valueIsNull() {
//                //given
//
//                //when
//                String result = tagConverter.convertToDatabaseColumn(null);
//
//                //then
//                assertThat(result).isEqualTo("");
//            }
//
//            @Test
//            @DisplayName("값이 빈 List면 빈 문자열을 리턴한다.")
//            public void valueIsEmpty() {
//                //given
//
//                //when
//                String result = tagConverter.convertToDatabaseColumn(new ArrayList<>());
//
//                //then
//                assertThat(result).isEqualTo("");
//            }
//
//            @Test
//            @DisplayName("값이 하나면 하나의 문자열만 리턴한다")
//            public void convertSingleValue() {
//                //given
//
//                //when
//                String result = tagConverter.convertToDatabaseColumn(Collections.singletonList(new TagContent("test")));
//
//                //then
//                assertThat(result).isEqualTo("test");
//            }
//
//            @Test
//            @DisplayName("값이 여러개면 |로 구분지어 리턴한다")
//            public void convertMultipleValue() {
//                //given
//
//                //when
//                String result = tagConverter.convertToDatabaseColumn(Arrays.asList(new TagContent("test"), new TagContent("test2")));
//
//                //then
//                assertThat(result).isEqualTo("test | test2");
//            }
//        }
//
//        @Nested
//        @DisplayName("List<Tag>를 String으로 변환할 때")
//        class Convert__to_List {
//
//            @Test
//            @DisplayName("문자열이 null이면 빈 리스트를 리턴한다")
//            public void valueIsNull() {
//                //given
//
//                //when
//                List<TagContent> tagContents = tagConverter.convertToEntityAttribute(null);
//
//                //then
//                assertThat(tagContents).isEmpty();
//            }
//
//            @Test
//            @DisplayName("문자열이 비어있으면 빈 리스트를 리턴한다")
//            public void valueIsEmpty() {
//                //given
//
//                //when
//                List<TagContent> tagContents = tagConverter.convertToEntityAttribute("");
//
//                //then
//                assertThat(tagContents).isEmpty();
//            }
//
//            @Test
//            @DisplayName("문자열 값이 하나면 하나의 Tag만 리턴한다")
//            public void convertSingleValue() {
//                //given
//
//                //when
//                List<TagContent> result = tagConverter.convertToEntityAttribute("test");
//
//                //then
//                assertThat(result.get(0)).isEqualTo(new TagContent("test"));
//            }
//
//            @Test
//            @DisplayName("문자열 값이 여러개면 갯수만큼 Tag를 리턴한다")
//            public void convertMultipleValue() {
//                //given
//
//                //when
//                List<TagContent> result = tagConverter.convertToEntityAttribute("test | test2 | test3");
//
//                //then
//                assertThat(result.size()).isEqualTo(3);
//                assertThat(result.get(0)).isEqualTo(new TagContent("test"));
//                assertThat(result.get(1)).isEqualTo(new TagContent("test2"));
//                assertThat(result.get(2)).isEqualTo(new TagContent("test3"));
//            }
//        }
//    }
//}
