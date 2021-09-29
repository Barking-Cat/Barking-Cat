package PetShop.BarkingCat.common.security;

import PetShop.BarkingCat.common.base.CheckedException;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Password {
    private final String password;

    private static final String SALT = "qkzldzot";

    public Password(String password) {
        this.password = password;
    }

    public String content() {
        String salt = password + SALT;
        MessageDigest msg = new CheckedException().wrap(() -> MessageDigest.getInstance("SHA-512"));
        msg.update(salt.getBytes());
        return String.format("%128x", new BigInteger(1, msg.digest()));
    }
}
