package PetShop.BarkingCat.common;

import PetShop.BarkingCat.common.security.JwtService;
import PetShop.BarkingCat.domain.member.dto.LoginForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtServiceTest {

    @Nested
    @DisplayName("JwtService.isUsable() 메소드는")
    class IsUsableTest {
        private final JwtService jwtService = new JwtService();
        private final int duration = 60 * 60 * 24;
        private final ZonedDateTime expirationDateTime = ZonedDateTime.of(LocalDateTime.of(LocalDate.of(2022, 10, 1), LocalTime.of(0, 0, 0)), ZoneId.of("Asia/Seoul"))
                .plusSeconds(duration);

        @Nested
        @DisplayName("주어진 시간이 만료시간을 넘지 않은 경우")
        class Time_under_expirationTime {
            @Test
            @DisplayName("true를 리턴한다")
            public void isUsableTrue() {
                //given
                String token = jwtService.createToken(new LoginForm(), expirationDateTime);

                long expired = expirationDateTime
                        .toInstant()
                        .getEpochSecond() * 1000;

                //when
                boolean result = jwtService.isUsable(token, expired);

                //then
                assertThat(result).isTrue();
            }
        }

        @Nested
        @DisplayName("주어진 시간이 만료시간을 넘은 경우")
        class Time_over_expirationTime {
            @Test
            @DisplayName("false를 리턴한다")
            public void isUsableFalse() {
                //given
                String token = jwtService.createToken(new LoginForm(), expirationDateTime);

                long expired = expirationDateTime
                        .plusSeconds(100)
                        .toInstant()
                        .getEpochSecond() * 1000;

                //when
                boolean result = jwtService.isUsable(token, expired);

                //then
                assertThat(result).isFalse();
            }
        }
    }
}
