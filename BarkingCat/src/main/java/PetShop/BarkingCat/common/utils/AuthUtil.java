package PetShop.BarkingCat.common.utils;

import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AuthUtil {
    public static String createAuthCode() {
        return Stream.generate(() -> new Random().nextInt(10))
                .limit(4)
                .map(Objects::toString)
                .collect(Collectors.joining());
    }
}
