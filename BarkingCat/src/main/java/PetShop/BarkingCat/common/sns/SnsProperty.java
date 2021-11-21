package PetShop.BarkingCat.common.sns;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("aws.sns")
@Getter
@Setter
public class SnsProperty {
    private String accessKey;
    private String secretKey;
}
