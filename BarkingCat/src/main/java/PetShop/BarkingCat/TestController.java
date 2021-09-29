package PetShop.BarkingCat;

import PetShop.BarkingCat.common.security.CookieFactory;
import PetShop.BarkingCat.common.security.JwtService;
import PetShop.BarkingCat.domain.member.model.LoginForm;
import PetShop.BarkingCat.common.security.annotations.Authenticated;
import PetShop.BarkingCat.common.security.annotations.JwtClaim;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/heartbeat")
    public String heartbeat() {
        return "Hello";
    }

    public final static String JWT_COOKIE_NAME = "my.test.jwt";

    private final JwtService jwtService;

    public TestController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/session")
    public Map<String, Object> createSession(@RequestBody LoginForm loginForm, HttpServletRequest req, HttpServletResponse res) {
        int duration = 60 * 60 * 24;
        ZonedDateTime expirationDateTime = ZonedDateTime.now()
                .plusSeconds(duration);

        String jwt = jwtService.createToken(loginForm, expirationDateTime);

        Cookie jwtCookie = CookieFactory.createCookie(conf -> conf
                .name(JWT_COOKIE_NAME)
                .value(jwt)
                .expires(duration)
                .secure("https".equals(req.getScheme()))
        );

        res.addCookie(jwtCookie);

        return Map.of("token", jwt);
    }

    @GetMapping("/session")
    public Map<String, Object> getSession(HttpServletRequest req, HttpServletResponse res) {

        return Map.of(
                "token", CookieFactory.getCookie(req, JWT_COOKIE_NAME)
                        .map(Cookie::getValue),
                "tokenName", CookieFactory.getCookie(req, JWT_COOKIE_NAME)
                        .map(Cookie::getName)
        );
    }

    @DeleteMapping("/session")
    public Map<String, Object> revokeSession(HttpServletRequest req, HttpServletResponse res) {
        CookieFactory.getCookie(req, JWT_COOKIE_NAME)
                .ifPresent(cookie -> CookieFactory.removeCookie(cookie, res));
        return Map.of(
                "code", "session.revoked",
                "message", "session.revoked"
        );
    }

    @Authenticated
    @GetMapping("/testApi")
    public Map<String, Object> testApi(@JwtClaim("info.email") String id, @JwtClaim("info.password") String pw) {

        return Map.of(
                "id", id,
                "pw", pw
        );
    }
}
