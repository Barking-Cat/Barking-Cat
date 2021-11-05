package PetShop.BarkingCat.domain.member.model;

import PetShop.BarkingCat.common.base.model.Base;
import PetShop.BarkingCat.common.exception.BarkingCatException;
import PetShop.BarkingCat.common.exception.ErrorCode;
import PetShop.BarkingCat.domain.board.model.objects.Money;
import PetShop.BarkingCat.domain.member.dto.MemberPayload;
import PetShop.BarkingCat.domain.member.model.objects.Email;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
public class Member extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private Email email;

    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private String name;

    private String businessNumber;

    protected Member() {
    }

    @Builder
    public Member(Long id, Email email, String password, String phone, MemberType memberType, String name, String businessNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.memberType = memberType;
        this.name = name;
        this.businessNumber = businessNumber;
        validateBusinessNumber();
    }

    public void checkPassword(String password, PasswordEncoder passwordEncoder) {
        if (passwordIsNotEqual(password, passwordEncoder)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }
    }

    private boolean passwordIsNotEqual(String password, PasswordEncoder passwordEncoder) {
        return !passwordEncoder.matches(password, this.password);
    }

    private void validateBusinessNumber() {
        if (isNormalMember()) {
            return;
        }

        if (businessNumber.isBlank()) {
            throw new RuntimeException("사업자번호를 입력하여야 합니다");
        }
    }

    public boolean isNormalMember() {
        return memberType == Member.MemberType.NORMAL;
    }

    public void checkBoardLimit(Integer count) {
        memberType.checkBoardLimit(count);
    }

    public Long id() {
        return this.id;
    }

    public MemberPayload createPayload() {
        return new MemberPayload(id, email.content());
    }

    public Money baseFee() {
        return memberType.fee();
    }

    public enum MemberType {
        NORMAL(3, Money.ZERO), COMPANY(100, Money.wons(20_000)), SHELTER(100, Money.wons(10_000));
        private final int boardLimit;
        private final Money fee;

        MemberType(int boardLimit, Money fee) {
            this.boardLimit = boardLimit;
            this.fee = fee;
        }

        public void checkBoardLimit(Integer count) {
            if (monthlyBoardCountIsOver(count)) {
                throw new BarkingCatException(ErrorCode.BOARD_WRITE_LIMIT);
            }
        }

        private boolean monthlyBoardCountIsOver(Integer count) {
            return count >= boardLimit;
        }

        public Money fee() {
            return this.fee;
        }
    }
}
