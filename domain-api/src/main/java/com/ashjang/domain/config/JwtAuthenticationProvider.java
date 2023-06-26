package com.ashjang.domain.config;

import com.ashjang.domain.common.UserVo;
import com.ashjang.domain.utils.Aes256Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Objects;

public class JwtAuthenticationProvider {
    private String secretKey = "signatureKey";
    private long tokenValidTime = 1000L * 60 * 20;

    public String createToken(String userPk, Long id) {
        Claims claims = Jwts.claims().setSubject(Aes256Utils.encrypt(userPk)).setId(Aes256Utils.encrypt(id.toString()));
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public UserVo getUserVo(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return new UserVo(
                Long.valueOf(Objects.requireNonNull(Aes256Utils.decrypt(claims.getId()))),
                Aes256Utils.decrypt(claims.getSubject())
        );
    }
}
