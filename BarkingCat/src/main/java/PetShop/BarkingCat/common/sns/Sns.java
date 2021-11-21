package PetShop.BarkingCat.common.sns;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Sns {
    private final SnsProperty snsProperty;

    public void send(Message message) {
        AmazonSNS amazonSNS = createAmazonSNS();

        amazonSNS.publish(new PublishRequest().withMessage(message.getMessage())
                .withPhoneNumber(message.getPhoneNumber()));
    }

    private AmazonSNS createAmazonSNS() {
        return AmazonSNSClient.builder()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(snsProperty.getAccessKey(), snsProperty.getSecretKey())))
                .build();
    }
}
