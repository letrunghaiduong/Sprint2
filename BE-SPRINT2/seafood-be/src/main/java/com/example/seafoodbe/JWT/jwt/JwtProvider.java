package com.example.seafoodbe.JWT.jwt;

import com.example.seafoodbe.JWT.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);// ghi log trong class jwtprovider
    private String jwtSecret = "letrunghaiduong@gmail.com"; // chữ ký của token
    private final int jwtExpiration = 60 * 60 * 24; // thời gian sống token 1 ngày

    public String createToken(Authentication authentication) {
        UserPrinciple accountPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(accountPrinciple.getUsername())// thêm vào
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(new Date(System.currentTimeMillis()).getTime() + jwtExpiration * 1000L))
                .signWith(SignatureAlgorithm.HS384, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) { // valid token
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return true;

        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {Token không hộ trợ}", e);
        } catch (MalformedJwtException e) {
            logger.error("The token invalid format -> Message: {Token không đúng định dạng}", e);

        } catch (SignatureException e) {
            logger.error("Invalid JWT Signature -> Message: {Token không hợp lệ}", e);

        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {Token có khoảng trắng}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {Token hết hạn}", e );
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject(); // lấy ra
        return username;
    }
}
