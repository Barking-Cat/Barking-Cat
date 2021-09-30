package PetShop.BarkingCat.common.config;

import PetShop.BarkingCat.common.security.JwtService;
import PetShop.BarkingCat.common.security.JwtInterceptor;
import PetShop.BarkingCat.common.security.JwtSessionArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final JwtService jwtService;
    public final static String JWT_COOKIE_NAME = "barking.cat";

    public WebConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(jwtArgsResolver());
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor(jwtService, JWT_COOKIE_NAME);
    }

    @Bean
    public JwtSessionArgumentResolver jwtArgsResolver() {
        return new JwtSessionArgumentResolver(jwtService, JWT_COOKIE_NAME);
    }
}