package PetShop.BarkingCat.common.security;

import PetShop.BarkingCat.common.security.annotations.JwtClaim;
import com.jayway.jsonpath.JsonPath;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.stream.Stream;

public class JwtSessionArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtService jwtService;
    private final String COOKIE_KEY;

    public JwtSessionArgumentResolver(JwtService jwtService, String cookie) {
        this.jwtService = jwtService;
        this.COOKIE_KEY = cookie;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JwtClaim.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        if (requestIsNull(request)) {
            throw new RuntimeException("Web Request Parsing Error");
        }

        String cookie = CookieFactory.getCookie(request, COOKIE_KEY)
                .map(Cookie::getValue)
                .orElseThrow(() -> new RuntimeException("토큰이 없습니다"));

        return findClaimValue(parameter, cookie);
    }

    private boolean requestIsNull(HttpServletRequest request) {
        return request == null;
    }

    private Object findClaimValue(MethodParameter parameter, String cookie) {
        return Stream.of(cookie)
                .map(jwtService::parseClaim)
                .filter(Objects::nonNull)
                .findFirst()
                .map(claim -> JsonPath.parse(claim)
                        .read(findPath(parameter), parameter.getParameterType()))
                .orElseThrow(() -> new RuntimeException("Unavailable web token!!!"));
    }

    private String findPath(MethodParameter parameter) {
        JwtClaim annotation = parameter.getParameterAnnotation(JwtClaim.class);
        return String.format("$.%s", annotation.value());
    }
}