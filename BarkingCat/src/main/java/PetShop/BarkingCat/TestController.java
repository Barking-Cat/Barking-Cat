package PetShop.BarkingCat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/heartbeat")
    public String heartbeat() {
        return "HI3";
    }
}
