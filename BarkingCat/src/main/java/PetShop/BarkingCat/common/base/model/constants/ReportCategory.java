package PetShop.BarkingCat.common.base.model.constants;

public enum ReportCategory {
    ADVERTISEMENT("광고"),
    ADULT_CONTENT("성인물"),
    POLITICAL_CONTENT("정치적 게시물"),
    HATEFUL_CONTENT("혐오감을 주는 게시물"),
    ETC("기타");

    private final String label;

    ReportCategory(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
