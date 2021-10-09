package PetShop.BarkingCat.domain.member.model.objects;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class Email implements Serializable {
    private String email;

    private static final String REGEX = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

    private static final Pattern PATTERN = Pattern.compile(REGEX);

    protected Email() {
    }

    public Email(String email) {
        validate(email);
        this.email = email;
    }

    public void validate(String email) {
        validateEmailPatter(email);
    }

    private void validateEmailPatter(String email) {
        if (emailNotMatchWithPattern(email)) {
            throw new RuntimeException("이메일 형식이 맞지 않습니다");
        }
    }

    private boolean emailNotMatchWithPattern(String email) {
        return !PATTERN.matcher(email)
                .matches();
    }

    public String content() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
