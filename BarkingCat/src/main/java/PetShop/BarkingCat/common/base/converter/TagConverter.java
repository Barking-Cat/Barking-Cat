package PetShop.BarkingCat.common.base.converter;

import PetShop.BarkingCat.domain.board.model.Tag;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagConverter implements AttributeConverter<List<Tag>, String> {
    @Override
    public String convertToDatabaseColumn(List<Tag> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }

        return attribute.stream()
                .map(Tag::content)
                .collect(Collectors.joining(" | "));
    }

    @Override
    public List<Tag> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return new ArrayList<>();
        }

        return Arrays.stream(dbData.split("\\|"))
                .map(String::trim)
                .map(Tag::new)
                .collect(Collectors.toList());
    }
}
