package PetShop.BarkingCat.board.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Title implements Serializable {
    private String title;

    private final static int BASE_LENGTH = 5;

    public Title() {
    }

    public Title(String title) {
        validateTitle(title);
        this.title = title;
    }

    private void validateTitle(String title) {
        if (titleLengthIsLowerThanBaseLength(title)) {
            throw new RuntimeException("제목은 최소 5자 이상이여야 합니다");
        }
    }

    private boolean titleLengthIsLowerThanBaseLength(String title) {
        return title.length() < BASE_LENGTH;
    }

    public String title() {
        return this.title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title1 = (Title) o;
        return Objects.equals(title, title1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
