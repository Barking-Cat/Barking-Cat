//package PetShop.BarkingCat.common.base.converter;
//
//import PetShop.BarkingCat.domain.board.model.objects.TagContent;
//
//import javax.persistence.AttributeConverter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class TagConverter implements AttributeConverter<List<TagContent>, String> {
//    @Override
//    public String convertToDatabaseColumn(List<TagContent> attribute) {
//        if (attribute == null || attribute.isEmpty()) {
//            return "";
//        }
//
//        return attribute.stream()
//                .map(TagContent::content)
//                .collect(Collectors.joining(" | "));
//    }
//
//    @Override
//    public List<TagContent> convertToEntityAttribute(String dbData) {
//        if (dbData == null || dbData.isBlank()) {
//            return new ArrayList<>();
//        }
//
//        return Arrays.stream(dbData.split("\\|"))
//                .map(String::trim)
//                .map(TagContent::new)
//                .collect(Collectors.toList());
//    }
//}
