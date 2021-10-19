package PetShop.BarkingCat.domain.board.model.objects;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TagContent implements Serializable {
    private String tag;

    private final static int MAX_LENGTH = 10;

    protected TagContent() {
    }

    public TagContent(String tag) {
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
        TagContent tagContent1 = (TagContent) o;
        return Objects.equals(tag, tagContent1.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}
