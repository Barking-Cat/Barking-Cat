package PetShop.BarkingCat.domain.member.model;

import PetShop.BarkingCat.common.security.CookieFactory;
import PetShop.BarkingCat.domain.member.model.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    private final static int duration = 60 * 60 * 24;

    private final static String JWT_COOKIE_NAME = "barking.cat";

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String jwt = sessionService.login(loginForm, duration);

        if (jwt.equals("")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .build();
        }

        httpServletResponse.addCookie(createCookie(jwt, httpServletRequest));

        return ResponseEntity.ok()
                .build();
    }

    private Cookie createCookie(String jwt, HttpServletRequest httpServletRequest) {
        return CookieFactory.createCookie(conf -> conf
                .name(JWT_COOKIE_NAME)
                .value(jwt)
                .expires(duration)
                .secure("https".equals(httpServletRequest.getScheme()))
        );
    }
}
