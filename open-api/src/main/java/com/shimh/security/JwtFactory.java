package com.shimh.security;

import com.shimh.config.AppProperties;
import com.shimh.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@Component
public class JwtFactory{

    @Autowired
    private AppProperties appProperties;


    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) throws JwtException {
        return Jwts.parser()
                .setSigningKey(appProperties.getSecurity().getJwt().getTokenSigningKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(User user) {
        return generateToken(user.getUsername());
    }
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        LocalDateTime currentTime = LocalDateTime.now();

        final Date expirationDate = calculateExpirationDate(currentTime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getSecurity().getJwt().getTokenSigningKey())
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getIssuedAtDateFromToken(token);
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        LocalDateTime currentTime = LocalDateTime.now();

        final Date expirationDate = calculateExpirationDate(currentTime);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()));
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, appProperties.getSecurity().getJwt().getTokenSigningKey())
                .compact();
    }

    private Date calculateExpirationDate(LocalDateTime currentTime) {
        return Date.from(currentTime
                .plusSeconds(appProperties.getSecurity().getJwt().getTokenExpirationTime())
                .atZone(ZoneId.systemDefault()).toInstant());
    }

}
