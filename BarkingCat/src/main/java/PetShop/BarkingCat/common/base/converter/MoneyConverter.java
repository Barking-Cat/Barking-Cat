//package PetShop.BarkingCat.common.base.converter;
//
//import PetShop.BarkingCat.domain.board.model.objects.Money;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//
//@Converter(autoApply = true)
//public class MoneyConverter implements AttributeConverter<Money, Long> {
//    @Override
//    public Long convertToDatabaseColumn(Money money) {
//        return money.amount().longValue();
//    }
//
//    @Override
//    public Money convertToEntityAttribute(Long amount) {
//        return Money.wons(amount);
//    }
//}