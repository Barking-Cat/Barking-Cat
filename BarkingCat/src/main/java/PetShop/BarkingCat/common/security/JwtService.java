package PetShop.BarkingCat.common.security;

import PetShop.BarkingCat.domain.member.dto.LoginForm;
import PetShop.BarkingCat.domain.member.dto.MemberPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Component
public class JwtService {
    private final static byte[] JWT_KEY_SALT = "didhddkajdajdgoqhk".getBytes(StandardCharsets.UTF_8);
    private final static byte[] JWT_KEY = "didhddlajdajd".getBytes(StandardCharsets.UTF_8);

    private Key getSecretKey() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(JWT_KEY);
            md.update(JWT_KEY_SALT);
            return Keys.hmacShaKeyFor(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String createToken(MemberPayload memberPayload, ZonedDateTime expirationDateTime) {
        Payload payload = new Payload(memberPayload, expirationDateTime);
        return createToken(payload);
    }

    private String createToken(Payload payload) {
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setExpiration(payload.expirationDate())
                .signWith(getSecretKey());

        for (Map.Entry<String, Object> entry : payload.entrySetOfClaims()) {
            builder.claim(entry.getKey(), entry.getValue());
        }

        return builder.compact();
    }

    public boolean isUsable(String token, long current) {
        return checkJwt(token, current);
    }

    public String parseClaim(String token) {
        return parseJwt(token);
    }

    private boolean checkJwt(String token, long current) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token);

        Date expiration = claims.getBody()
                .getExpiration();

        return current <= expiration.getTime();
    }

    private String parseJwt(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token);

        try {
            return new ObjectMapper().writeValueAsString(claims.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("jwt 파싱에 실패했습니다.");
        }
    }
}