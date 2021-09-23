package PetShop.BarkingCat.base.model.constants;

public enum Earning {
    ONE_HUNDRED("1백만원 이상"), TWO_HUNDRED("2백만원 이상"), THREE_HUNDRED("3백만원 이상"), FOUR_HUNDRED("4백만원 이상"), FIVE_HUNDRED("오백만원 이상");

    private final String comment;

    Earning(String comment) {
        this.comment = comment;
    }
}