package PetShop.BarkingCat.base.converter;

import PetShop.BarkingCat.base.infra.Title;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TitleConverter implements AttributeConverter<Title, String> {
    @Override
    public String convertToDatabaseColumn(Title title) {
        return title.title();
    }

    @Override
    public Title convertToEntityAttribute(String title) {
        return new Title(title);
    }
}