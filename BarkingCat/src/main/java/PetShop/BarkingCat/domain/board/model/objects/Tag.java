package PetShop.BarkingCat.domain.board.model.objects;

import java.util.Objects;

public class Tag {
    private String tag;

    private final static int MAX_LENGTH = 10;

    private Tag() {
    }

    public Tag(String tag) {
        validate(tag);
        this.tag = tag;
    }

    private void validate(String tag) {
        if (tagLengthOverTheMax(tag)) {
            throw new RuntimeException("태그 하나의 길이는 최대 " + MAX_LENGTH + "를 초과할 수 없습니다");
        }

        if (tagIsBlank(tag)) {
            throw new RuntimeException("태그값이 비어있습니다");
        }
    }

    private boolean tagLengthOverTheMax(String tag) {
        return tag.length() > MAX_LENGTH;
    }

    private boolean tagIsBlank(String tag) {
        return tag.isBlank();
    }

    public String content() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag1 = (Tag) o;
        return Objects.equals(tag, tag1.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}
