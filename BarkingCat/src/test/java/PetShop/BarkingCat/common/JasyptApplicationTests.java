package PetShop.BarkingCat.common;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JasyptApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void jasypt() {
        String accessKey = "AKIAQKWCU3BP67XIVHOX";
        String secretKey = "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";

        System.out.println(jasyptEncoding(accessKey));
        System.out.println(jasyptEncoding(secretKey));
    }

    public String jasyptEncoding(String value) {

        String key = "barking-cat";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }

}